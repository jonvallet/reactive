package com.jonvallet.scala.reactive.week2.observer

/**
 * @author Jon Vallet
 */
class Consolidator(observed: List[BankAccount]) extends Subscriber{

  private var total: Int = _
  compute()

  private def compute() = {
    total = observed map (_.currentBalance()) sum
  }

  observed foreach (_.subscribe(this))

  def totalBalance() = total

  override def handler(publisher: Publisher): Unit = compute()
}
