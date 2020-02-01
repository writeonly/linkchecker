package pl.writeonly.linkchecker.scala.impl.monad.monad4

import pl.writeonly.linkchecker.scala.common.states.api.monad._
import pl.writeonly.linkchecker.scala.impl.Apps.ValidationAPIStateEffect

object TaskApp extends ValidationAPIStateEffect[ParallelStateTask] {

  private val domain = "https://www.writeonly.pl"

  def main(args: Array[String]): Unit = apply().showResult()

  def apply(): ValidationAPIState = effect().unsafePerformSync

  def effect(): ParallelStateTask = TaskState(domain)

}
