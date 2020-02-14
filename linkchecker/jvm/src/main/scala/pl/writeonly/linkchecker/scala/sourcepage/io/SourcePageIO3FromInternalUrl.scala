package pl.writeonly.linkchecker.scala.sourcepage.io

import pl.writeonly.linkchecker.scala.common.url.typed.InternalUrl
import pl.writeonly.linkchecker.scala.sourcepage.monad.SourcePageValidationFromInternalUrl
import zio.IO

object SourcePageIO3FromInternalUrl {

  def apply(internalUrl: InternalUrl): SourcePageIO3 =
    IO.effectAsync(io => io(IO.effect(SourcePageValidationFromInternalUrl.apply(internalUrl))))
}
