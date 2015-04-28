package com.jonvallet.scala.reactive.week3.future

import scala.util.Try

/**
 * @author Jon Vallet
 */
trait Future[T] {
  def onComplete(callback: Try[T] => Unit)
  def flatMap[S](f: T=>Future[S]): Future[S] = ???
  def recover(f: PartialFunction[Throwable, T]): Future[T]
  def recoverWith(f: PartialFunction[Throwable, Future[T]]): Future[T]
}

object Future {
  def apply[T](body: => T): Future[T] = ???
}

