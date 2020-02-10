package pl.writeonly.linkchecker.scala.impl

import pl.writeonly.linkchecker.scala.common.states.api.monad.ValidationAPIState
import pl.writeonly.linkchecker.scala.common.states.notexception.AbstractNextState
import pl.writeonly.linkchecker.scala.common.states.oo.AbstractOOState

object Apps {

  trait Apply[A] extends (() => A)

  type AbstractOOStateApply = Apply[AbstractOOState]
  type AbstractNextStateApply = Apply[AbstractNextState]

  trait Effect[A, E] extends Apply[A] {
    def effect(): E
  }

  type ValidationAPIStateEffect[E] = Effect[ValidationAPIState, E]
}
