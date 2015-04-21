package com.jonvallet.scala.reactive.week2.observer

/**
 * @author Jon Vallet
 */
trait Subscriber {
  def handler(publisher: Publisher)
}
