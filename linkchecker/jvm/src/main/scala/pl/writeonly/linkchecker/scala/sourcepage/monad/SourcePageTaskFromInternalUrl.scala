package pl.writeonly.linkchecker.scala.sourcepage.monad

import pl.writeonly.linkchecker.scala.common.url.typed.InternalUrl
import scalaz.Scalaz._
import scalaz.concurrent.Task

object SourcePageTaskFromInternalUrl {

  def apply(internalUrl: InternalUrl): SourcePageTask =
    Task { internalUrl |> SourcePageValidationFromInternalUrl.apply }
}
