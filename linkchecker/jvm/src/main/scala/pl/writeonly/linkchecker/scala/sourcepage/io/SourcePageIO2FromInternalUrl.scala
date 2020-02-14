package pl.writeonly.linkchecker.scala.sourcepage.io

import pl.writeonly.linkchecker.scala.common.url.typed.InternalUrl
import pl.writeonly.linkchecker.scala.sourcepage.monad._
import scalaz.ioeffect.ExitResult.Completed
import scalaz.ioeffect.IO

object SourcePageIO2FromInternalUrl {

  def apply(internalUrl: InternalUrl): SourcePageIO2 =
    IO.async(cb => cb(Completed[Throwable, SourcePageValidation](SourcePageValidationFromInternalUrl.apply(internalUrl))))
}
