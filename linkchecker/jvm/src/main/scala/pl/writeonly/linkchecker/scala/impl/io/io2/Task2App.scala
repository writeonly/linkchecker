package pl.writeonly.linkchecker.scala.impl.io.io2

import pl.writeonly.linkchecker.scala.common.states.api.monad.ValidationAPIState
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.impl.Apps.ValidationAPIStateEffect
import scalaz.Scalaz._
import scalaz.ioeffect._

object Task2App extends SafeApp with ValidationAPIStateEffect[Task[ValidationAPIState]] {

  private val domain = "https://writeonly.pl"

  override def run(args: List[String]): IO[Void, ExitStatus] =
    effect().attempt.map(_ => ExitStatus.DoNotExit)

  def apply(): ValidationAPIState = unsafePerformIO(effect())

  def effect(): Task[ValidationAPIState] = Task2State.fromDomain(new Domain(domain)) |> Task2State.run
}
