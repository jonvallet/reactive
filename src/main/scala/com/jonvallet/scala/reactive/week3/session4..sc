

class Duration (length: Int, unit: String) {
  override def toString() = "Length: "+length+" Unit: "+unit
}


object Duration {
  def apply(length: Int, unit: String): Duration = new Duration(length, unit)
}

case class Music(song: String)

object print {
  def apply(music: Music) = println(music)
}

object sing {
  def apply(song: String): Music = new Music(song)
}

val duration: Duration = Duration(54, "minutes")

val duration2 = Duration(43 , "minutes")

print(sing("lalala"))