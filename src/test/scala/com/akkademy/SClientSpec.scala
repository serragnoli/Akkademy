package com.akkademy

import com.akkademy.messages.UnknownReverseTextMessageException
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

class SClientSpec extends FunSpecLike with Matchers {
  val client = new SClient("127.0.0.1:2552")

  describe("akkademyDbClient") {
    it("should set a value") {
      client.set("123", "Fabio Serragnoli")

      val future = client.get("123")

      val result = Await.result(future.mapTo[String], 10 seconds)
      result should equal("Fabio Serragnoli")
    }

    it("should reverse the text") {
      val future = client.reverse("Fabio Serragnoli")

      val result = Await.result(future.mapTo[String], 10 seconds)
      result should equal("ilongarreS oibaF")
    }

    it("should fail when message is unknown") {
      val future = client.reverseWithDodgyMessage("Fabio Serragnoli")

      intercept[UnknownReverseTextMessageException] {
        Await.result(future.mapTo[String], 10 seconds)
      }
    }
  }
}
