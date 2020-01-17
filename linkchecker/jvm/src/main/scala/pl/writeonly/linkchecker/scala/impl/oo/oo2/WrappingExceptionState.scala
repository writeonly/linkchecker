package pl.writeonly.linkchecker.scala.impl.oo.oo2

import pl.writeonly.linkchecker.scala.common.states.oo._
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.common.url.urls.Urls
import pl.writeonly.linkchecker.scala.sourcepage.oo._

class WrappingExceptionState(urls: Urls)(implicit d: Domain) extends AbstractOOState(urls) {

  override protected def nextState(urls: Urls): AbstractOOState = new WrappingExceptionState(urls)

  override protected def impureFunction: InternalUrlToSourcePage = SourcePageOrThrowExceptionFromInternalUrl.apply
}

object WrappingExceptionState extends AbstractOOStateObject {

  override def fromDomain(implicit d: Domain): AbstractOOState = new WrappingExceptionState(Urls.fromDomain)
}
