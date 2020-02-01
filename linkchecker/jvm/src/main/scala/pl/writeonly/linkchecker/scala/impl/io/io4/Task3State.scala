package pl.writeonly.linkchecker.scala.impl.io.io4

import pl.writeonly.linkchecker.scala.common.states.api.io.ParallelStateTask3
import pl.writeonly.linkchecker.scala.common.states.api.monad.ValidationAPIState
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.common.url.urls.UrlsWithThrowableList
import pl.writeonly.linkchecker.scala.sourcepage.io.SourcePageTask3FromInternalUrl
import pl.writeonly.linkchecker.scala.sourcepage.monad._
import zio._

object Task3State {

  def fromDomain(implicit d: Domain): Task3State = new Task3State(UrlsWithThrowableList.fromDomain)

  @SuppressWarnings(Array("org.wartremover.warts.Any"))
  def run(state: Task3State): ParallelStateTask3 =
    if (state.isEmptyNextInternalUrls) ZIO.effect(state) else state.nextMonad.flatMap(run)

  private def sequence(set: Set[Task[SourcePageValidation]]): Task[SourcePageValidationSet] =
    Task
      .traverse(set)(identity)
      .map(_.toSet)
}

class Task3State(data: UrlsWithThrowableList)(implicit d: Domain) extends ValidationAPIState(data) {

  override type NextState = Task3State

  def nextState(data: UrlsWithThrowableList): Task3State = new Task3State(data)

  def nextMonad: Task[Task3State] = {

    val set: Set[Task[SourcePageValidation]] = data.nextUrls
      .map(SourcePageTask3FromInternalUrl.apply)
    val monad: Task[SourcePageValidationSet] = Task3State.sequence(set)

    monad.map(newState)
  }
}
