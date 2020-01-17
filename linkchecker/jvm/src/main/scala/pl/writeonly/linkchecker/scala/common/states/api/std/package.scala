package pl.writeonly.linkchecker.scala.common.states.api

import scala.concurrent.Future

package object std {
  type ParallelStateFuture = Future[EitherAPIState]
}
