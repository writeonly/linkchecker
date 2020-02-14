package pl.writeonly.linkchecker.scala.impl.io.io6

import pl.writeonly.linkchecker.scala.common.states.api.io.ParallelStateUIO3
import pl.writeonly.linkchecker.scala.common.states.api.monad.ValidationAPIState
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.impl.Apps.ValidationAPIStateEffect
import scalaz.Scalaz._
import zio._

object UIO3App extends App with ValidationAPIStateEffect[ParallelStateUIO3] {

  private val domain = "https://www.writeonly.pl"

  @SuppressWarnings(Array("org.wartremover.warts.Any"))
  override def run(args: List[String]): UIO[Int] = effect().as(0)

  def apply(): ValidationAPIState = unsafeRun(effect())

  def effect(): ParallelStateUIO3 = UIO3State.fromDomain(new Domain(domain)) |> UIO3State.run
}
