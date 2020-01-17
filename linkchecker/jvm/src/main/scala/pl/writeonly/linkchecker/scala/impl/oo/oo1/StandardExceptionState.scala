package pl.writeonly.linkchecker.scala.impl.oo.oo1

import pl.writeonly.linkchecker.scala.common.states.oo._
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.common.url.urls.Urls
import pl.writeonly.linkchecker.scala.sourcepage.oo._

class StandardExceptionState(urls: Urls)(implicit d: Domain) extends AbstractOOState(urls) {

  override protected def nextState(urls: Urls): AbstractOOState = new StandardExceptionState(urls)

  override protected def impureFunction: InternalUrlToSourcePage = SourcePageFromInternalUrl.apply
}

object StandardExceptionState extends AbstractOOStateObject {

  override def fromDomain(implicit d: Domain): AbstractOOState = new StandardExceptionState(Urls.fromDomain)
}
