package pl.writeonly.scala.hyde.sourcepage

import scala.util.matching.Regex

import pl.writeonly.scala.hyde.common.url._
import scalaz.Scalaz._

final case class SourcePage(sourcePage: String) {

  def getWrappedUrlSet: WrappedUrlSet = SourcePage.HRefRegexSet.flatMap(getWrappedUrlSet)

  private def getWrappedUrlSet(regex: Regex): WrappedUrlSet =
    (for { m <- regex.findAllMatchIn(sourcePage) } yield m.group(1) |> WrappedUrl.apply).toSet
}

object SourcePage {
  private val HRefRegex1: Regex = """href='([^']*)'""".r
  private val HRefRegex2: Regex = """href="([^"]*)"""".r
  private val HRefRegexSet = Set(HRefRegex1, HRefRegex2)
}
