package pl.writeonly.linkchecker.scala.common.url.exception

class UrlException(url: String, e: Throwable) extends IllegalArgumentException(url, e)
