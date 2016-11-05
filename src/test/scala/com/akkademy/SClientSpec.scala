package com.akkademy

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

      val result: String = Await.result(future.mapTo[String], 10 seconds)
      result should equal("Fabio Serragnoli")
    }
  }
}
