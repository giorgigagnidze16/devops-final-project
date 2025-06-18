import io.gatling.core.Predef._
import io.gatling.http.Predef._

class BasicSimulation extends Simulation {
  val httpProtocol = http
    .baseUrl("http://backend:8080")

  val scn = scenario("Basic Load Test")
    .exec(
      http("Get All Tasks")
        .get("/api/tasks")
        .check(status.is(200))
    )
    .pause(1)

  setUp(
    scn.inject(
      atOnceUsers(10),          // 10 users at once
      rampUsers(50) during (30) // 50 users over 30 seconds
    )
  ).protocols(httpProtocol)
}
