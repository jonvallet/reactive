package com.jonvallet.scala.reactive.week2.signals

/**
 * @author Jon Vallet
 */
class Var[T](expr: => T) extends Signal[T](expr) {

  override def update(expr: => T): Unit = super.update(expr)

}

object Var {
  def apply[T](expr: => T) = new Var(expr)
}
