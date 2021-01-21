package pl.writeonly.linkchecker.scala.impl.std.std1

import pl.writeonly.linkchecker.scala.common.states.notexception.AbstractNextState
import pl.writeonly.linkchecker.scala.impl.Apps.AbstractNextStateApply

object EitherApp extends AbstractNextStateApply {

  private val domain = "https://writeonly.pl"

  def main(args: Array[String]): Unit = apply().showResult()

  def apply(): AbstractNextState = EitherState(domain)
}
