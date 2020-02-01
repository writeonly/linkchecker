package pl.writeonly.linkchecker.scala.impl.std.std0

import pl.writeonly.linkchecker.scala.common.states.api.std.TryAPIState
import pl.writeonly.linkchecker.scala.common.states.notexception._
import pl.writeonly.linkchecker.scala.common.url._
import pl.writeonly.linkchecker.scala.common.url.urls._
import pl.writeonly.linkchecker.scala.sourcepage.std._

object TryState extends AbstractNextStateObject {

  override protected def fromDomain(implicit d: Domain): AbstractNextState = new TryState(UrlsWithThrowableList.fromDomain)

  val sourcePageTryToWrappedUrlSet: SourcePageTry => WrappedUrlSet = _.map(_.getWrappedUrlSet).toOption.toSet.flatten
}

class TryState(data: UrlsWithThrowableList)(implicit d: Domain) extends TryAPIState(data) with AbstractFunctionState {

  override protected def nextState(data: UrlsWithThrowableList): AbstractNextState = new TryState(data)

  override protected def impureFunction: HPFromInternalUrl = SourcePageTryFromInternalUrl.apply
}
