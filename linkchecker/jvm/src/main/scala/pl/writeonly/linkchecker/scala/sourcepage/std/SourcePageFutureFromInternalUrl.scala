package pl.writeonly.linkchecker.scala.sourcepage.std

import scala.concurrent._

import pl.writeonly.linkchecker.scala.common.url.typed.InternalUrl
import scalaz.Scalaz._

object SourcePageFutureFromInternalUrl {

  def apply(internalUrl: InternalUrl)(implicit ec: ExecutionContext): SourcePageFuture =
    Future { internalUrl |> SourcePageEitherFromInternalUrl.apply }
}
