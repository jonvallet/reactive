package com.jonvallet.scala.reactive.week3.sinchro

/**
 * @author Jon Vallet
 */
trait Adventure {
  def collectCoins(): Try[List[Coin]]
  def buyTreasure(coins: List[Coin]): Try[Treasure]
}

class Coin

class Treasure

abstract class Try[T] {

  case class Success[T](elem: T) extends Try[T]

  case class Failure(t: Throwable) extends Try[Nothing]

  def flatMap[S](f: T=>Try[S]): Try[S] = ???


}

