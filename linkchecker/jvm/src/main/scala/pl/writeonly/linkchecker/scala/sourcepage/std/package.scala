package pl.writeonly.linkchecker.scala.sourcepage

import scala.concurrent.Future
import scala.util.Try

import pl.writeonly.linkchecker.scala.common.url.exception.UrlException

package object std {
  type SourcePageTry = Try[SourcePage]

  type SourcePageTrySet = Set[SourcePageTry]

  type SourcePageEither = Either[UrlException, SourcePage]

  type SourcePageEitherSet = Set[SourcePageEither]

  type SourcePageFuture = Future[SourcePageEither]

}
