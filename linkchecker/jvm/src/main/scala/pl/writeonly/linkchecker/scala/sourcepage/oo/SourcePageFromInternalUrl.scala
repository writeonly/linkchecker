package pl.writeonly.linkchecker.scala.sourcepage.oo

import scala.io._

import pl.writeonly.linkchecker.scala.common.url.typed.InternalUrl
import pl.writeonly.linkchecker.scala.sourcepage._
import scalaz.Scalaz._

object SourcePageFromInternalUrl extends InternalUrlToSourcePage {

  def apply(internalUrl: InternalUrl): SourcePage = new SourcePageFromInternalUrl(internalUrl).toSourcePage
}

class SourcePageFromInternalUrl(internalUrl: InternalUrl) {

  def toSourcePage: SourcePage = internalUrl.toURI.toASCIIString |> Source.fromURL |> toSourcePage

  def toSourcePage(source: Source): SourcePage = SourcePage(source)(internalUrl)
}
