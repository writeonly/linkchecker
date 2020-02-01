package pl.writeonly.linkchecker.scala.impl.io.io0

import pl.writeonly.linkchecker.scala.common.states.api.monad.ValidationAPIState
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.common.url.urls.UrlsWithThrowableList
import pl.writeonly.linkchecker.scala.sourcepage.io.SourcePageIOFromInternalUrl
import pl.writeonly.linkchecker.scala.sourcepage.monad._
import scalaz.effect.IO

object IOState {

  def fromDomain(implicit d: Domain): IOState = new IOState(UrlsWithThrowableList.fromDomain)

  def run(state: IOState): IO[ValidationAPIState] =
    if (state.isEmptyNextInternalUrls) IO.apply(state) else state.nextMonad.flatMap(run)

  private def sequence(set: Set[IO[SourcePageValidation]]): IO[SourcePageValidationSet] =
    set
      .foldLeft(IO.apply(Set.empty[SourcePageValidation])) { (b, a) =>
        for {
          bb <- b
          aa <- a
        } yield bb + aa
      }

}

class IOState(data: UrlsWithThrowableList)(implicit d: Domain) extends ValidationAPIState(data) {

  override type NextState = IOState

  def nextState(data: UrlsWithThrowableList): IOState = new IOState(data)

  def nextMonad: IO[IOState] = {

    val set: Set[IO[SourcePageValidation]] = data.nextUrls
      .map(SourcePageIOFromInternalUrl.apply)
    val monad: IO[SourcePageValidationSet] = IOState.sequence(set)

    monad.map(newState)
  }

}
