package com.jonvallet.scala.reactive.week5.linkchecker

import akka.actor.{ReceiveTimeout, Props, Actor}
import com.jonvallet.scala.reactive.week5.linkchecker.Getter.Get
import scala.concurrent.duration._

/**
 * @author Jon Vallet
 */
class MainLinkChecker extends Actor {
  import Receptionist._

  val receptionist = context.actorOf(Props[Receptionist], "receptionist")

  receptionist ! Get("http://www.google.com")
  receptionist ! Get("http://www.google.com")
  receptionist ! Get("http://www.google.com")
  receptionist ! Get("http://www.google.com")
  receptionist ! Get("http://www.google.com/4")

  context.setReceiveTimeout(10 seconds)

  def receive = {
    case Result(url, set) =>
      println(set.toVector.sorted.mkString(s"Results for '$url':\n","\n","\n"))
    case Failed(url) =>
      println(s"Failed to fetch '$url'\n")
    case ReceiveTimeout =>
      context.stop(self)
  }

  override def postStop(): Unit = {
    WebClient.shutdown()
  }

}
