package pl.writeonly.linkchecker.scala.common.url

import java.net._

import pl.writeonly.linkchecker.scala.common.url.exception._
import pl.writeonly.linkchecker.scala.common.url.typed._

@SuppressWarnings(Array("org.wartremover.warts.Throw"))
final case class WrappedUrl(private val url: String)(implicit private val parent: Option[InternalUrl]) extends Ordered[WrappedUrl] {

  override def toString: String = s"$url $parent"

  override def compare(that: WrappedUrl): Int = url.compareTo(that.url)

  def append(domain: WrappedUrl): WrappedUrl = WrappedUrl(domain.url + url)

  def toURL: URL = new URL(url)

  def toURI: URI = URI.create(url)

  def toException(e: Throwable): UrlException = new UrlException(toString, e)

  def toNonInternalException: NonInternalUrlException = new NonInternalUrlException(toString)

  def toNonRelativeException: NonRelativeUrlException = new NonRelativeUrlException(toString)

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

  def create(url: String)(implicit internalUrl: InternalUrl): WrappedUrl = WrappedUrl(url)(Some(internalUrl))

  def fromUrl(url: String): WrappedUrl = WrappedUrl(url)(Option.empty)
}
