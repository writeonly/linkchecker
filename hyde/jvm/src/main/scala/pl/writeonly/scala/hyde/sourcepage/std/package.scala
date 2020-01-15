package pl.writeonly.scala.hyde.sourcepage

import scala.concurrent.Future

import pl.writeonly.scala.hyde.common.url.exception.UrlException

package object std {
  type SourcePageEither = Either[UrlException, SourcePage]

  type SourcePageEitherSet = Set[SourcePageEither]

  type SourcePageFuture = Future[SourcePageEither]

}
