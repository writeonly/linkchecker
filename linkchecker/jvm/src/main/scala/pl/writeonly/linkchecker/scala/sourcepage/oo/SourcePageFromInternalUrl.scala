package pl.writeonly.linkchecker.scala.sourcepage.oo

import scala.io.Source

import pl.writeonly.linkchecker.scala.common.url.typed.InternalUrl
import pl.writeonly.linkchecker.scala.sourcepage._
import scalaz.Scalaz._

object SourcePageFromInternalUrl extends InternalUrlToSourcePage {

  def apply(internalUrl: InternalUrl): SourcePage = Source.fromURL(internalUrl.toURL).mkString |> SourcePage.apply
}
