package function

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._
import scala.concurrent.duration._

class EchoSimulation extends Simulation {

  val protocol = karateProtocol(
    "/dispatch" -> Nil
  )

  val echoSenario = scenario("echoDispatch").exec(karateFeature("classpath:function/echo-dispatch.feature"))

  var MAX_USERS = 20
  var injections = Seq(rampUsersPerSec(1) to (MAX_USERS) during(10 seconds), constantUsersPerSec(MAX_USERS) during(20 seconds))

  setUp(
    echoSenario.inject(injections).protocols(protocol)
  )

}
