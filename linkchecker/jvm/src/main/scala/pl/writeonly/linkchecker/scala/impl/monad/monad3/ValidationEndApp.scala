package pl.writeonly.linkchecker.scala.impl.monad.monad3

import pl.writeonly.linkchecker.scala.common.states.notexception.AbstractNextState
import pl.writeonly.linkchecker.scala.impl.Apps.AbstractNextStateApply

object ValidationEndApp extends AbstractNextStateApply {

  private val domain = "https://www.writeonly.pl"

  def main(args: Array[String]): Unit = apply().showResult()

  def apply(): AbstractNextState = ValidationEndState(domain)

}
