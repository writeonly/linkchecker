package pl.writeonly.scala.hyde.impl.std.std2

import scala.concurrent._
import scala.concurrent.duration._

import pl.writeonly.scala.hyde.common.states.api.EitherAPIState
import pl.writeonly.scala.hyde.common.states.notexception._
import pl.writeonly.scala.hyde.common.url.Domain
import pl.writeonly.scala.hyde.common.url.urls.UrlsWithThrowableList
import pl.writeonly.scala.hyde.sourcepage.std._
import scalaz.Scalaz._

class EitherBeginState(data: UrlsWithThrowableList)(implicit d: Domain, ec: ExecutionContext) extends EitherAPIState(data) with AbstractNewSetState {

  override protected def nextState(data: UrlsWithThrowableList): AbstractNextState = new EitherBeginState(data)

  override protected def newSet: SourcePageEitherSet = {

    val set: Set[Future[SourcePageEither]] = nextUrls
      .map(SourcePageFutureFromInternalUrl.apply)

    val future: Future[SourcePageEitherSet] = Future.sequence(set)

    Await.result(future, 1.minute)
  }
}

object EitherBeginState {

  def apply(domain: String)(implicit ec: ExecutionContext): AbstractNextState = fromDomain(new Domain(domain)) |> AbstractNextState.run

  private def fromDomain(domain: Domain)(implicit ec: ExecutionContext): AbstractNextState = fromDomainAllImplicit(domain, ec)

  private def fromDomainAllImplicit(implicit d: Domain, ec: ExecutionContext): AbstractNextState =
    new EitherBeginState(UrlsWithThrowableList.fromDomain)

}
