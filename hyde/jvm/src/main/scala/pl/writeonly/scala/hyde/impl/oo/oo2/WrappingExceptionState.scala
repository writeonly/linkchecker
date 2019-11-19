package pl.writeonly.scala.hyde.impl.oo.oo2

import pl.writeonly.scala.hyde.common.states.oo._
import pl.writeonly.scala.hyde.common.url.Domain
import pl.writeonly.scala.hyde.common.url.urls.Urls
import pl.writeonly.scala.hyde.sourcepage.oo._

class WrappingExceptionState(urls: Urls)(implicit d: Domain) extends AbstractOOState(urls) {

  override protected def nextState(urls: Urls): AbstractOOState = new WrappingExceptionState(urls)

  override protected def impureFunction: InternalUrlToSourcePage = SourcePageOrThrowExceptionFromInternalUrl.apply
}

object WrappingExceptionState extends AbstractOOStateObject {

  override def fromDomain(implicit d: Domain): AbstractOOState = new WrappingExceptionState(Urls.fromDomain)
}
