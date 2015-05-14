package com.jonvallet.scala.reactive.week5

import akka.actor.{Props, Actor}

/**
 * @author Jon Vallet
 */
class Main extends Actor {
  val counter = context.actorOf(Props[Counter], "counter")


  counter ! "incr"
  counter ! "incr"
  counter ! "incr"
  counter ! "get"

  def receive = {
    case count: Int =>
      println(s"count was $count")
      context.stop(self)
  }

}
