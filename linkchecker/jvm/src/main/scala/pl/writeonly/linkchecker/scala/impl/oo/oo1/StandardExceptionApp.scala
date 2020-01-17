package pl.writeonly.linkchecker.scala.impl.oo.oo1

import pl.writeonly.linkchecker.scala.common.states.oo.AbstractOOState

object StandardExceptionApp {

  private val domain = "https://www.writeonly.pl"

  def main(args: Array[String]): Unit = apply().showResult()

  def apply(): AbstractOOState = StandardExceptionState(domain)
}
