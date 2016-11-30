package com.akkademy

import akka.actor.SupervisorStrategy.{Restart, Resume}
import akka.actor.{Actor, ActorRef, OneForOneStrategy}
import akka.pattern.ask
import akka.util.Timeout
import com.akkademy.messages.{GetRequest, HttpResponse, ParseArticle, ParseHtmlArticle, SetRequest}

import scala.concurrent.Future
import scala.concurrent.duration.Duration
import scala.language.postfixOps

class AskDemoArticleParser(cacheActorPath: String,
                           httpClientActorPath: String, articleParserActorPath: String, implicit
                           val timeout: Timeout) extends Actor {

  val cacheActor = context.actorSelection(cacheActorPath)
  val httpClientActor = context.actorSelection(httpClientActorPath)
  val articleParseActor = context.actorSelection(articleParserActorPath)

  override def receive: Receive = {
    case ParseArticle(uri) =>
      val senderRef: ActorRef = sender() //sender ref needed for closure

      val cacheResult = cacheActor ? GetRequest(uri)

      val result = cacheResult.recoverWith {
        case _ =>
          val fRawResult = httpClientActor ? uri

          fRawResult flatMap {
            case HttpResponse(rawArticle) =>
              articleParseActor ? ParseHtmlArticle(uri, rawArticle)
            case x =>
              Future.failed(new Exception("unknown response"))
          }
      }

      result onComplete {
        case scala.util.Success(x: String) =>
          println("cached result!")
          senderRef ! x
        case scala.util.Success(x: ArticleBody) =>
          cacheActor ! SetRequest(uri, x.body)
          senderRef ! x
        case scala.util.Failure(t) =>
          senderRef ! akka.actor.Status.Failure(t)
        case x =>
          println("unknown message! " + x)

      }
  }

  override def supervisorStrategy = {
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = Duration.apply("10 seconds")) {
      case IllegalArgumentException => Resume
      case IllegalStateException => Restart
    }
  }
}
