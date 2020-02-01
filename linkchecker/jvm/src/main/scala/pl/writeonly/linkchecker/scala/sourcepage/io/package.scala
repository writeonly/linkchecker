package pl.writeonly.linkchecker.scala.sourcepage

import pl.writeonly.linkchecker.scala.sourcepage.monad.SourcePageValidation
import scalaz.effect.IO
import scalaz.ioeffect.{IO => IO2, Task => Task2}
import zio.{IO => IO3, Task => Task3, UIO => UIO3}

package object io {
  type SourcePageIO = IO[SourcePageValidation]

  type SourcePageIO2 = IO2[Throwable, SourcePageValidation]

  type SourcePageTask2 = Task2[SourcePageValidation]

  type SourcePageIO3 = IO3[Throwable, SourcePageValidation]

  type SourcePageTask3 = Task3[SourcePageValidation]

  type SourcePageUIO3 = UIO3[SourcePageValidation]
}
