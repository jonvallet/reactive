package com.jonvallet.scala.reactive.week3.future

import scala.util.Try

/**
 * @author Jon Vallet
 */
trait Socket {
  def readFromMemory(): Future[Array[Byte]]
  def sentToEurope(packet: Array[Byte]): Future[Array[Byte]]
}