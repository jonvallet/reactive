import com.jonvallet.scala.reactive.week2.signals.{Signal,Var, BankAccount}

def consolidate(accs: List[BankAccount]): Signal[Int] = Signal(accs.map(_.balance()).sum)

val a = new BankAccount()
val b = new BankAccount()
val c = consolidate(List(a,b))
c()
a deposit 20
c()
val exchRate = Var(276)

val bitCoins = Var(c() * exchRate())

bitCoins()

a withdraw 5

bitCoins()

exchRate() = 240

bitCoins()