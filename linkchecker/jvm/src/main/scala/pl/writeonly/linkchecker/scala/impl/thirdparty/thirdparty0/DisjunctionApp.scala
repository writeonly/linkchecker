package pl.writeonly.linkchecker.scala.impl.thirdparty.thirdparty0

import pl.writeonly.linkchecker.scala.common.states.notexception.AbstractNextState

object DisjunctionApp {

  private val domain = "https://www.writeonly.pl"

  def main(args: Array[String]): Unit =
    apply().showResult()

  def apply(): AbstractNextState = DisjunctionState(domain)
}
