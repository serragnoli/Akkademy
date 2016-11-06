package com.akkademy.messages

import akka.actor.ActorSystem
import org.scalatest.{FunSpecLike, Matchers}
import akka.pattern.ask
import akka.util.Timeout
import com.akkademy.PongActor

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

class AskExamplesTest extends FunSpecLike with Matchers {
  implicit val timeout = Timeout(5 seconds)
  val system = ActorSystem()
  val pongActor = system.actorOf(PongActor props)

  describe("Pong Actor") {
    it("should respond with Pong") {
      val future = pongActor ? "Ping" //uses the implicit timeout

      val result = Await.result(future.mapTo[String], 1 second)

      assert(result == "Pong")
    }

    it("should fail on unknown message") {
      val future = pongActor ? "Unknown"

      intercept[Exception] {
        Await.result(future.mapTo[String], 1 second)
      }
    }
  }
}
