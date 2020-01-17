package pl.writeonly.linkchecker.scala.impl.thirdparty.thirdparty1

import pl.writeonly.linkchecker.scala.common.states.notexception.AbstractNextState

object ValidationApp {

  private val domain = "https://www.writeonly.pl"

  def main(args: Array[String]): Unit =
    apply().showResult()

  def apply(): AbstractNextState = ValidationState(domain)
}
