package function

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._
import scala.concurrent.duration._

class EchoFluxSimulation extends Simulation {

  val protocol = karateProtocol(
    "/dispatchFlux" -> Nil
  )

  val echoSenario = scenario("echoDispatchFlux").exec(karateFeature("classpath:function/echo-flux-dispatch.feature"))

  var MAX_USERS = 20
  var injections = Seq(rampUsersPerSec(1) to (MAX_USERS) during(10 seconds), constantUsersPerSec(MAX_USERS) during(20 seconds))

  setUp(
    echoSenario.inject(injections).protocols(protocol)
  )

}
