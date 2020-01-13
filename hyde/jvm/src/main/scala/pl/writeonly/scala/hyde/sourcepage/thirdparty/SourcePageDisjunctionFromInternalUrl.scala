package pl.writeonly.scala.hyde.sourcepage.thirdparty

import pl.writeonly.scala.hyde.common.url.typed.InternalUrl
import pl.writeonly.scala.hyde.sourcepage.oo.SourcePageFromInternalUrl
import scalaz.Disjunction
import scalaz.Scalaz._

object SourcePageDisjunctionFromInternalUrl extends (InternalUrl => SourcePageDisjunction) {

  override def apply(internalUrl: InternalUrl): SourcePageDisjunction =
    Disjunction.attempt(internalUrl |> SourcePageFromInternalUrl.apply)(internalUrl.toException)

}
