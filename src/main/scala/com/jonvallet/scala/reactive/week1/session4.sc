import scala.collection.immutable.BitSet
import scala.util.control.NonFatal

abstract class Try[+T]
case class Success[T](x: T) extends Try[T]
case class Failure(ex: Throwable) extends Try[Nothing]


object Try {
  def apply[T](expr: => T): Try[T] =
  try Success(expr)
  catch {
    case NonFatal(ex) => Failure(ex)
  }
}

3 until 4 length

val a = for {
  x <- 3 until 5
  y <- 1 until 3
}yield (x, 42)

a.length

for {
  x <- 0 until 10
  y <- 0 until 10
}yield(x+y)

val list = List(1,2,3)
list.map(_*2)
def f(n: Int): Int = n * 2
list map f
list.map((n: Int) => n * 2)


BitSet(1,2,3) map (_.toString.toInt)

BitSet(1,2,3) map (_.toString)

BitSet(1,2,3) map (_.toString) map (_.toInt)

val m = Map(1 -> 2) withDefaultValue 10

List(1,2,3).contains("1")
