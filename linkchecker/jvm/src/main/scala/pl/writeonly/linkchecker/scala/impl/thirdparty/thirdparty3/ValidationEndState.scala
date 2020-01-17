package pl.writeonly.linkchecker.scala.impl.thirdparty.thirdparty3

import pl.writeonly.linkchecker.scala.common.states.api.thirdparty.ValidationAPIState
import pl.writeonly.linkchecker.scala.common.states.notexception.AbstractNextState
import pl.writeonly.linkchecker.scala.common.url.Domain
import pl.writeonly.linkchecker.scala.common.url.urls.UrlsWithThrowableList
import pl.writeonly.linkchecker.scala.sourcepage.thirdparty._
import scalaz.Scalaz._
import scalaz.concurrent.Task

object ValidationEndState {

  def apply(domain: String): AbstractNextState = fromDomain(new Domain(domain)) |> AbstractNextState.run

  def fromDomain(implicit d: Domain): AbstractNextState =
    new ValidationEndState(UrlsWithThrowableList.fromDomain)
}

class ValidationEndState(data: UrlsWithThrowableList)(implicit d: Domain) extends ValidationAPIState(data) with AbstractNextState {

  override def next: AbstractNextState = {

    val set: Set[Task[SourcePageValidation]] = nextUrls
      .map(SourcePageTaskFromInternalUrl.apply)

    val monad: Task[SourcePageValidationSet] = Task.gatherUnordered(set.toSeq).map(_.toSet)

    monad.map(newState).unsafePerformSync
  }

  override def nextState(data: UrlsWithThrowableList): AbstractNextState = new ValidationEndState(data)
}
