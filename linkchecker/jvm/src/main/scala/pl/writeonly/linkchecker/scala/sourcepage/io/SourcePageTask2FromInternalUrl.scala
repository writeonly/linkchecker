package pl.writeonly.linkchecker.scala.sourcepage.io

import pl.writeonly.linkchecker.scala.common.url.typed.InternalUrl
import pl.writeonly.linkchecker.scala.sourcepage.monad.SourcePageValidationFromInternalUrl
import scalaz.Scalaz._
import scalaz.ioeffect._

object SourcePageTask2FromInternalUrl {

  def apply(internalUrl: InternalUrl): SourcePageTask2 =
    IO.syncThrowable(internalUrl |> SourcePageValidationFromInternalUrl.apply)
}
