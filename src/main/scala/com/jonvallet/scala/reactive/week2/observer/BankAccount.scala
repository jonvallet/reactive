package com.jonvallet.scala.reactive.week2.observer

/**
 * @author Jon Vallet
 */
class BankAccount extends Publisher {
  private var balance: Int = 0

  def deposit(amount: Int): Unit = {
    assert(amount > 0)
    balance += amount
    publish()
  }

  def withdraw(amount: Int): Int = {
    assert(amount > 0)
    assert(balance >= amount)
    balance -= amount
    publish()
    balance
  }

  def currentBalance(): Int = balance

  override def toString(): String = "Balance= "+balance
}
