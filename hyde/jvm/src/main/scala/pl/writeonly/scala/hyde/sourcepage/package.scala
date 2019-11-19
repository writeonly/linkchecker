package pl.writeonly.scala.hyde

import pl.writeonly.scala.hyde.common.url.typed.InternalUrl

package object sourcepage {
  type InternalUrlTo[A] = InternalUrl => A
}
