package pl.writeonly.linkchecker.scala.sourcepage.oo

import java.io.IOException
import java.net.URISyntaxException

import pl.writeonly.linkchecker.scala.common.url.typed.InternalUrl
import pl.writeonly.linkchecker.scala.sourcepage.SourcePage

object SourcePageOrThrowExceptionFromInternalUrl extends InternalUrlToSourcePage {

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  override def apply(internalUrl: InternalUrl): SourcePage =
    try {
      SourcePageFromInternalUrl(internalUrl)
    } catch {
      case e: RuntimeException   => throw internalUrl.toException(e)
      case e: IOException        => throw internalUrl.toException(e)
      case e: URISyntaxException => throw internalUrl.toException(e)
    }
}
