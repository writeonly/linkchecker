package pl.writeonly.linkchecker.scala.common.states.notexception

import pl.writeonly.linkchecker.scala.sourcepage.InternalUrlTo

trait AbstractFunctionState extends AbstractNewSetState {

  protected final def newSet: SET = nextUrls.map(impureFunction)

  protected def impureFunction: HPFromInternalUrl

  protected type HPFromInternalUrl = InternalUrlTo[HP]

}
