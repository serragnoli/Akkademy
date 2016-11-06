package com.akkademy

import akka.actor.{Actor, Props, Status}
import akka.event.Logging
import com.akkademy.messages.{GetRequest, KeyNotFoundException, SetIfNotExistsRequest, SetRequest}

import scala.collection.mutable

class AkkademyDbActor extends Actor {
  val map = new mutable.HashMap[String, String]
  val log = Logging(context.system, this)

  override def receive = {
    case SetRequest(key, value) =>
      log.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value)
      sender() ! Status.Success
    case GetRequest(key) =>
      log.info("received GetRequest - key: {}", key)
      val response: Option[String] = map.get(key)
      response match {
        case Some(x) => sender() ! x
        case None => sender() ! Status.Failure(KeyNotFoundException(key))
      }
    case SetIfNotExistsRequest(key, value) =>
      log.info("received SetIfNotExistsRequest - key: {} value {}", key, value)
      sender() ! map.getOrElseUpdate(key, value)
    case o =>
      log.info("received unknown message: {}", o)
      Status.Failure(new ClassNotFoundException)
  }
}

object AkkademyDbActor {
  def props: Props = {
    Props(classOf[AkkademyDbActor])
  }
}
