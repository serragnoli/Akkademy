package com.akkademy.messages

import akka.actor.Actor
import akka.actor.Actor.Receive
import akka.event.Logging

import scala.collection.mutable

class AkkademyDb extends Actor {
  val map = new mutable.HashMap[String, Object]
  val log = Logging(context.system, this)

  override def receive = {
    case SetRequest(key, value) =>
      log.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value)
    case o => log.info("received unknown message: {}", o)
  }
}
