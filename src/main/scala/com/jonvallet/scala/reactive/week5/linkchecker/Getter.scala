package com.jonvallet.scala.reactive.week5.linkchecker

import akka.actor._
import akka.pattern.pipe
import com.jonvallet.scala.reactive.week5.linkchecker.Getter.Get
import com.jonvallet.scala.reactive.week5.linkchecker.WebClient._
import scala.concurrent.duration._

/**
 * @author Jon Vallet
 */
class Getter(url: String, depth: Int) extends Actor {
  implicit val exec = context.dispatcher

  import Getter._

  val future = WebClient get url pipeTo self
  //  future onComplete {
  //    case Success(body) => self ! body
  //    case Failure(err) => sef ! Status.Failure(err)
  //  }

  def receive = {

    case body: String =>
      for (link <- findLinks(body))
        context.parent ! Controller.Check(link, depth)
      stop()

    case _: BadStatus => stop()
    case Abort => stop()
  }

  def stop(): Unit = {
    context.parent ! Done
    context.stop(self)
  }

}

object Getter {
  case class Get(url: String)
  case object Done
  case object Abort
}

class Controller extends Actor with ActorLogging {

  import Controller._
  import Getter._

  context.setReceiveTimeout(10 seconds)

  var cache = Set.empty[String]
  var children = Set.empty[ActorRef]

  def receive = {

    case Check(url, depth) =>
      log.debug("{} checking {}", depth, url)
      if (!cache(url) && depth > 0)
        children += context.actorOf(Props(new Getter(url, depth - 1)))
      cache += url

    case Getter.Done =>
      children -= sender
      if (children.isEmpty) context.parent ! Result(cache)

    case ReceiveTimeout => children foreach (_ ! Getter.Abort)
  }
}

object Controller {
  case class Check(url: String, depth: Int)
  case class Result(cache: Set[String])
}

class Receptionist extends Actor {

  var reqNo = 0

  def receive = waiting

  val waiting: Receive = {
    case Get(url) => context.become(runNext(Vector(Job(sender, url))))
  }

  def running(queue: Vector[Job]): Receive = {
    case Controller.Result(links) =>
      val job = queue.head
      job.client ! Receptionist.Result(job.url, links)
      context.stop(sender)
      context.become(runNext(queue.tail))
    case Get(url) =>
      context.become(enqueueJob(queue, Job(sender,url)))
  }

  def enqueueJob(queue: Vector[Job], job: Job): Receive = {
    if (queue.size > 3) {
      sender ! Failed(job.url)
      running(queue)
    }else running(queue :+ job)
  }

  def runNext(queue: Vector[Job]): Receive = {
    reqNo += 1
    if (queue.isEmpty) waiting
    else {
      val controller = context.actorOf(Props[Controller], s"c$reqNo")
      controller ! Controller.Check(queue.head.url, 2)
      running(queue)
    }
  }
}

object Receptionist {
  case class Result(url: String, cache: Set[String])
}

case class Job(client: ActorRef, url: String)

case class Failed(url: String)

