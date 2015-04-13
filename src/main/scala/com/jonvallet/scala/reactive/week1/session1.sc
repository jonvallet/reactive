println("test")
val f: PartialFunction[String, String] = {
  case "ping" => "pong"
}

f("ping")
f.isDefinedAt("ping")
f.isDefinedAt("abc")
val f2: PartialFunction[List[Int], String] = {
  case Nil => "one"
  case x :: y :: rest => "two"
}

f2(List(1,2,3))
f2.isDefinedAt(List(1,2,3))
val g: PartialFunction[List[Int], String] = {
  case Nil => "one"
  case x :: rest =>
    rest match {
      case Nil => "two"
    }
}

g.isDefinedAt(List(1, 2, 3))