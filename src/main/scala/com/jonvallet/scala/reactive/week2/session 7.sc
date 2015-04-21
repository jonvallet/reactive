import com.jonvallet.scala.reactive.week2.observer.{Consolidator, BankAccount}

val bankAccount1 = new BankAccount
val bankAccount2 = new BankAccount

val consolidator = new Consolidator(List(bankAccount1, bankAccount2))

consolidator.totalBalance()

bankAccount1.deposit(10)

consolidator.totalBalance()

bankAccount2.deposit(50)

consolidator.totalBalance()


