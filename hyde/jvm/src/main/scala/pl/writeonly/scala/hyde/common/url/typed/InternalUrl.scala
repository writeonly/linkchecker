package pl.writeonly.scala.hyde.common.url.typed

import java.net.URL

import pl.writeonly.scala.hyde.common.url._
import pl.writeonly.scala.hyde.common.url.exception.UrlException

final class InternalUrl(private val url: WrappedUrl) extends Ordered[InternalUrl] {

  override def compare(that: InternalUrl): Int = url.compare(that.url)

  override def toString: String = url.toString

  def toException(e: Throwable): UrlException = url.toException(e)

  def toURL: URL = url.toURL
}

object InternalUrl {

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def apply(url: WrappedUrl)(implicit d: Domain): InternalUrl =
    if (url.isInternalUrl) new InternalUrl(url) else throw url.toNonInternalException
}
