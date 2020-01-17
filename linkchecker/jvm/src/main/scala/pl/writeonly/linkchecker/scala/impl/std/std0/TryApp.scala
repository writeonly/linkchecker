package pl.writeonly.linkchecker.scala.impl.std.std0

import pl.writeonly.linkchecker.scala.common.states.notexception.AbstractNextState

object TryApp {

  private val domain = "https://www.writeonly.pl"

  def main(args: Array[String]): Unit = apply().showResult()

  def apply(): AbstractNextState = TryState(domain)
}
