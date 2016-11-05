package com.akkademy

import akka.actor.ActorSystem
import akka.util.Timeout

import scala.concurrent.duration._
import scala.language.postfixOps
import akka.pattern.ask
import com.akkademy.messages.{GetRequest, SetRequest}

class SClient(remoteAddress: String) {
  private implicit val timeout = Timeout(2 seconds)
  private implicit val system = ActorSystem("LocalSystem")
  private val remoteDb = system.actorSelection(s"akka.tcp://AkkademySystem@$remoteAddress/user/akkademy-db")

  def set(key: String, value: String) = {
    remoteDb ? SetRequest(key, value)
  }

  def get(key: String) = {
    remoteDb ? GetRequest(key)
  }
}
