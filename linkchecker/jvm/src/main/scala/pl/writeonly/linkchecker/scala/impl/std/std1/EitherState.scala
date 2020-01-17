package pl.writeonly.linkchecker.scala.impl.std.std1

import pl.writeonly.linkchecker.scala.common.states.api.std.EitherAPIState
import pl.writeonly.linkchecker.scala.common.states.notexception._
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.common.url.urls.UrlsWithThrowableList
import pl.writeonly.linkchecker.scala.sourcepage.std.SourcePageEitherFromInternalUrl

class EitherState(data: UrlsWithThrowableList)(implicit d: Domain) extends EitherAPIState(data) with AbstractFunctionState {

  override protected def nextState(data: UrlsWithThrowableList): AbstractNextState = new EitherState(data)

  override protected def impureFunction: HPFromInternalUrl = SourcePageEitherFromInternalUrl.apply
}

object EitherState extends AbstractNextStateObject {

  override protected def fromDomain(implicit d: Domain): AbstractNextState = new EitherState(UrlsWithThrowableList.fromDomain)
}
