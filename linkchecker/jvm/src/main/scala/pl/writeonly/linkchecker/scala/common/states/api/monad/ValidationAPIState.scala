package pl.writeonly.linkchecker.scala.common.states.api.monad

import pl.writeonly.linkchecker.scala.common.states.api.AbstractAPIState
import pl.writeonly.linkchecker.scala.common.url._
import pl.writeonly.linkchecker.scala.common.url.urls._
import pl.writeonly.linkchecker.scala.sourcepage.monad._

abstract class ValidationAPIState(data: UrlsWithThrowableList)(implicit d: Domain) extends AbstractAPIState(data) {
  override type HP = SourcePageValidation

  def nextData(set: SourcePageValidationSet): UrlsWithThrowableList = {

    val partitioned = set.partition(_.isSuccess)

    val newWrappedUrls: WrappedUrlSet = partitioned._1
      .flatMap(ValidationAPIState.sourcePageValidationToWrappedUrlSet)

    val newThrowableList: ThrowableList = partitioned._2.toList
      .flatMap(_.swap.toOption.toList)

    val newUrls = NewUrls(newWrappedUrls)

    data.next(newUrls, newThrowableList)
  }

}

object ValidationAPIState {
  val sourcePageValidationToWrappedUrlSet: SourcePageValidation => WrappedUrlSet =
    _.map(_.getWrappedUrlSet).toOption.toSet.flatten
}
