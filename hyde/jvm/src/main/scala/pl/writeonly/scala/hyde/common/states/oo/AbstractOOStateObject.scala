package pl.writeonly.scala.hyde.common.states.oo

import pl.writeonly.scala.hyde.common.url.Domain
import scalaz.Scalaz._

trait AbstractOOStateObject {

  final def apply(domain: String): AbstractOOState = fromDomain(new Domain(domain)) |> AbstractOOState.run

  def fromDomain(implicit d: Domain): AbstractOOState
}
