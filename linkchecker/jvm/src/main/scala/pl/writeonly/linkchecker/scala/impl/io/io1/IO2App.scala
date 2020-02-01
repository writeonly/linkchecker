package pl.writeonly.linkchecker.scala.impl.io.io1

import pl.writeonly.linkchecker.scala.common.states.api.io.ParallelStateIO2
import pl.writeonly.linkchecker.scala.common.states.api.monad.ValidationAPIState
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.impl.Apps.ValidationAPIStateEffect
import scalaz.Scalaz._
import scalaz.ioeffect._

object IO2App extends SafeApp with ValidationAPIStateEffect[ParallelStateIO2] {

  private val domain = "https://www.writeonly.pl"

  override def run(args: List[String]): IO[Void, ExitStatus] =
    effect().attempt.map(_ => ExitStatus.DoNotExit)

  def apply(): ValidationAPIState = unsafePerformIO(effect())

  def effect(): ParallelStateIO2 = IO2State.fromDomain(new Domain(domain)) |> IO2State.run
}
