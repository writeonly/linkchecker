package pl.writeonly.linkchecker.scala.impl.std.std4

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

import pl.writeonly.linkchecker.scala.common.states.api.std._
import pl.writeonly.linkchecker.scala.impl.Apps.Effect

object FutureApp extends Effect[EitherAPIState, ParallelStateFuture] {

  private val domain = "https://writeonly.pl"

  def main(args: Array[String]): Unit = apply().showResult()

  def apply(): EitherAPIState = Await.result(effect(), 1.minute)

  def effect(): ParallelStateFuture = FutureState(domain)

}
