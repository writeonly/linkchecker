package pl.writeonly.scala.hyde.sourcepage.std

import scala.util.control.Exception.nonFatalCatch

import pl.writeonly.scala.hyde.common.url.typed.InternalUrl
import pl.writeonly.scala.hyde.sourcepage.InternalUrlTo
import pl.writeonly.scala.hyde.sourcepage.oo.SourcePageFromInternalUrl
import scalaz.Scalaz._

object SourcePageEitherFromInternalUrl extends InternalUrlTo[SourcePageEither] {

  override def apply(internalUrl: InternalUrl): SourcePageEither = applyWithThrowable(internalUrl).left.map(internalUrl.toException)

  private def applyWithThrowable(internalUrl: InternalUrl) = nonFatalCatch either { internalUrl |> SourcePageFromInternalUrl.apply }
}
