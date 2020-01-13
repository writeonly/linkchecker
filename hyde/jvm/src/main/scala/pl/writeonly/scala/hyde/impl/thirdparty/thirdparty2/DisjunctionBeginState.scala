package pl.writeonly.scala.hyde.impl.thirdparty.thirdparty2

import pl.writeonly.scala.hyde.common.states.api.DisjunctionAPIState
import pl.writeonly.scala.hyde.common.states.notexception._
import pl.writeonly.scala.hyde.common.url.Domain
import pl.writeonly.scala.hyde.common.url.urls.UrlsWithThrowableList
import pl.writeonly.scala.hyde.sourcepage.thirdparty._
import scalaz.Scalaz._
import scalaz.concurrent.Task

class DisjunctionBeginState(data: UrlsWithThrowableList)(implicit d: Domain) extends DisjunctionAPIState(data) with AbstractNewSetState {

  override def newSet: SourcePageDisjunctionSet = {

    val set: Set[Task[SourcePageDisjunction]] = nextUrls
      .map(SourcePageTaskFromInternalUrl.apply)

    val monad: Task[SourcePageDisjunctionSet] = Task.gatherUnordered(set.toSeq).map(_.toSet)

    monad.unsafePerformSync
  }

  override def nextState(data: UrlsWithThrowableList): AbstractNextState = new DisjunctionBeginState(data)
}

object DisjunctionBeginState {

  def apply(domain: String): AbstractNextState = fromDomain(new Domain(domain)) |> AbstractNextState.run

  def fromDomain(implicit d: Domain): AbstractNextState =
    new DisjunctionBeginState(UrlsWithThrowableList.fromDomain)

}
