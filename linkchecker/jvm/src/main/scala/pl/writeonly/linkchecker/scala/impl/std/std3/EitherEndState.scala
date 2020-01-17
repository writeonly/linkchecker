package pl.writeonly.linkchecker.scala.impl.std.std3

import scala.concurrent._
import scala.concurrent.duration._

import pl.writeonly.linkchecker.scala.common.states.api.std.EitherAPIState
import pl.writeonly.linkchecker.scala.common.states.notexception._
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.common.url.urls.UrlsWithThrowableList
import pl.writeonly.linkchecker.scala.sourcepage.std._
import scalaz.Scalaz._

class EitherEndState(data: UrlsWithThrowableList)(implicit d: Domain, ec: ExecutionContext) extends EitherAPIState(data) with AbstractNextState {

  override protected def nextState(data: UrlsWithThrowableList): AbstractNextState = new EitherEndState(data)

  override def next: AbstractNextState = {

    val set: Set[Future[SourcePageEither]] = nextUrls
      .map(SourcePageFutureFromInternalUrl.apply)

    val monad: Future[NextState] = Future
      .sequence(set)
      .map(newState)

    Await.result(monad, 1.minute)
  }
}

object EitherEndState {

  def apply(domain: String)(implicit ec: ExecutionContext): AbstractNextState = fromDomain(new Domain(domain)) |> AbstractNextState.run

  private def fromDomain(domain: Domain)(implicit ec: ExecutionContext): AbstractNextState = fromDomainAllImplicit(domain, ec)

  private def fromDomainAllImplicit(implicit d: Domain, ec: ExecutionContext): AbstractNextState =
    new EitherEndState(UrlsWithThrowableList.fromDomain)
}
