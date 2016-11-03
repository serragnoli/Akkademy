package com.akkademy.messages

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import org.scalatest.{BeforeAndAfterEach, FunSpecLike, Matchers}

class AkkademyDbTest extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem()

  describe("akkademyDb") {
    describe("given SetRequest") {
      it("should place key/value into map") {
        val actorRef = TestActorRef(new AkkademyDb)

        actorRef ! SetRequest("MyKey", "MyValue")

        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map.get("MyKey") should equal(Some("MyValue"))
      }
    }
  }
}
