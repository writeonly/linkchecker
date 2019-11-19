package pl.writeonly.scala.hyde.sourcepage.oo

import scala.util.Try

import pl.writeonly.scala.hyde.common.url.typed.InternalUrl
import pl.writeonly.scala.hyde.sourcepage.InternalUrlTo
import scalaz.Scalaz._

object SourcePageTryFromInternalUrl extends InternalUrlTo[SourcePageTry] {

  def apply(internalUrl: InternalUrl): SourcePageTry = Try { internalUrl |> SourcePageFromInternalUrl.apply }
}
