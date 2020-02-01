package pl.writeonly.linkchecker.scala.common.states.api

import pl.writeonly.linkchecker.scala.common.states.api.monad.ValidationAPIState
import scalaz.effect.IO
import scalaz.ioeffect.{IO => IO2, Task => Task2}
import zio.{IO => IO3, Task => Task3, UIO => UIO3}

package object io {
  type ParallelStateIO = IO[ValidationAPIState]
  type ParallelStateIO2 = IO2[Throwable, ValidationAPIState]
  type ParallelStateTask2 = Task2[ValidationAPIState]
  type ParallelStateIO3 = IO3[Throwable, ValidationAPIState]
  type ParallelStateTask3 = Task3[ValidationAPIState]
  type ParallelStateUIO3 = UIO3[ValidationAPIState]
}
