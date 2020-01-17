package pl.writeonly.linkchecker.scala.common.states

trait State {
  def showResult(): Unit

  def showStep(): Unit

  def isEmptyNextInternalUrls: Boolean
}
