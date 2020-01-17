package pl.writeonly.linkchecker.scala.impl.thirdparty.thirdparty3

import pl.writeonly.linkchecker.scala.common.states.notexception.AbstractNextState

object ValidationEndApp {

  private val domain = "https://www.writeonly.pl"

  def main(args: Array[String]): Unit = apply().showResult()

  def apply(): AbstractNextState = ValidationEndState(domain)

}