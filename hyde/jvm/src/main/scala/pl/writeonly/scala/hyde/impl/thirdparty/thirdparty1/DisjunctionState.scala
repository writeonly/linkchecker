package pl.writeonly.scala.hyde.impl.thirdparty.thirdparty1

import pl.writeonly.scala.hyde.common.states.api.DisjunctionAPIState
import pl.writeonly.scala.hyde.common.states.notexception._
import pl.writeonly.scala.hyde.common.url.Domain
import pl.writeonly.scala.hyde.common.url.urls.UrlsWithThrowableList
import pl.writeonly.scala.hyde.sourcepage.thirdparty.SourcePageDisjunctionFromInternalUrl

class DisjunctionState(data: UrlsWithThrowableList)(implicit d: Domain) extends DisjunctionAPIState(data) with AbstractFunctionState {

  override def impureFunction: HPFromInternalUrl = SourcePageDisjunctionFromInternalUrl

  override def nextState(data: UrlsWithThrowableList): AbstractNextState = new DisjunctionState(data)
}

object DisjunctionState extends AbstractNextStateObject {

  override def fromDomain(implicit d: Domain): AbstractNextState = new DisjunctionState(UrlsWithThrowableList.fromDomain)
}
