package pl.writeonly.linkchecker.scala.common.url.typed

import pl.writeonly.linkchecker.scala.common.url._

final class RelativeUrl private (val url: WrappedUrl) {
  def toInternalUrl(implicit d: Domain): InternalUrl = InternalUrl(url)

  override def toString: String = url.toString
}

object RelativeUrl {

  def isRelativeUrl(url: WrappedUrl): Boolean = url.isRelativeUrl

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def apply(url: WrappedUrl): RelativeUrl =
    if (url.isRelativeUrl) new RelativeUrl(url) else throw url.toNonRelativeException

}
