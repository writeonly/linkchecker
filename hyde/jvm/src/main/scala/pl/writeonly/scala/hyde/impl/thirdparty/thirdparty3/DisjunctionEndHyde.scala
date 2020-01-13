package pl.writeonly.scala.hyde.impl.thirdparty.thirdparty3

import pl.writeonly.scala.hyde.common.states.notexception.AbstractNextState

object DisjunctionEndHyde {

  private val domain = "https://www.writeonly.pl"

  def main(args: Array[String]): Unit = apply().showResult()

  def apply(): AbstractNextState = DisjunctionEndState(domain)

}
