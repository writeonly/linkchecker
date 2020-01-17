package pl.writeonly.linkchecker.scala

import pl.writeonly.linkchecker.scala.common.url.typed.InternalUrl

package object sourcepage {
  type InternalUrlTo[A] = InternalUrl => A
}
