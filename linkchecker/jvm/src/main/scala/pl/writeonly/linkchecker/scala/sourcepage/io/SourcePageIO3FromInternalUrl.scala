package pl.writeonly.linkchecker.scala.sourcepage.io

import pl.writeonly.linkchecker.scala.common.url.typed.InternalUrl
import pl.writeonly.linkchecker.scala.sourcepage.monad.SourcePageValidationFromInternalUrl
import scalaz.Scalaz._
import zio.IO

object SourcePageIO3FromInternalUrl {

  def apply(internalUrl: InternalUrl): SourcePageIO3 =
    IO.effect(internalUrl |> SourcePageValidationFromInternalUrl.apply)
}
