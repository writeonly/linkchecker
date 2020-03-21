package pl.writeonly.linkchecker.scala.common.url.exception

class UrlException(message: String, e: Throwable) extends IllegalArgumentException(message, e)
