package pl.writeonly.scala.hyde.impl.thirdparty.thirdparty1

import pl.writeonly.scala.hyde.common.states.notexception.AbstractNextState

object DisjunctionHyde {

  private val domain = "https://www.writeonly.pl"

  def main(args: Array[String]): Unit =
    apply().showResult()

  def apply(): AbstractNextState = DisjunctionState(domain)
}
