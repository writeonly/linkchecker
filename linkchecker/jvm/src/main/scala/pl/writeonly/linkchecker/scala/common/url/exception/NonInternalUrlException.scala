package pl.writeonly.linkchecker.scala.common.url.exception

class NonInternalUrlException(url: String) extends IllegalArgumentException(url)
