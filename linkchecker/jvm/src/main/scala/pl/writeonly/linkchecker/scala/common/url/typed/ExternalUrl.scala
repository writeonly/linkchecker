package pl.writeonly.linkchecker.scala.common.url.typed

import pl.writeonly.linkchecker.scala.common.url._

final class ExternalUrl(private val url: WrappedUrl) extends Ordered[ExternalUrl] {

  def toInternalUrl(implicit d: Domain): InternalUrl = InternalUrl(url)

  override def compare(that: ExternalUrl): Int = url.compareTo(that.url)

  override def toString: String = url.toString
}
