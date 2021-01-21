package pl.writeonly.linkchecker.scala.impl.std.std0

import pl.writeonly.linkchecker.scala.common.states.notexception.AbstractNextState
import pl.writeonly.linkchecker.scala.impl.Apps.AbstractNextStateApply

object TryApp extends AbstractNextStateApply {

  private val domain = "https://writeonly.pl"

  def main(args: Array[String]): Unit = apply().showResult()

  def apply(): AbstractNextState = TryState(domain)
}
