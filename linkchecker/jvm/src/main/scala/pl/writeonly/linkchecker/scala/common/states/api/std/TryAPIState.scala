package pl.writeonly.linkchecker.scala.common.states.api.std

import pl.writeonly.linkchecker.scala.common.states.api.AbstractAPIState
import pl.writeonly.linkchecker.scala.common.url._
import pl.writeonly.linkchecker.scala.common.url.urls._
import pl.writeonly.linkchecker.scala.impl.std.std0.TryState
import pl.writeonly.linkchecker.scala.sourcepage.std._

abstract class TryAPIState(data: UrlsWithThrowableList)(implicit d: Domain) extends AbstractAPIState(data) {
  override protected type HP = SourcePageTry

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
