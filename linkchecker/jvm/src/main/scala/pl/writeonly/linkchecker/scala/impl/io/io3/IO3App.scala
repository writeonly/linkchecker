package pl.writeonly.linkchecker.scala.impl.io.io3

import pl.writeonly.linkchecker.scala.common.states.api.monad.ValidationAPIState
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.impl.Apps.ValidationAPIStateEffect
import scalaz.Scalaz._
import zio._

object IO3App extends App with ValidationAPIStateEffect[IO[Throwable, ValidationAPIState]] {

  private val domain = "https://writeonly.pl"

  @SuppressWarnings(Array("org.wartremover.warts.Any"))
  override def run(args: List[String]): UIO[Int] = effect().orDie.as(0)

  def apply(): ValidationAPIState = unsafeRun(effect())

  def effect(): IO[Throwable, ValidationAPIState] = IO3State.fromDomain(new Domain(domain)) |> IO3State.run
}
