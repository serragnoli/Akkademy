package com.akkademy

import akka.actor.ActorSystem
import akka.util.Timeout

import scala.concurrent.duration._
import scala.language.postfixOps

object Runner {

  def main(args: Array[String]): Unit = {
    implicit val timeout = Timeout(1 second)
    val system = ActorSystem("AkkademySystem")

    system.actorOf(AkkademyDbActor props, "akkademy-db")
    system.actorOf(ReverseTextActor props, "reverse-text-actor")
  }
}
