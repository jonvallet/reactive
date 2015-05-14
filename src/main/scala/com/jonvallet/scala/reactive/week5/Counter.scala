package com.jonvallet.scala.reactive.week5

import akka.actor.Actor


/**
 * @author Jon Vallet
 */
class Counter extends Actor {
  def counter(n: Int): Receive = {
    case "incr" => context.become(counter(n+1))
    case "get" => sender ! n
  }
  def receive = counter(0)
}



