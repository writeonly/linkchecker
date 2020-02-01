package pl.writeonly.linkchecker.scala.impl.monad.monad2

import pl.writeonly.linkchecker.scala.common.states.api.monad.ValidationAPIState
import pl.writeonly.linkchecker.scala.common.states.notexception._
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.common.url.urls.UrlsWithThrowableList
import pl.writeonly.linkchecker.scala.sourcepage.monad._
import scalaz.Scalaz._
import scalaz.concurrent.Task

object ValidationBeginState {

  def apply(domain: String): AbstractNextState = fromDomain(new Domain(domain)) |> AbstractNextState.run

  def fromDomain(implicit d: Domain): AbstractNextState =
    new ValidationBeginState(UrlsWithThrowableList.fromDomain)

}

class ValidationBeginState(data: UrlsWithThrowableList)(implicit d: Domain) extends ValidationAPIState(data) with AbstractNewSetState {

  override def newSet: SourcePageValidationSet = {

    val set: Set[Task[SourcePageValidation]] = nextUrls
      .map(SourcePageTaskFromInternalUrl.apply)

    val monad: Task[SourcePageValidationSet] = Task.gatherUnordered(set.toSeq).map(_.toSet)

    monad.unsafePerformSync
  }

  override def nextState(data: UrlsWithThrowableList): AbstractNextState = new ValidationBeginState(data)
}
