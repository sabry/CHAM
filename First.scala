import io.chymyst.jc._

// First example

object First extends App {
  println("Hello, World!")
}

// Prime numbers

object Primes extends App {
  val n = m[Int] // our molecules are integers

  // reaction: pick two molecules; if one is a 
  // multiple of the other, remove it
  site (go { case n(i) + n(j) if j % i == 0 => n(i) })

  // populate with 2..15; run reactions for 500 ms
  (2 to 50).foreach(n)
  Thread.sleep(5000)
  println(n.logSoup)
  // Molecules: n(11) + n(13) + n(2) + n(3) + n(5) + n(7)
}


/*

  printf("Initial solution: %s%n%n", n.logSoup)

  Thread.sleep(1)
  printf("After 1 ms: %s%n%n", n.logSoup)

  Thread.sleep(1)
  printf("After 2 ms: %s%n%n", n.logSoup)

  Thread.sleep(100)
  printf("After 100 ms: %s%n", n.logSoup)
*/

/*
// Baby molecular dynamics

class Atom(val x : Double, val y : Double, val z : Double) {
  private def sq (x : Double) = x * x
  def bondEnergy (a : Atom, k : Double) : Double =
      0.5 * k * sq (a.x - x) + sq (a.y - y) + sq (a.z - z)
  override def toString: String =
    "Atom@(%s,%s,%s)".format(x,y,z)
}

class Bond(val k : Double, val p1 : Atom, val p2 : Atom) {
  def bondEnergy () : Double = p1.bondEnergy(p2,k)
}

//

class Graph (val edges : List[Bond]) {

  def energy () : Double = {
    val bond = m[Bond]
    val energy = m[Double]

    site (
      go { case bond (e) + energy(p) => energy (p + e.bondEnergy ())},
      go { case _ => energy(0) },
    )

    edges.foreach(bond)

    Thread.sleep (100)
    energy.volatileValue
  }

}

//

object Atoms extends App {

  val a1 = new Atom(0,0,0)
  val a2 = new Atom(1,2,3)
  val a3 = new Atom(1,0,1)
  val a4 = new Atom(2,0,1)
  val a5 = new Atom(3,1,1)

  val e1 = new Bond(200, a1, a2)
  val e2 = new Bond(150, a1, a3)
  val e3 = new Bond(100, a2, a3)
  val e4 = new Bond(120, a2, a5)
  val e5 = new Bond(80,  a2, a4)
  val e6 = new Bond(300, a3, a4)
  val e7 = new Bond(270, a5, a1)
  val e8 = new Bond(180, a5, a2)

  val g = new Graph(List(e1,e2,e3,e4,e5,e6,e7,e8))
  printf("Energy = %s%n", g.energy())
}

object Graphs extends App {
  val a1 = new Atom(0,0,0)
  val a1p = new Atom(0,0,0.1)
  val a2 = new Atom(1,2,3)
  val a3 = new Atom(1,0,1)
  val a4 = new Atom(2,0,1)
  val a5 = new Atom(3,1,1)

  val e1 = new Bond(200, a1, a2)
  val e1p = new Bond(200, a1p, a2)
  val e2 = new Bond(150, a1, a3)
  val e3 = new Bond(100, a2, a3)
  val e4 = new Bond(120, a2, a5)
  val e5 = new Bond(80,  a2, a4)
  val e6 = new Bond(300, a3, a4)
  val e7 = new Bond(270, a5, a1)
  val e8 = new Bond(180, a5, a2)

  val g1 = new Graph(List(e1,e2,e3,e4,e5,e6,e7,e8))
  val g2 = new Graph(List(e1,e2,e3,e4,e6,e7,e8))
  val g3 = new Graph(List(e1p,e2,e3,e4,e5,e6,e7,e8))
  val gs = List(g1,g2,g3)

  val graph = m[Graph]
  val energy = m[Double]

  site (
    go { case graph (g) + energy(p) => energy (p + g.energy()) }
  )

  gs.foreach(graph)
  energy(0)

  Thread.sleep (1000)
  printf("Total energy = %s%n", energy.logSoup) // energy.volatileValue)
}

*/
