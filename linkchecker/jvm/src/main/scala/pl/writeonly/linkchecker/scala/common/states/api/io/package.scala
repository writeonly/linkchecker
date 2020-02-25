package pl.writeonly.linkchecker.scala.common.states.api

import pl.writeonly.linkchecker.scala.common.states.api.monad.ValidationAPIState
import scalaz.effect.IO
import scalaz.ioeffect.{IO => IO2, Task => Task2}
import zio.{IO => IO3, Task => Task3, UIO => UIO3}

package object io {
  type PureStateIO = IO[ValidationAPIState]
  type PureStateIO2 = IO2[Throwable, ValidationAPIState]
  type PureStateTask2 = Task2[ValidationAPIState]
  type PureStateIO3 = IO3[Throwable, ValidationAPIState]
  type PureStateTask3 = Task3[ValidationAPIState]
  type PureStateUIO3 = UIO3[ValidationAPIState]
}
