import com.jonvallet.scala.reactive.week3.future.{Future, Socket}

import scala.util.{Failure, Success}

val socket = new Socket() {
  override def readFromMemory(): Future[Array[Byte]] = ???

  override def sentToEurope(packet: Array[Byte]): Future[Array[Byte]] = ???
}

val packet = socket.readFromMemory()

val confirmation = packet.flatMap(p => socket.sentToEurope(p))

val confirmation2: Future[Array[Byte]] = for {
  p <- packet
  sent <- socket.sentToEurope(p)
} yield sent