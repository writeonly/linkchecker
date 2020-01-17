package pl.writeonly.linkchecker.scala.common.states.api

import scalaz.concurrent.Task

package object thirdparty {
  type ParallelStateTask = Task[ValidationAPIState]
}
