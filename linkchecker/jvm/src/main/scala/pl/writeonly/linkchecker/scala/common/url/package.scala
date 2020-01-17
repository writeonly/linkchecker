package pl.writeonly.linkchecker.scala.common

import scala.collection.immutable.SortedSet

package object url {
  type WrappedUrls = SortedSet[WrappedUrl]

  type WrappedUrlSet = Set[WrappedUrl]
}
