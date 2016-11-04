package com.akkademy

import akka.actor.{ActorRef, ActorSystem}
import com.akkademy.messages.PongActor

/**
  * Created by tvsdev on 04/11/2016.
  */
object Runner {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("AkkademySystem")

    val pongActor: ActorRef = system.actorOf(PongActor props)

    pongActor ! "Ping"
  }
}
