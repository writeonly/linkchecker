package pl.writeonly.scala.hyde.sourcepage.oo

import scala.io.Source

import pl.writeonly.scala.hyde.common.url.typed.InternalUrl
import pl.writeonly.scala.hyde.sourcepage._
import scalaz.Scalaz._

object SourcePageFromInternalUrl extends InternalUrlToSourcePage {

  def apply(internalUrl: InternalUrl): SourcePage = Source.fromURL(internalUrl.toURL).mkString |> SourcePage.apply
}
