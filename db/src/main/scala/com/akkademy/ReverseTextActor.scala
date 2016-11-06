package com.akkademy

import akka.actor.{Actor, Props, Status}
import akka.event.Logging
import com.akkademy.messages.{ReverseRequest, UnknownReverseTextMessageException}

class ReverseTextActor extends Actor {
  val log = Logging(context.system, this)

  override def receive: Receive = {
    case ReverseRequest(text) =>
      log.info("received ReverseRequest - text: {}", text)
      sender() ! text.reverse
    case o =>
      log.info("received unknown message: {}", o)
      sender() ! Status.Failure(UnknownReverseTextMessageException())
  }
}

object ReverseTextActor {
  def props = {
    Props(classOf[ReverseTextActor])
  }
}