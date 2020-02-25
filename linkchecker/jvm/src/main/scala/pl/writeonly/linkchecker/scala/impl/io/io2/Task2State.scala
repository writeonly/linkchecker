package pl.writeonly.linkchecker.scala.impl.io.io2

import pl.writeonly.linkchecker.scala.common.states.api.io.PureStateTask2
import pl.writeonly.linkchecker.scala.common.states.api.monad.ValidationAPIState
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.common.url.urls.UrlsWithThrowableList
import pl.writeonly.linkchecker.scala.sourcepage.io.SourcePageIO2FromInternalUrl
import pl.writeonly.linkchecker.scala.sourcepage.monad._
import scalaz.ioeffect.Task

object Task2State {

  def fromDomain(implicit d: Domain): Task2State = new Task2State(UrlsWithThrowableList.fromDomain)

  def run(state: Task2State): PureStateTask2 =
    if (state.isEmptyNextInternalUrls) Task.now(state) else state.nextMonad.flatMap(run)

  private def sequence(set: Set[Task[SourcePageValidation]]): Task[SourcePageValidationSet] =
    set.foldLeft(Task.point[SourcePageValidationSet](Set.empty)) { (b: Task[SourcePageValidationSet], a: Task[SourcePageValidation]) =>
      b.flatMap(bb => a.map(aa => bb + aa))
    }
}

class Task2State(data: UrlsWithThrowableList)(implicit d: Domain) extends ValidationAPIState(data) {

  override type NextState = Task2State

  def nextState(data: UrlsWithThrowableList): Task2State = new Task2State(data)

  def nextMonad: Task[Task2State] = {

    val set: Set[Task[SourcePageValidation]] = data.nextUrls
      .map(SourcePageIO2FromInternalUrl.apply)
    val monad: Task[SourcePageValidationSet] = Task2State.sequence(set)

    monad.map(newState)
  }
}
