package pl.writeonly.linkchecker.scala.sourcepage.oo

import scala.io._

import pl.writeonly.linkchecker.scala.common.url.typed.InternalUrl
import pl.writeonly.linkchecker.scala.sourcepage._
import scalaz.Scalaz._

object SourcePageFromInternalUrl extends InternalUrlToSourcePage {

  def apply(internalUrl: InternalUrl): SourcePage = SourcePage(bufferedSource(internalUrl))(internalUrl)

  def bufferedSource(internalUrl: InternalUrl): Source = internalUrl.toURI.toASCIIString |> Source.fromURL
}
