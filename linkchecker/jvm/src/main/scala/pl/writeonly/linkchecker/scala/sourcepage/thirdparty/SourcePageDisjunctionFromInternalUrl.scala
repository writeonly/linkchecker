package pl.writeonly.linkchecker.scala.sourcepage.thirdparty

import pl.writeonly.linkchecker.scala.common.url.typed.InternalUrl
import pl.writeonly.linkchecker.scala.sourcepage.InternalUrlTo
import pl.writeonly.linkchecker.scala.sourcepage.oo.SourcePageFromInternalUrl
import scalaz._
import scalaz.Scalaz._

object SourcePageDisjunctionFromInternalUrl extends InternalUrlTo[SourcePageDisjunction] {

  override def apply(internalUrl: InternalUrl): SourcePageDisjunction =
    Disjunction.attempt(internalUrl |> SourcePageFromInternalUrl.apply)(internalUrl.toException)
}
