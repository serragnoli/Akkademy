package com.akkademy.messages

import akka.actor.ActorSystem
import akka.pattern.ask
import akka.util.Timeout
import com.akkademy.PongActor
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.language.postfixOps
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

    it("should recover from failure") {
      askPong("causeError").recover({
        case t => {
          println("Returning default")
          "default"
        }
      })
    }

    it("should recover by invoking another another async call") {
      askPong("causeError").recoverWith({
        case t => askPong("Ping");
      })
    }

    it("should combine futures") {
      val f1 = Future {
        12
      }
      val f2 = Future {
        0
      }

      val futureDivision = {
        for (
          res1 <- f1;
          res2 <- f2
        ) yield res1 / res2
      }

      futureDivision.onComplete({
        case Success(res) => println("Division of 2 futures is: " + res)
        case Failure(ex) => println("Exception dude: " + ex)
      })
    }

    it("should make async calls for each member in a collection") {
      // Here we end up with a list of Futures. BAD!!!
      val listOfFutures: List[Future[String]] = List("Ping", "Ping", "Ping", "CauseError").map(x => askPong(x))
      println("Lots of futures: " + listOfFutures)

      // Let's convert the List[Future[String]] to Future[List[String]]
      val futureOfList: Future[List[String]] = Future.sequence(listOfFutures)
      println("Future of list: " + futureOfList)
    }
  }

  def askPong(message: String): Future[String] = (pongActor ? message).mapTo[String]
}
