package pl.writeonly.scala.hyde.common.states.notexception

import pl.writeonly.scala.hyde.sourcepage.InternalUrlTo

trait AbstractFunctionState extends AbstractNewSetState {

  protected final def newSet: SET = nextUrls.map(impureFunction)

  protected def impureFunction: HPFromInternalUrl

  protected type HPFromInternalUrl = InternalUrlTo[HP]

}
