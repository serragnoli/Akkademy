package com.akkademy

import akka.actor.{Actor, Props, Status}

class PongActor extends Actor {
  override def receive = {
    case "Ping" => sender() ! "Pong"
    case _ => sender() ! Status.Failure(new Exception("Unknown message"))
  }
}

object PongActor {
  def props: Props = {
    Props(classOf[PongActor])
  }

  def props(response: String): Props = {
    Props(classOf[PongActor], response)
  }
}


