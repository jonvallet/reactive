def power (x: Double, exp: Int): Double = {
  var result = x
  for (i <- 1 until exp) result = result * x
  result
}
power (3,3)
power (3, 2)
power (3, 1)

def REPEAT (command: Unit)(f: Boolean): Unit = {
  if (f){}
  else
    command
    REPEAT(command)(f)
}