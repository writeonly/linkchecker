package pl.writeonly.linkchecker.scala.sourcepage

import pl.writeonly.linkchecker.scala.common.url.exception.UrlException
import scalaz._
import scalaz.concurrent.Task

package object thirdparty {
  type SourcePageDisjunction = Disjunction[UrlException, SourcePage]

  type SourcePageDisjunctionSet = Set[SourcePageDisjunction]

  type SourcePageValidation = Validation[UrlException, SourcePage]

  type SourcePageValidationSet = Set[SourcePageValidation]

  type SourcePageTask = Task[SourcePageValidation]

}
