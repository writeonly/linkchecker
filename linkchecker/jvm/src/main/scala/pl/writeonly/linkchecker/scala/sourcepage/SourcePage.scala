package pl.writeonly.linkchecker.scala.sourcepage

import scala.io.Source
import scala.util.matching.Regex

import pl.writeonly.linkchecker.scala.common.url._
import pl.writeonly.linkchecker.scala.common.url.typed._
import scalaz.Scalaz._

object SourcePage {
  def apply(source: Source)(implicit internalUrl: InternalUrl): SourcePage = SourcePage(source.mkString)

  private val HRefRegex1: Regex = """href='([^']*)'""".r
  private val HRefRegex2: Regex = """href="([^"]*)"""".r
  private val HRefRegexSet = Set(HRefRegex1, HRefRegex2)
}

final case class SourcePage(source: String)(implicit internalUrl: InternalUrl) {

  def getWrappedUrlSet: WrappedUrlSet = SourcePage.HRefRegexSet.flatMap(getWrappedUrlSet)

  private def getWrappedUrlSet(regex: Regex): WrappedUrlSet =
    (for { m <- regex.findAllMatchIn(source) } yield m.group(1) |> WrappedUrl.create).toSet
}
