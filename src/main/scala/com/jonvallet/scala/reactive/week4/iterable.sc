import scala.util.Try

type Iterable[T]= ()=>(()=>Try[Option[T]])
trait Observer[T]  {
  def onError(error: Throwable): Unit
  def onCompleted(): Unit
  def onNext(value: T): Unit
}
trait Observable[T] {
  def Subscribe(observer: Observer[T]): Subscription
}
trait Subscription {
  def unsubscribe(): Unit
  def isUnsuscribed: Boolean
}
