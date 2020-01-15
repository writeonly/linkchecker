package pl.writeonly.scala.hyde.impl.std.std1

import pl.writeonly.scala.hyde.common.states.api.EitherAPIState
import pl.writeonly.scala.hyde.common.states.notexception._
import pl.writeonly.scala.hyde.common.url.Domain
import pl.writeonly.scala.hyde.common.url.urls.UrlsWithThrowableList
import pl.writeonly.scala.hyde.sourcepage.std.SourcePageEitherFromInternalUrl

class EitherState(data: UrlsWithThrowableList)(implicit d: Domain) extends EitherAPIState(data) with AbstractFunctionState {

  override protected def nextState(data: UrlsWithThrowableList): AbstractNextState = new EitherState(data)

  override protected def impureFunction: HPFromInternalUrl = SourcePageEitherFromInternalUrl.apply
}

object EitherState extends AbstractNextStateObject {

  override protected def fromDomain(implicit d: Domain): AbstractNextState = new EitherState(UrlsWithThrowableList.fromDomain)
}
