package pl.writeonly.linkchecker.scala.impl.thirdparty.thirdparty0

import pl.writeonly.linkchecker.scala.common.states.api.thirdparty.DisjunctionAPIState
import pl.writeonly.linkchecker.scala.common.states.notexception._
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.common.url.urls.UrlsWithThrowableList
import pl.writeonly.linkchecker.scala.sourcepage.thirdparty.SourcePageDisjunctionFromInternalUrl

object DisjunctionState extends AbstractNextStateObject {

  override def fromDomain(implicit d: Domain): AbstractNextState = new DisjunctionState(UrlsWithThrowableList.fromDomain)
}

class DisjunctionState(data: UrlsWithThrowableList)(implicit d: Domain) extends DisjunctionAPIState(data) with AbstractFunctionState {

  override def impureFunction: HPFromInternalUrl = SourcePageDisjunctionFromInternalUrl

  override def nextState(data: UrlsWithThrowableList): AbstractNextState = new DisjunctionState(data)
}
