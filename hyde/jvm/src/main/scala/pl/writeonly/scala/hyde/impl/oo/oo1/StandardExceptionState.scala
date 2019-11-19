package pl.writeonly.scala.hyde.impl.oo.oo1

import pl.writeonly.scala.hyde.common.states.oo._
import pl.writeonly.scala.hyde.common.url.Domain
import pl.writeonly.scala.hyde.common.url.urls.Urls
import pl.writeonly.scala.hyde.sourcepage.oo._

class StandardExceptionState(urls: Urls)(implicit d: Domain) extends AbstractOOState(urls) {

  override protected def nextState(urls: Urls): AbstractOOState = new StandardExceptionState(urls)

  override protected def impureFunction: InternalUrlToSourcePage = SourcePageFromInternalUrl.apply
}

object StandardExceptionState extends AbstractOOStateObject {

  override def fromDomain(implicit d: Domain): AbstractOOState = new StandardExceptionState(Urls.fromDomain)
}
