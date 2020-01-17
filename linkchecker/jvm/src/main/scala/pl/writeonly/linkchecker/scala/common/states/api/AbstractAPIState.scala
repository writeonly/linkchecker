package pl.writeonly.linkchecker.scala.common.states.api

import pl.writeonly.linkchecker.scala.common.states.State
import pl.writeonly.linkchecker.scala.common.url.typed.InternalUrls
import pl.writeonly.linkchecker.scala.common.url.urls._
import scalaz.Scalaz._

abstract class AbstractAPIState(data: UrlsWithThrowableList) extends State {

  protected type HP

  protected final type SET = Set[HP]

  type NextState

  override def showStep(): Unit = data.showStep()

  def showResult(): Unit = data.showResult()

  def isEmptyNextInternalUrls: Boolean = data.isEmptyNextInternalUrls

  override def toString: String = data.toString

  protected final def newState(set: SET): NextState = set |> nextData |> nextState

  protected def nextData(set: SET): UrlsWithThrowableList

  protected def nextState(data: UrlsWithThrowableList): NextState

  protected def nextUrls: InternalUrls = data.nextUrls

  def throwableList: ThrowableList = data.throwableList
}
