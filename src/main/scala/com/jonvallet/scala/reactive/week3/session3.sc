import com.jonvallet.scala.reactive.week3.future._
import scala.util.{Failure, Success, Try}


val socket = new Socket() {
  override def readFromMemory(): Future[Array[Byte]] = ???

  override def sentToEurope(packet: Array[Byte]): Future[Array[Byte]] = ???
}

val packet = socket.readFromMemory()
val confirmation = packet.onComplete {
  case Success(p) => socket.sentToEurope(p)
  case Failure(t) => throw t
}
