package pl.writeonly.linkchecker.scala.impl.thirdparty.thirdparty4

import pl.writeonly.linkchecker.scala.common.states.api.thirdparty._

object TaskApp {

  private val domain = "https://www.writeonly.pl"

  def main(args: Array[String]): Unit = apply().showResult()

  def apply(): ValidationAPIState = applyTask().unsafePerformSync

  def applyTask(): ParallelStateTask = TaskState(domain)

}
