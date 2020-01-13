package pl.writeonly.scala.hyde.impl.thirdparty.thirdparty4

import pl.writeonly.scala.hyde.common.states.api._
import pl.writeonly.scala.hyde.common.url.Domain
import pl.writeonly.scala.hyde.common.url.urls.UrlsWithThrowableList
import pl.writeonly.scala.hyde.sourcepage.thirdparty._
import scalaz.Scalaz._
import scalaz.concurrent.Task

class TaskState(refsWithThrowable: UrlsWithThrowableList)(implicit d: Domain) extends DisjunctionAPIState(refsWithThrowable) {

  override type NextState = TaskState

  override def nextState(data: UrlsWithThrowableList): TaskState = new TaskState(data)

  def nextMonad: Task[TaskState] = {

    val set: Set[Task[SourcePageDisjunction]] = refsWithThrowable.nextUrls
      .map(SourcePageTaskFromInternalUrl.apply)

    val monad: Task[SourcePageDisjunctionSet] = Task
      .gatherUnordered(set.toSeq)
      .map(_.toSet)

    monad.map(newState)
  }

}

object TaskState {

  def apply(domain: String): ParallelStateTask = fromDomain(new Domain(domain)) |> TaskState.run

  def fromDomain(implicit d: Domain): TaskState = new TaskState(UrlsWithThrowableList.fromDomain)

  def run(state: TaskState): Task[DisjunctionAPIState] =
    if (state.isEmptyNextInternalUrls) Task.now(state) else state.nextMonad.flatMap(run)
}
