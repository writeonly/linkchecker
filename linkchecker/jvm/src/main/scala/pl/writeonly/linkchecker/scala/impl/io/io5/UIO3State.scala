package pl.writeonly.linkchecker.scala.impl.io.io5

import pl.writeonly.linkchecker.scala.common.states.api.io.ParallelStateUIO3
import pl.writeonly.linkchecker.scala.common.states.api.monad.ValidationAPIState
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.common.url.urls.UrlsWithThrowableList
import pl.writeonly.linkchecker.scala.sourcepage.io.SourcePageUIO3FromInternalUrl
import pl.writeonly.linkchecker.scala.sourcepage.monad._
import zio._

object UIO3State {

  def fromDomain(implicit d: Domain): UIO3State = new UIO3State(UrlsWithThrowableList.fromDomain)

  @SuppressWarnings(Array("org.wartremover.warts.Any"))
  def run(state: UIO3State): ParallelStateUIO3 =
    if (state.isEmptyNextInternalUrls) ZIO.effectTotal(state) else state.nextMonad.flatMap(run)

  private def sequence(set: Set[UIO[SourcePageValidation]]): UIO[SourcePageValidationSet] =
    UIO
      .traverse(set)(identity)
      .map(_.toSet)
}

class UIO3State(data: UrlsWithThrowableList)(implicit d: Domain) extends ValidationAPIState(data) {

  override type NextState = UIO3State

  def nextState(data: UrlsWithThrowableList): UIO3State = new UIO3State(data)

  def nextMonad: UIO[UIO3State] = {

    val set: Set[UIO[SourcePageValidation]] = data.nextUrls
      .map(SourcePageUIO3FromInternalUrl.apply)
    val monad: UIO[SourcePageValidationSet] = UIO3State.sequence(set)

    monad.map(newState)
  }
}
