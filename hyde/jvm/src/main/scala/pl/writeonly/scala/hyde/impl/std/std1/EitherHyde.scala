package pl.writeonly.scala.hyde.impl.std.std1

import pl.writeonly.scala.hyde.common.states.notexception.AbstractNextState

object EitherHyde {

  private val domain = "https://www.writeonly.pl"

  def main(args: Array[String]): Unit = apply().showResult()

  def apply(): AbstractNextState = EitherState(domain)
}
