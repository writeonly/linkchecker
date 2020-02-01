package pl.writeonly.linkchecker.scala.impl.std.std4

import scala.concurrent._

import pl.writeonly.linkchecker.scala.common.states.api.std._
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.common.url.urls.UrlsWithThrowableList
import pl.writeonly.linkchecker.scala.sourcepage.std._
import scalaz.Scalaz._

object FutureState {

  def apply(domain: String)(implicit ec: ExecutionContext): ParallelStateFuture = fromDomain(new Domain(domain)) |> run

  private def fromDomain(domain: Domain)(implicit ec: ExecutionContext): FutureState = fromDomainAllImplicit(domain, ec)

  private def fromDomainAllImplicit(implicit d: Domain, ec: ExecutionContext): FutureState =
    new FutureState(UrlsWithThrowableList.fromDomain)

  private def run(state: FutureState)(implicit executor: ExecutionContext): Future[EitherAPIState] =
    if (state.isEmptyNextInternalUrls) Future.successful(state) else state.nextMonad.flatMap(run)
}

class FutureState(data: UrlsWithThrowableList)(implicit d: Domain, ec: ExecutionContext) extends EitherAPIState(data) {

  override protected def nextState(data: UrlsWithThrowableList): NextState = new FutureState(data)

  override type NextState = FutureState

  def nextMonad: Future[FutureState] = {

    val set: Set[Future[SourcePageEither]] = nextUrls
      .map(SourcePageFutureFromInternalUrl.apply)

    val monad: Future[SourcePageEitherSet] = Future
      .sequence(set)

    monad.map(newState)
  }
}
