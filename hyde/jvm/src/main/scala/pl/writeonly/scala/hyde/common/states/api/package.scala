package pl.writeonly.scala.hyde.common.states

import scala.concurrent.Future

import scalaz.concurrent.Task

package object api {
  type ParallelStateFuture = Future[EitherAPIState]

  type ParallelStateTask = Task[DisjunctionAPIState]

}
