package pl.writeonly.linkchecker.scala.impl.monad.monad1

import pl.writeonly.linkchecker.scala.common.states.api.monad.ValidationAPIState
import pl.writeonly.linkchecker.scala.common.states.notexception._
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.common.url.urls.UrlsWithThrowableList
import pl.writeonly.linkchecker.scala.sourcepage.monad.SourcePageValidationFromInternalUrl

object ValidationState extends AbstractNextStateObject {

  override def fromDomain(implicit d: Domain): AbstractNextState = new ValidationState(UrlsWithThrowableList.fromDomain)
}

class ValidationState(data: UrlsWithThrowableList)(implicit d: Domain) extends ValidationAPIState(data) with AbstractFunctionState {

  override def impureFunction: HPFromInternalUrl = SourcePageValidationFromInternalUrl

  override def nextState(data: UrlsWithThrowableList): AbstractNextState = new ValidationState(data)
}
