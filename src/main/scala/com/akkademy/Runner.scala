package com.akkademy

import akka.actor.{ActorRef, ActorSystem}
import akka.pattern.ask
import akka.util.Timeout
import com.akkademy.messages.PongActor
import scala.language.postfixOps

import scala.concurrent.Future

import scala.concurrent.duration._

object Runner {

  def main(args: Array[String]): Unit = {
    implicit val timeout = Timeout(1 second)
    val system = ActorSystem("AkkademySystem")

    val pongActor: ActorRef = system.actorOf(PongActor props)

    val future = pongActor ? "Ping"
  }
}
