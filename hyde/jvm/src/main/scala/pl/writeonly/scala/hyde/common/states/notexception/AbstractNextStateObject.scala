package pl.writeonly.scala.hyde.common.states.notexception

import pl.writeonly.scala.hyde.common.url.Domain
import scalaz.Scalaz._

trait AbstractNextStateObject {

  final def apply(domain: String): AbstractNextState = fromDomain(new Domain(domain)) |> AbstractNextState.run

  protected def fromDomain(implicit d: Domain): AbstractNextState
}
