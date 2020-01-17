package pl.writeonly.linkchecker.scala.impl.std.std1

import pl.writeonly.linkchecker.scala.common.states.notexception.AbstractNextState

object EitherApp {

  private val domain = "https://www.writeonly.pl"

  def main(args: Array[String]): Unit = apply().showResult()

  def apply(): AbstractNextState = EitherState(domain)
}
