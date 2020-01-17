package pl.writeonly.linkchecker.scala.sourcepage.std

import scala.util.Try

import pl.writeonly.linkchecker.scala.common.url.typed.InternalUrl
import pl.writeonly.linkchecker.scala.sourcepage.InternalUrlTo
import pl.writeonly.linkchecker.scala.sourcepage.oo.SourcePageFromInternalUrl
import scalaz.Scalaz._

object SourcePageTryFromInternalUrl extends InternalUrlTo[SourcePageTry] {

  def apply(internalUrl: InternalUrl): SourcePageTry = Try { internalUrl |> SourcePageFromInternalUrl.apply }
}
