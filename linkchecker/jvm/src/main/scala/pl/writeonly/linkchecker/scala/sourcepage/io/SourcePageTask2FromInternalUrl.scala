package pl.writeonly.linkchecker.scala.sourcepage.io

import pl.writeonly.linkchecker.scala.common.url.typed.InternalUrl
import pl.writeonly.linkchecker.scala.sourcepage.monad._
import scalaz.ioeffect._
import scalaz.ioeffect.ExitResult.Completed

object SourcePageTask2FromInternalUrl {

  def apply(internalUrl: InternalUrl): SourcePageTask2 =
    IO.async(cb => cb(Completed[Throwable, SourcePageValidation](SourcePageValidationFromInternalUrl.apply(internalUrl))))
}
