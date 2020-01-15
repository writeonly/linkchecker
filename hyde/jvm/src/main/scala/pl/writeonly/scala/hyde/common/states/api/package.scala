package pl.writeonly.scala.hyde.common.states

import scala.concurrent.Future

package object api {
  type ParallelStateFuture = Future[EitherAPIState]
}
