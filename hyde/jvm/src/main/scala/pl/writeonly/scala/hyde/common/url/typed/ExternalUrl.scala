package pl.writeonly.scala.hyde.common.url.typed

import pl.writeonly.scala.hyde.common.url._

final class ExternalUrl(private val url: WrappedUrl) extends Ordered[ExternalUrl] {

  def toInternalUrl(implicit d: Domain): InternalUrl = InternalUrl(url)

  override def compare(that: ExternalUrl): Int = url.compareTo(that.url)

  override def toString: String = url.toString
}
