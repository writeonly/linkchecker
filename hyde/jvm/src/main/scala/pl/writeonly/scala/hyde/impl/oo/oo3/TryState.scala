package pl.writeonly.scala.hyde.impl.oo.oo3

import pl.writeonly.scala.hyde.common.states.api.AbstractAPIState
import pl.writeonly.scala.hyde.common.states.notexception._
import pl.writeonly.scala.hyde.common.url._
import pl.writeonly.scala.hyde.common.url.urls._
import pl.writeonly.scala.hyde.sourcepage.oo._
import scalaz.Scalaz._

class TryState(data: UrlsWithThrowableList)(implicit d: Domain) extends AbstractAPIState(data) with AbstractFunctionState {

  override protected def nextState(data: UrlsWithThrowableList): AbstractNextState = new TryState(data)

  override protected def impureFunction: HPFromInternalUrl = SourcePageTryFromInternalUrl.apply

  override type HP = SourcePageTry

  def nextData(set: SourcePageTrySet): UrlsWithThrowableList = {

    val partitioned = set.partition(_.isSuccess)

    val newWrappedUrls: WrappedUrlSet = partitioned._1
      .flatMap(TryState.sourcePageTryToWrappedUrlSet)

    val newThrowableList: ThrowableList = partitioned._2.toList
      .flatMap(_.failed.toOption.toList)

    val newUrls = NewUrls(newWrappedUrls)

    data.next(newUrls, newThrowableList)
  }
}

object TryState extends AbstractNextStateObject {

  override protected def fromDomain(implicit d: Domain): AbstractNextState = new TryState(UrlsWithThrowableList.fromDomain)

  val sourcePageTryToWrappedUrlSet: SourcePageTry => WrappedUrlSet = _.map(_.getWrappedUrlSet).toOption.toSet.flatten
}
