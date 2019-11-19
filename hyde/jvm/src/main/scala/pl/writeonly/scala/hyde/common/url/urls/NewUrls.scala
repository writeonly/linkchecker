package pl.writeonly.scala.hyde.common.url.urls

import pl.writeonly.scala.hyde.common.url._
import pl.writeonly.scala.hyde.common.url.typed._

class NewUrls private (val newExternalUrls: ExternalUrlSet, val newInternalUrls: InternalUrlSet)

object NewUrls {
  type GroupedUrlMap = Map[Symbol, WrappedUrlSet]

  def apply(wrappedUrlSet: WrappedUrlSet)(implicit d: Domain): NewUrls = {
    val grouped: GroupedUrlMap = wrappedUrlSet.groupBy(_.getType)

    val newExternalUrls: ExternalUrlSet = grouped.getOrElse(WrappedUrl.External, Set.empty).map(_.toExternalUrl)

    val relativeUrls1: InternalUrlSet = grouped.getOrElse(WrappedUrl.Relative, Set.empty).map(_.makeInternalUrl)

    val internalUrls: InternalUrlSet = grouped.getOrElse(WrappedUrl.Internal, Set.empty).map(_.toInternalUrl)

    val newInternalUrls: InternalUrlSet = internalUrls ++ relativeUrls1

    new NewUrls(newExternalUrls, newInternalUrls)
  }

}
