package pl.writeonly.linkchecker.scala.common.states.notexception

trait AbstractNewSetState extends AbstractNextState {

  final def next: AbstractNextState = newState(newSet)

  protected def newSet: SET
}
