package pl.writeonly.linkchecker.scala.common.url

import pl.writeonly.linkchecker.scala.common.url.typed._

class Domain(domain: WrappedUrl) {

  def this(url: String) = this(WrappedUrl.fromUrl(url))

  println("domain " + toString)

  override def toString: String = domain.toString

  def toInternalUrl: InternalUrl = InternalUrl(domain)(this)

  def toInternalUrl(url: WrappedUrl): InternalUrl = new InternalUrl(url.append(domain))

  def isInternalUrl(url: WrappedUrl): Boolean = url.isInternalUrl(domain)

  def toWrappedUrl: WrappedUrl = domain
}
