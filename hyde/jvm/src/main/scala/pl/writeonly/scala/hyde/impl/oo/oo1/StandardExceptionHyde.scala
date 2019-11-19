package pl.writeonly.scala.hyde.impl.oo.oo1

import pl.writeonly.scala.hyde.common.states.oo.AbstractOOState

object StandardExceptionHyde {

  private val domain = "https://www.writeonly.pl"

  def main(args: Array[String]): Unit = apply().showResult()

  def apply(): AbstractOOState = StandardExceptionState(domain)
}
