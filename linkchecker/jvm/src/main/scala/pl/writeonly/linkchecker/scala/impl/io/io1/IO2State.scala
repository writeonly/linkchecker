package pl.writeonly.linkchecker.scala.impl.io.io1

import pl.writeonly.linkchecker.scala.common.states.api.monad.ValidationAPIState
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.common.url.urls.UrlsWithThrowableList
import pl.writeonly.linkchecker.scala.sourcepage.io.SourcePageIO2FromInternalUrl
import pl.writeonly.linkchecker.scala.sourcepage.monad._
import scalaz.ioeffect.IO

object IO2State {

  def fromDomain(implicit d: Domain): IO2State = new IO2State(UrlsWithThrowableList.fromDomain)

  def run(state: IO2State): IO[Throwable, ValidationAPIState] =
    if (state.isEmptyNextInternalUrls) IO.now(state) else state.nextMonad.flatMap(run)

  private def sequence(set: Set[IO[Throwable, SourcePageValidation]]): IO[Throwable, SourcePageValidationSet] =
    set.foldLeft(IO.point[Throwable, SourcePageValidationSet](Set.empty)) {
      (b: IO[Throwable, SourcePageValidationSet], a: IO[Throwable, SourcePageValidation]) =>
        b.flatMap(bb => a.map(aa => bb + aa))
    }
}

class IO2State(data: UrlsWithThrowableList)(implicit d: Domain) extends ValidationAPIState(data) {

  override type NextState = IO2State

  def nextState(data: UrlsWithThrowableList): IO2State = new IO2State(data)

  def nextMonad: IO[Throwable, IO2State] = {

    val set: Set[IO[Throwable, SourcePageValidation]] = data.nextUrls
      .map(SourcePageIO2FromInternalUrl.apply)
    val monad: IO[Throwable, SourcePageValidationSet] = IO2State.sequence(set)

    monad.map(newState)
  }
}
