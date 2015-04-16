import java.util.Random

def randomIntegers: Stream[Int] = Stream.cons({new Random().nextInt()}, randomIntegers)

randomIntegers take 10 toList

trait Generator[+T] {

  self =>

  def generate: T

  def map[S](f: T=> S): Generator[S] = new Generator[S] {
    def generate = f(self.generate)
  }

  def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
    override def generate: S = f(self.generate).generate
  }

}

val integers = new Generator[Int] {
  def generate = {
    val rand = new Random
    rand.nextInt()
  }
}
integers.generate
integers

val booleans = for (x <- integers) yield x > 0
booleans.generate
booleans.generate

trait Tree
case class Inner(left: Tree, right: Tree) extends Tree
case class Leaf(x: Int) extends Tree


def leafs: Generator[Leaf] = for {
  x <- integers
} yield Leaf(x)

def inners: Generator[Inner]= for {
  l <- trees
  r <- trees
} yield Inner(l,r)

def trees: Generator[Tree] = for {
  isLeaf <- booleans
  tree <- if (isLeaf) leafs else inners
} yield tree

trees.generate
trees.generate
