package com.akkademy

import akka.actor.ActorSystem
import akka.pattern.ask
import akka.util.Timeout
import com.akkademy.messages.{GetRequest, ReverseRequest, SetRequest}

import scala.concurrent.duration._
import scala.language.postfixOps

class SClient(remoteAddress: String) {
  private implicit val timeout = Timeout(2 seconds)
  private implicit val system = ActorSystem("LocalSystem")

  private val remoteDb = system.actorSelection(s"akka.tcp://AkkademySystem@$remoteAddress/user/akkademy-db")
  private val reverser = system.actorSelection(s"akka.tcp://AkkademySystem@$remoteAddress/user/reverse-text-actor")

  def set(key: String, value: String) = {
    remoteDb ? SetRequest(key, value)
  }

  def get(key: String) = {
    remoteDb ? GetRequest(key)
  }

  def reverse(text: String) = {
    reverser ? ReverseRequest(text)
  }

  def reverseWithDodgyMessage(text: String) = {
    reverser ? GetRequest(text)
  }
}
