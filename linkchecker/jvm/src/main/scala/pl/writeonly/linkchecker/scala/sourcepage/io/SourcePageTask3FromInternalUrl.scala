package pl.writeonly.linkchecker.scala.sourcepage.io

import pl.writeonly.linkchecker.scala.common.url.typed.InternalUrl
import pl.writeonly.linkchecker.scala.sourcepage.monad.SourcePageValidationFromInternalUrl
import zio.Task

object SourcePageTask3FromInternalUrl {

  def apply(internalUrl: InternalUrl): SourcePageTask3 =
    Task.effectAsync(io => io(Task.effect(SourcePageValidationFromInternalUrl.apply(internalUrl))))
}
