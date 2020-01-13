package pl.writeonly.scala.hyde.common.states.api

import pl.writeonly.scala.hyde.common.url._
import pl.writeonly.scala.hyde.common.url.urls._
import pl.writeonly.scala.hyde.sourcepage.thirdparty._

abstract class DisjunctionAPIState(data: UrlsWithThrowableList)(implicit d: Domain) extends AbstractAPIState(data) {
  override type HP = SourcePageDisjunction

  def nextData(set: SourcePageDisjunctionSet): UrlsWithThrowableList = {

    val partitioned = set.partition(_.isRight)

    val newWrappedUrls: WrappedUrlSet = partitioned._1
      .flatMap(DisjunctionAPIState.sourcePageDisjunctionToWrappedUrlSet)

    val newThrowableList: ThrowableList = partitioned._2.toList
      .flatMap(_.swap.toOption.toList)

    val newUrls = NewUrls(newWrappedUrls)

    data.next(newUrls, newThrowableList)
  }

}

object DisjunctionAPIState {
  val sourcePageDisjunctionToWrappedUrlSet: SourcePageDisjunction => WrappedUrlSet =
    _.map(_.getWrappedUrlSet).toOption.toSet.flatten
}
