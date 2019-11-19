package pl.writeonly.scala.hyde.common.url

import scala.collection.immutable.SortedSet

package object typed {
  type RelationUrls = SortedSet[RelativeUrl]
  type InternalUrls = SortedSet[InternalUrl]
  type ExternalUrls = SortedSet[ExternalUrl]

  type RelationUrlSet = Set[RelativeUrl]
  type InternalUrlSet = Set[InternalUrl]
  type ExternalUrlSet = Set[ExternalUrl]
}
