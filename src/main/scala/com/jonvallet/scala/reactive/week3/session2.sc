import com.jonvallet.scala.reactive.week3._

val adventure = new sinchro.Adventure() {
  override def collectCoins(): sinchro.Try[List[sinchro.Coin]] = ???

  override def buyTreasure(coins: List[sinchro.Coin]): sinchro.Try[sinchro.Treasure] = ???
}

val coins = adventure.collectCoins()

val treasure = coins flatMap(coins => adventure.buyTreasure(coins))

val treasure2 = for {
  coins <- adventure.collectCoins()
  treasure <- adventure.buyTreasure(coins)
} yield treasure