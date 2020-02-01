package pl.writeonly.linkchecker.scala.impl.oo.oo2

import pl.writeonly.linkchecker.scala.common.states.oo.AbstractOOState
import pl.writeonly.linkchecker.scala.impl.Apps.AbstractOOStateApply

object WrappingExceptionApp extends App with AbstractOOStateApply {

  private val domain = "https://www.writeonly.pl"

  apply().showResult()

  def apply(): AbstractOOState = WrappingExceptionState(domain)
}
