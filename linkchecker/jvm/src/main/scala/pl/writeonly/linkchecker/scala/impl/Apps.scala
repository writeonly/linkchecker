package pl.writeonly.linkchecker.scala.impl

import pl.writeonly.linkchecker.scala.common.states.api.monad.ValidationAPIState
import pl.writeonly.linkchecker.scala.common.states.notexception.AbstractNextState
import pl.writeonly.linkchecker.scala.common.states.oo.AbstractOOState

object Apps {

  trait Apply[RESULT] extends (() => RESULT)

  type AbstractOOStateApply = Apply[AbstractOOState]
  type AbstractNextStateApply = Apply[AbstractNextState]

  trait Effect[RESULT, EFFECT] extends Apply[RESULT] {
    def effect(): EFFECT
  }

  type ValidationAPIStateEffect[EFFECT] = Effect[ValidationAPIState, EFFECT]
}
