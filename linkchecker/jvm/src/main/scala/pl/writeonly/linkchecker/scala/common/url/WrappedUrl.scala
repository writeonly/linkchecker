package pl.writeonly.linkchecker.scala.common.url

import java.net._

import pl.writeonly.linkchecker.scala.common.url.exception._
import pl.writeonly.linkchecker.scala.common.url.typed._

final case class WrappedUrl(private val url: String) extends Ordered[WrappedUrl] {

  override def toString: String = url

  override def compare(that: WrappedUrl): Int = url.compareTo(that.url)

  def append(that: WrappedUrl): WrappedUrl = new WrappedUrl(url + that.url)

  def toURL: URL = new URL(url)

  def toURI: URI = URI.create(url)

  def toException(e: Throwable): UrlException = new UrlException(url, e)

  def toNonInternalException: NonInternalUrlException = new NonInternalUrlException(url)

  def toNonRelativeException: NonRelativeUrlException = new NonRelativeUrlException(url)

  def isRelativeUrl: Boolean = url.startsWith("/")

  def isInternalUrl(implicit d: Domain): Boolean = d.isInternalUrl(this)

  def isInternalUrl(d: WrappedUrl): Boolean = url.startsWith(d.url)

  def toInternalUrl(implicit d: Domain): InternalUrl = InternalUrl(this)

  def makeInternalUrl(implicit d: Domain): InternalUrl = d.toInternalUrl(this)

  def toExternalUrl: ExternalUrl = new ExternalUrl(this)

  def getType(implicit d: Domain): Symbol = url match {
    case _ if isInternalUrl => WrappedUrl.Internal
    case _ if isRelativeUrl => WrappedUrl.Relative
    case _                  => WrappedUrl.External
  }
}

object WrappedUrl {
  val Internal = 'internal
  val Relative = 'relative
  val External = 'external
}
