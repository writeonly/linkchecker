package pl.writeonly.scala.hyde.sourcepage.std

import scala.concurrent._

import pl.writeonly.scala.hyde.common.url.typed.InternalUrl
import scalaz.Scalaz._

object SourcePageFutureFromInternalUrl {

  def apply(internalUrl: InternalUrl)(implicit ec: ExecutionContext): SourcePageFuture =
    Future { internalUrl |> SourcePageEitherFromInternalUrl.apply }
}
