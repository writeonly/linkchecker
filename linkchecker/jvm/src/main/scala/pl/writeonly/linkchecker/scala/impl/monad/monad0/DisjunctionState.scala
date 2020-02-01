package pl.writeonly.linkchecker.scala.impl.monad.monad0

import pl.writeonly.linkchecker.scala.common.states.api.monad.DisjunctionAPIState
import pl.writeonly.linkchecker.scala.common.states.notexception._
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.common.url.urls.UrlsWithThrowableList
import pl.writeonly.linkchecker.scala.sourcepage.monad.SourcePageDisjunctionFromInternalUrl

object DisjunctionState extends AbstractNextStateObject {

  override def fromDomain(implicit d: Domain): AbstractNextState = new DisjunctionState(UrlsWithThrowableList.fromDomain)
}

class DisjunctionState(data: UrlsWithThrowableList)(implicit d: Domain) extends DisjunctionAPIState(data) with AbstractFunctionState {

  override def impureFunction: HPFromInternalUrl = SourcePageDisjunctionFromInternalUrl

  override def nextState(data: UrlsWithThrowableList): AbstractNextState = new DisjunctionState(data)
}
