package pl.writeonly.linkchecker.scala.sourcepage.std

import scala.util.control.Exception.nonFatalCatch

import pl.writeonly.linkchecker.scala.common.url.typed.InternalUrl
import pl.writeonly.linkchecker.scala.sourcepage.InternalUrlTo
import pl.writeonly.linkchecker.scala.sourcepage.oo.SourcePageFromInternalUrl
import scalaz.Scalaz._

object SourcePageEitherFromInternalUrl extends InternalUrlTo[SourcePageEither] {

  override def apply(internalUrl: InternalUrl): SourcePageEither = applyWithThrowable(internalUrl).left.map(internalUrl.toException)

  private def applyWithThrowable(internalUrl: InternalUrl) = nonFatalCatch either { internalUrl |> SourcePageFromInternalUrl.apply }
}
