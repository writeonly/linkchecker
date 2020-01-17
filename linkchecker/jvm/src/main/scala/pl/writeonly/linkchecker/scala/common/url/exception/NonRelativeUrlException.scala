package pl.writeonly.linkchecker.scala.common.url.exception

class NonRelativeUrlException(url: String) extends IllegalArgumentException(url)
