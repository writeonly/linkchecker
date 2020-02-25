package pl.writeonly.linkchecker.scala.impl.io.io3

import pl.writeonly.linkchecker.scala.common.states.api.monad.ValidationAPIState
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.common.url.urls.UrlsWithThrowableList
import pl.writeonly.linkchecker.scala.sourcepage.io.SourcePageIO3FromInternalUrl
import pl.writeonly.linkchecker.scala.sourcepage.monad._
import zio._

object IO3State {

  def fromDomain(implicit d: Domain): IO3State = new IO3State(UrlsWithThrowableList.fromDomain)

  @SuppressWarnings(Array("org.wartremover.warts.Any"))
  def run(state: IO3State): IO[Throwable, ValidationAPIState] =
    if (state.isEmptyNextInternalUrls) IO.effect(state) else state.nextMonad.flatMap(run)

  private def sequence(set: Set[IO[Throwable, SourcePageValidation]]): IO[Throwable, SourcePageValidationSet] =
    IO.traverse(set)(identity)
      .map(_.toSet)
}

class IO3State(data: UrlsWithThrowableList)(implicit d: Domain) extends ValidationAPIState(data) {

  override type NextState = IO3State

  def nextState(data: UrlsWithThrowableList): IO3State = new IO3State(data)

  def nextMonad: IO[Throwable, IO3State] = {

    val set: Set[IO[Throwable, SourcePageValidation]] = data.nextUrls
      .map(SourcePageIO3FromInternalUrl.apply)
    val monad: IO[Throwable, SourcePageValidationSet] = IO3State.sequence(set)

    monad.map(newState)
  }
}
