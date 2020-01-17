package pl.writeonly.linkchecker.scala.common.states.notexception

import pl.writeonly.linkchecker.scala.common.url.Domain
import scalaz.Scalaz._

trait AbstractNextStateObject {

  final def apply(domain: String): AbstractNextState = fromDomain(new Domain(domain)) |> AbstractNextState.run

  protected def fromDomain(implicit d: Domain): AbstractNextState
}
