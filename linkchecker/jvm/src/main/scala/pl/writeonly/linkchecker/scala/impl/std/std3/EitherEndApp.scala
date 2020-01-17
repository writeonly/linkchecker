package pl.writeonly.linkchecker.scala.impl.std.std3

import scala.concurrent.ExecutionContext.Implicits.global

import pl.writeonly.linkchecker.scala.common.states.notexception.AbstractNextState

object EitherEndApp {

  private val domain = "https://www.writeonly.pl"

  def main(args: Array[String]): Unit = apply().showResult()

  def apply(): AbstractNextState = EitherEndState(domain)

}
