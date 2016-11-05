package com.akkademy

import akka.actor.{ActorRef, ActorSystem}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.duration._
import scala.language.postfixOps

object Runner {

  def main(args: Array[String]): Unit = {
    implicit val timeout = Timeout(1 second)
    val system = ActorSystem("AkkademySystem")

    system.actorOf(AkkademyDb props, "akkademy-db")

  }
}
