package pl.writeonly.scala.hyde.sourcepage

import scala.util.Try

package object oo {

  type InternalUrlToSourcePage = InternalUrlTo[SourcePage]

  type SourcePageTry = Try[SourcePage]

  type SourcePageTrySet = Set[SourcePageTry]
}
