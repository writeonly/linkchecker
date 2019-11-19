package pl.writeonly.scala.hyde.common.states

trait State {
  def showResult(): Unit

  def showStep(): Unit

  def isEmptyNextInternalUrls: Boolean
}
