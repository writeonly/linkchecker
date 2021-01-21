package pl.writeonly.linkchecker.scala.impl.std.std3

import scala.concurrent.ExecutionContext.Implicits.global

import pl.writeonly.linkchecker.scala.common.states.notexception.AbstractNextState
import pl.writeonly.linkchecker.scala.impl.Apps.AbstractNextStateApply

object EitherEndApp extends AbstractNextStateApply {

  private val domain = "https://writeonly.pl"

  def main(args: Array[String]): Unit = apply().showResult()

  def apply(): AbstractNextState = EitherEndState(domain)

}
