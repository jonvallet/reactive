package com.jonvallet.scala.reactive.week2.signals


/**
  * @author Jon Vallet
  */
class BankAccount {


  val balance = Var(0)

   def deposit(amount: Int): Unit = {
     assert(amount > 0)
     val b = balance()
     balance() = b + amount
   }

   def withdraw(amount: Int): Int = {
     assert(amount > 0)
     val b = balance()
     assert(b >= amount)
     balance() = b - amount
     balance()
   }

   def currentBalance(): Int = balance()

   override def toString(): String = "Balance= "+balance()
 }
