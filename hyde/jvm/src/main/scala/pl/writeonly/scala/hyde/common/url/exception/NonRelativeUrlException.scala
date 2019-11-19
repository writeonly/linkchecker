package pl.writeonly.scala.hyde.common.url.exception

class NonRelativeUrlException(url: String) extends IllegalArgumentException(url)
