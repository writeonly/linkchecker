package pl.writeonly.scala.hyde.impl

import java.text.NumberFormat

import scala.util.Try

import pl.writeonly.scala.hyde.impl.oo.oo1.StandardExceptionHyde
import pl.writeonly.scala.hyde.impl.oo.oo2.WrappingExceptionHyde
import pl.writeonly.scala.hyde.impl.oo.oo3.TryHyde
import pl.writeonly.scala.hyde.impl.std.std1.EitherHyde
import pl.writeonly.scala.hyde.impl.std.std2.EitherBeginHyde
import pl.writeonly.scala.hyde.impl.std.std3.EitherEndHyde
import pl.writeonly.scala.hyde.impl.std.std4.FutureHyde
import slogging._

object Hyde {

  LoggerConfig.factory = SLF4JLoggerFactory()
  LoggerConfig.level = LogLevel.TRACE

  def main(implicit args: Array[String]): Unit = {

    oo

    std()

    println("end of program")
  }

  def oo(implicit args: Array[String]): Unit = {
    println("StandardExceptionHyde")
    val standard = Try(StandardExceptionHyde.main(args))
    println("StandardExceptionHyde " + standard.toString)

    println("WrappingExceptionHyde")
    val wrapping = Try(WrappingExceptionHyde.main(args))
    println("WrappingExceptionHyde " + wrapping.toString)

    val tryHyde = time("TryHyde") {
      TryHyde.apply()
    }
    println(tryHyde.throwableList.size.toString)
  }

  def std(): Unit = {

    val eitherState = time("EitherHyde") {
      EitherHyde.apply()
    }
    println(eitherState.throwableList.size.toString)

    val eitherBeginState = time("EitherBeginHyde") {
      EitherBeginHyde.apply()
    }
    println(eitherBeginState.throwableList.size.toString)

    val eitherEndState = time("EitherEndHyde") {
      EitherEndHyde.apply()
    }
    println(eitherEndState.throwableList.size.toString)

    val futureState = time("FutureHyde") {
      FutureHyde.apply()
    }
    println(futureState.throwableList.size.toString)
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
