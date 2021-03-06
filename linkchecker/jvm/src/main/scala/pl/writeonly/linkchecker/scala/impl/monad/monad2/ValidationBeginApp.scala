package pl.writeonly.linkchecker.scala.impl.monad.monad2

import pl.writeonly.linkchecker.scala.common.states.notexception.AbstractNextState
import pl.writeonly.linkchecker.scala.impl.Apps.AbstractNextStateApply

object ValidationBeginApp extends AbstractNextStateApply {

  private val domain = "https://writeonly.pl"

  def main(args: Array[String]): Unit = apply().showResult()

  def apply(): AbstractNextState = ValidationBeginState(domain)

}
