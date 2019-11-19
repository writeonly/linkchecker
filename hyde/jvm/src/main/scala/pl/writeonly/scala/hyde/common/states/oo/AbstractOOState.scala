package pl.writeonly.scala.hyde.common.states.oo

import scala.annotation.tailrec

import pl.writeonly.scala.hyde.common.states.State
import pl.writeonly.scala.hyde.common.url._
import pl.writeonly.scala.hyde.common.url.urls._
import pl.writeonly.scala.hyde.sourcepage.oo.InternalUrlToSourcePage
import scalaz.Scalaz._

abstract class AbstractOOState(urls: Urls)(implicit d: Domain) extends State {

  final def next: AbstractOOState = getWrappedUrlSet |> NewUrls.apply |> urls.next |> nextState

  final def getWrappedUrlSet: WrappedUrlSet =
    urls.nextInternalUrls
      .map(impureFunction)
      .flatMap(_.getWrappedUrlSet)

  override final def showResult(): Unit = urls.showResult()

  override final def showStep(): Unit = urls.showStep()

  override final def isEmptyNextInternalUrls: Boolean = urls.isEmptyNextInternalUrls

  protected def nextState(urls: Urls): AbstractOOState

  protected def impureFunction: InternalUrlToSourcePage
}

object AbstractOOState {
  @tailrec
  def run(state: AbstractOOState): AbstractOOState = if (state.isEmptyNextInternalUrls) state else run(state.next)
}
