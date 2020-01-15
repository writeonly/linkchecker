package pl.writeonly.scala.hyde.common.states.api

import pl.writeonly.scala.hyde.common.url._
import pl.writeonly.scala.hyde.common.url.urls._
import pl.writeonly.scala.hyde.sourcepage.std._

abstract class EitherAPIState(data: UrlsWithThrowableList)(implicit d: Domain) extends AbstractAPIState(data) {
  override protected type HP = SourcePageEither

  protected def nextData(set: SourcePageEitherSet): UrlsWithThrowableList = {

    val partitioned = set.partition(_.isRight)

    val newWrappedUrls: WrappedUrlSet = partitioned._1
      .flatMap(EitherAPIState.sourcePageEitherToWrappedUrlSet)

    val newThrowableList: ThrowableList = partitioned._2.toList
      .flatMap(_.left.toOption.toList)

    val newUrls = NewUrls(newWrappedUrls)

    data.next(newUrls, newThrowableList)
  }
}

object EitherAPIState {
  val sourcePageEitherToWrappedUrlSet: SourcePageEither => WrappedUrlSet =
    _.right.map(_.getWrappedUrlSet).right.toOption.toSet.flatten
}
