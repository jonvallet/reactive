package com.jonvallet.scala.reactive.week2.observer

/**
 * @author Jon Vallet
 */
trait Publisher {
  private var subscribers: Set[Subscriber] = Set()

  def subscribe(subscriber: Subscriber) = subscribers += subscriber
  def unsubscribe (subscriber: Subscriber) = subscribers -= subscriber
  def publish() = for (subscriber <- subscribers) subscriber.handler(this)
}