package pl.writeonly.scala.hyde.common.url.exception

class UrlException(url: String, e: Throwable) extends IllegalArgumentException(url, e)
