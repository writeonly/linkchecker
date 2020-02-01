package pl.writeonly.linkchecker.scala.sourcepage.io

import pl.writeonly.linkchecker.scala.common.url.typed.InternalUrl
import pl.writeonly.linkchecker.scala.sourcepage.monad.SourcePageValidationFromInternalUrl
import scalaz.Scalaz._
import zio._

object SourcePageUIO3FromInternalUrl {

  def apply(internalUrl: InternalUrl): SourcePageUIO3 =
    UIO.effectTotal(internalUrl |> SourcePageValidationFromInternalUrl.apply)
}
