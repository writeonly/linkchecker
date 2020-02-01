package pl.writeonly.linkchecker.scala.sourcepage.io

import pl.writeonly.linkchecker.scala.common.url.typed.InternalUrl
import pl.writeonly.linkchecker.scala.sourcepage.monad.SourcePageValidationFromInternalUrl
import scalaz.Scalaz._
import scalaz.ioeffect.IO

object SourcePageIO2FromInternalUrl {

  def apply(internalUrl: InternalUrl): SourcePageIO2 =
    IO.sync(internalUrl |> SourcePageValidationFromInternalUrl.apply)
}
