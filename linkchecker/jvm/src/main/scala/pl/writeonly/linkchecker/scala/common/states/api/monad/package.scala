package pl.writeonly.linkchecker.scala.common.states.api

import scalaz.concurrent.Task

package object monad {
  type ParallelStateTask = Task[ValidationAPIState]
}
