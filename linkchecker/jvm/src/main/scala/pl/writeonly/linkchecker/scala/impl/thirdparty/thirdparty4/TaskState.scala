package pl.writeonly.linkchecker.scala.impl.thirdparty.thirdparty4

import pl.writeonly.linkchecker.scala.common.states.api.thirdparty._
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.common.url.urls.UrlsWithThrowableList
import pl.writeonly.linkchecker.scala.sourcepage.thirdparty._
import scalaz.Scalaz._
import scalaz.concurrent.Task

object TaskState {

  def apply(domain: String): ParallelStateTask = fromDomain(new Domain(domain)) |> TaskState.run

  def fromDomain(implicit d: Domain): TaskState = new TaskState(UrlsWithThrowableList.fromDomain)

  def run(state: TaskState): Task[ValidationAPIState] =
    if (state.isEmptyNextInternalUrls) Task.now(state) else state.nextMonad.flatMap(run)
}

class TaskState(refsWithThrowable: UrlsWithThrowableList)(implicit d: Domain) extends ValidationAPIState(refsWithThrowable) {

  override type NextState = TaskState

  override def nextState(data: UrlsWithThrowableList): TaskState = new TaskState(data)

  def nextMonad: Task[TaskState] = {

    val set: Set[Task[SourcePageValidation]] = refsWithThrowable.nextUrls
      .map(SourcePageTaskFromInternalUrl.apply)

    val monad: Task[SourcePageValidationSet] = Task
      .gatherUnordered(set.toSeq)
      .map(_.toSet)

    monad.map(newState)
  }

}
