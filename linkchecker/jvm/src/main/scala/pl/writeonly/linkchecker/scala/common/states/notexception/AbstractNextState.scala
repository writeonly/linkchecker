package pl.writeonly.linkchecker.scala.common.states.notexception

import scala.annotation.tailrec

import pl.writeonly.linkchecker.scala.common.states.api.AbstractAPIState

trait AbstractNextState extends AbstractAPIState {

  def next: NextState

  type NextState = AbstractNextState
}

object AbstractNextState {
  @tailrec
  def run(state: AbstractNextState): AbstractNextState = if (state.isEmptyNextInternalUrls) state else run(state.next)
}
