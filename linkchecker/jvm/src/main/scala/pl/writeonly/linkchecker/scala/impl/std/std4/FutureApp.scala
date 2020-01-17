package pl.writeonly.linkchecker.scala.impl.std.std4

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

import pl.writeonly.linkchecker.scala.common.states.api.std._

object FutureApp {

  private val domain = "https://www.writeonly.pl"

  def main(args: Array[String]): Unit = apply().showResult()

  def apply(): EitherAPIState = Await.result(applyFuture(), 1.minute)

  def applyFuture(): ParallelStateFuture = FutureState(domain)

}
