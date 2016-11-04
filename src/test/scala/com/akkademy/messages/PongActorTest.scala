package com.akkademy.messages

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import org.scalatest.{BeforeAndAfterEach, FunSpecLike, FunSuite, Matchers}

class PongActorTest extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem()

  describe("akkademyDb") {
    describe("given 'Ping'") {
      it("should respond with 'Pong'") {
        val actorRef = TestActorRef(new PongActor, "Marty McFly")

        actorRef ! "Ping"

        println(">>>>>>>" + actorRef.path)

        val pongActor = actorRef.underlyingActor
      }
    }
  }
}
