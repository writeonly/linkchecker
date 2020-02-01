package pl.writeonly.linkchecker.scala.sourcepage.io

import pl.writeonly.linkchecker.scala.common.url.typed.InternalUrl
import pl.writeonly.linkchecker.scala.sourcepage.monad.SourcePageValidationFromInternalUrl
import scalaz.Scalaz._
import scalaz.effect.IO

object SourcePageIOFromInternalUrl {

  def apply(internalUrl: InternalUrl): SourcePageIO =
    IO(internalUrl |> SourcePageValidationFromInternalUrl.apply)
}
