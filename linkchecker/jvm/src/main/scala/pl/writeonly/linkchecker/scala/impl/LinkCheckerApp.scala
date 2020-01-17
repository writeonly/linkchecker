package pl.writeonly.linkchecker.scala.impl

import java.text.NumberFormat

import scala.util.Try

import pl.writeonly.linkchecker.scala.impl.oo.oo1.StandardExceptionApp
import pl.writeonly.linkchecker.scala.impl.oo.oo2.WrappingExceptionApp
import pl.writeonly.linkchecker.scala.impl.std.std0.TryApp
import pl.writeonly.linkchecker.scala.impl.std.std1.EitherApp
import pl.writeonly.linkchecker.scala.impl.std.std2.EitherBeginApp
import pl.writeonly.linkchecker.scala.impl.std.std3.EitherEndApp
import pl.writeonly.linkchecker.scala.impl.std.std4.FutureApp
import pl.writeonly.linkchecker.scala.impl.thirdparty.thirdparty0.DisjunctionApp
import pl.writeonly.linkchecker.scala.impl.thirdparty.thirdparty1.ValidationApp
import pl.writeonly.linkchecker.scala.impl.thirdparty.thirdparty2.ValidationBeginApp
import pl.writeonly.linkchecker.scala.impl.thirdparty.thirdparty3.ValidationEndApp
import pl.writeonly.linkchecker.scala.impl.thirdparty.thirdparty4.TaskApp
import slogging._

object LinkCheckerApp {

  LoggerConfig.factory = SLF4JLoggerFactory()
  LoggerConfig.level = LogLevel.TRACE

  def main(implicit args: Array[String]): Unit = {

    oo

    std()

    thirdParty()

    println("end of program")
  }

  def oo(implicit args: Array[String]): Unit = {
    println("StandardExceptionApp")
    val standard = Try(StandardExceptionApp.main(args))
    println("StandardExceptionApp " + standard.toString)

    println("WrappingExceptionApp")
    val wrapping = Try(WrappingExceptionApp.main(args))
    println("WrappingExceptionApp " + wrapping.toString)

  }

  def std(): Unit = {

    val tryApp = time("TryApp") {
      TryApp.apply()
    }
    println(tryApp.throwableList.size.toString)

    val eitherState = time("EitherApp") {
      EitherApp.apply()
    }
    println(eitherState.throwableList.size.toString)

    val eitherBeginState = time("EitherBeginApp") {
      EitherBeginApp.apply()
    }
    println(eitherBeginState.throwableList.size.toString)

    val eitherEndState = time("EitherEndApp") {
      EitherEndApp.apply()
    }
    println(eitherEndState.throwableList.size.toString)

    val futureState = time("FutureApp") {
      FutureApp.apply()
    }
    println(futureState.throwableList.size.toString)
  }

  def thirdParty(): Unit = {
    val disjunctionState = time("DisjunctionApp") {
      DisjunctionApp.apply()
    }
    println(disjunctionState.throwableList.size.toString)

    val validationState = time("ValidationApp") {
      ValidationApp.apply()
    }
    println(validationState.throwableList.size.toString)

    val disjunctionBeginState = time("ValidationBeginApp") {
      ValidationBeginApp.apply()
    }
    println(disjunctionBeginState.throwableList.size.toString)

    val disjunctionEndState = time("ValidationEndApp") {
      ValidationEndApp.apply()
    }
    println(disjunctionEndState.throwableList.size.toString)

    val taskState = time("TaskApp") {
      TaskApp.apply()
    }
    println(taskState.throwableList.size.toString)
  }

  def time[R](name: String)(block: => R): R = {
    println(name)

    val startTime = System.nanoTime()
    val result = block
    val stopTime = System.nanoTime()

    val t = stopTime - startTime

    val formatter = NumberFormat.getIntegerInstance

    println("Elapsed time: " + formatter.format(t) + "ns")
    result
  }
}
