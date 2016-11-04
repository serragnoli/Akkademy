package com.akkademy.messages

import akka.actor.ActorSystem

import scala.concurrent.Future
import scala.language.postfixOps
import akka.pattern.ask
import akka.util.Timeout
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.duration._
import scala.util.{Failure, Success}

class WithoutRedundancyTest extends FunSpecLike with Matchers {
  implicit val timeout = Timeout(5 seconds)
  val actorSystem = ActorSystem()
  val pongActor = actorSystem.actorOf(PongActor props)

  describe("Future Examples") {
    import scala.concurrent.ExecutionContext.Implicits.global

    it("should print to console") {
      println("Invoker " + Thread.currentThread().getName)
      (pongActor ? "Ping").onComplete(x => println(Thread.currentThread().getName + ": replied with " + x))
      Thread.sleep(100)
    }

    it("should start using askPong") {
      val newFuture = askPong("Ping").map(x => x.charAt(0))

      newFuture.onComplete(x => println("Result should be 'P': " + x.get))
    }

    it("should make two async calls to askPong") {
      val f1 = askPong("Ping").map(x => askPong("xPing").map(x => askPong("Ping")))
      val f2 = askPong("Ping").flatMap(x => askPong("xPing").flatMap(x => askPong("Ping")))
      println("Nested failures and not nested " + f1 + " and " + f2)
    }

    it("should handle failures") {
      val pong: Future[String] = askPong("causeError")

      pong.onComplete({
        case Success(s) => println("Yay " + s)
        case Failure(e) => println("Shit " + e)
      })
    }
  }

  def askPong(message: String): Future[String] = (pongActor ? message).mapTo[String]
}
