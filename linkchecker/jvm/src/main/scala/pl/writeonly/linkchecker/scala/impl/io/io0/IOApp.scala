package pl.writeonly.linkchecker.scala.impl.io.io0

import pl.writeonly.linkchecker.scala.common.states.api.monad.ValidationAPIState
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.impl.Apps.ValidationAPIStateEffect
import scalaz.ImmutableArray
import scalaz.Scalaz._
import scalaz.effect._

object IOApp extends SafeApp with ValidationAPIStateEffect[IO[ValidationAPIState]] {

  private val domain = "https://writeonly.pl"

  override def run(args: ImmutableArray[String]): IO[Unit] = effect().map(_.showResult())

  def apply(): ValidationAPIState = effect().unsafePerformIO()

  def effect(): IO[ValidationAPIState] = IOState.fromDomain(new Domain(domain)) |> IOState.run
}
