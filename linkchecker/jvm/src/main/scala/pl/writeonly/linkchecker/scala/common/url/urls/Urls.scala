package pl.writeonly.linkchecker.scala.common.url.urls

import scala.collection.immutable._

import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.common.url.typed._
import slogging.LazyLogging

class Urls(count: Int, val externalUrls: ExternalUrls, val internalUrls: InternalUrls, val nextInternalUrls: InternalUrls) {

  def next(newUrls: NewUrls): Urls =
    new Urls(
      count + 1,
      externalUrls ++ newUrls.newExternalUrls,
      internalUrls ++ nextInternalUrls,
      SortedSet(newUrls.newInternalUrls.filter(isNewInternalUrl).toSeq: _*)
    )

  def showStep(): Unit = {
    Urls.showHeader("count: " + count.toString)
    Urls.showPart(details = false, "externalUrls", externalUrls)
    Urls.showPart(details = false, "internalUrls", internalUrls)
    Urls.showPart(details = false, "nextInternalUrls", nextInternalUrls)
    nextInternalUrls.foreach(Urls.showString)
  }

  def showResult(): Unit = {
    Urls.showPart(details = true, "externalUrls", externalUrls)
    Urls.showPart(details = true, "internalUrls", internalUrls)
    Urls.showPart(details = true, "nextInternalUrls", nextInternalUrls)
  }

  private def isNewInternalUrl(internalUrl: InternalUrl): Boolean =
    !internalUrls.contains(internalUrl) && !nextInternalUrls.contains(internalUrl)

  def isEmptyNextInternalUrls: Boolean = nextInternalUrls.isEmpty
}

object Urls extends LazyLogging {

  def showPartThrowableList(details: Boolean, x: String, xs: Iterable[Throwable]): Unit = {
    showHeader(x + " size: " + xs.size.toString)
    if (details) xs.foreach(x => logger.error(x.getMessage, x.getCause))
  }

  def showPart(details: Boolean, x: String, xs: Iterable[AnyRef]): Unit = {
    showHeader(x + " size: " + xs.size.toString)
    if (details) xs.foreach(showString)
  }

  private def showHeader(x: String): Unit = {
    (1 to 80).foreach(_ => print("'"))
    println("")
    println(x)
  }

  private def showString(x: AnyRef): Unit = println(x)

  def fromDomain(implicit d: Domain): Urls =
    new Urls(0, SortedSet.empty, SortedSet.empty, SortedSet(d.toInternalUrl))
}
