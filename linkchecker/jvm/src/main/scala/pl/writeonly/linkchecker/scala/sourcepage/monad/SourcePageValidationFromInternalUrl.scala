package pl.writeonly.linkchecker.scala.sourcepage.monad

import pl.writeonly.linkchecker.scala.common.url.typed.InternalUrl
import pl.writeonly.linkchecker.scala.sourcepage.InternalUrlTo
import pl.writeonly.linkchecker.scala.sourcepage.oo.SourcePageFromInternalUrl
import scalaz.Scalaz._
import scalaz.Validation

object SourcePageValidationFromInternalUrl extends InternalUrlTo[SourcePageValidation] {

  override def apply(internalUrl: InternalUrl): SourcePageValidation = applyWithThrowable(internalUrl).leftMap(internalUrl.toException)

  private def applyWithThrowable(internalUrl: InternalUrl) = Validation.fromTryCatchNonFatal { internalUrl |> SourcePageFromInternalUrl.apply }
}
