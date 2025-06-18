import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.Random

class CrudSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://backend:8080")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")

  val feeder = Iterator.continually(Map(
    "desc" -> ("Task " + Random.alphanumeric.take(12).mkString),
    "completed" -> Random.nextBoolean()
  ))

  val scn = scenario("High-Concurrency CRUD Users")
    .feed(feeder)
    // CREATE
    .exec(
      http("Create Task")
        .post("/api/tasks")
        .body(StringBody("""{"description": "${desc}", "completed": ${completed}}""")).asJson
        .check(status.is(200))
        .check(jsonPath("$.id").saveAs("taskId"))
    )
    // UPDATE with new random description
    .exec(session => session.set("desc2", "Task " + Random.alphanumeric.take(10).mkString))
    .exec(
      http("Update Task")
        .put("/api/tasks/${taskId}")
        .body(StringBody("""{"description": "${desc2}", "completed": false}""")).asJson
        .check(status.is(200))
    )
    // GET ALL
    .exec(
      http("Get All Tasks")
        .get("/api/tasks")
        .check(status.is(200))
    )
    // DELETE
    .exec(
      http("Delete Task")
        .delete("/api/tasks/${taskId}")
        .check(status.is(200))
    )

  setUp(
    scn.inject(
      atOnceUsers(1000)              // 1000 concurrent users
    )
  ).protocols(httpProtocol)
}
