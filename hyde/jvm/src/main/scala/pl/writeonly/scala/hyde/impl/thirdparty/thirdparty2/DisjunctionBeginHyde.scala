package pl.writeonly.scala.hyde.impl.thirdparty.thirdparty2

import pl.writeonly.scala.hyde.common.states.notexception.AbstractNextState

object DisjunctionBeginHyde {

  private val domain = "https://www.writeonly.pl"

  def main(args: Array[String]): Unit = apply().showResult()

  def apply(): AbstractNextState = DisjunctionBeginState(domain)

}
