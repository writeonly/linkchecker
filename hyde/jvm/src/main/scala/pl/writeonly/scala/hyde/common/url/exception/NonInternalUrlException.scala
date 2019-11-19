package pl.writeonly.scala.hyde.common.url.exception

class NonInternalUrlException(url: String) extends IllegalArgumentException(url)
