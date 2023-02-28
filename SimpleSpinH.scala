import io.chymyst.jc._
import math._

/*

  H_Ion−Trap = ∑ i<j (Jij σi σj)

*/

// Sites

class Atom (val i : Int, val x : Double, val y : Double, val z : Double) {

  private def sq (x : Double) = x * x

  def distanceTo (a : Atom) : Double =
    sqrt (sq (a.x - x) + sq (a.y - y) + sq (a.z - z))

  override def toString : String =
    "Atom@(%s,%s,%s)".format(x,y,z)

}

// Connections

class Bond(val p1 : Atom, val p2 : Atom, J0 : Double, a : Double) {

  def bondEnergy : Double = {
    val r = p1.distanceTo(p2)
    J0 / pow(r,a)
  }

  override def toString : String =
    "Bond[%s--%s]".format(p1,p2)

}

// Sample system

class System (n : Int) {

  val J0 : Double = 1000.0
  val a : Double = 2.0
  val atoms : List[Atom] = makeAtoms(n)
  val bonds : List[Bond] = makeBonds(atoms)

  def makeAtoms (n : Int) : List[Atom] =
    for (i <- List.range(0,n))
      yield new Atom(i, random(),random(),random())

  def makeBonds (atoms : List[Atom]) : List[Bond] =
    for (p1 <- atoms; p2 <- atoms if p1.i < p2.i)
      yield new Bond(p1,p2,J0,a)

  override def toString : String =
    "Atoms = %s%nBonds = %s%n".format(atoms,bonds)

}

// Main

object SimpleSpinH extends App {

  def energy (s : System) : Double = {
    val bond = m[Bond]
    val energy = m[Double]

    site(
      go { case bond(e) + energy(p) => energy(p + e.bondEnergy) },
      go { case _ => energy(0) },
    )

    s.bonds.foreach(bond)

    Thread.sleep(100)
    energy.volatileValue
  }

  val e = energy(new System(4))
  printf("Total energy after 100ms = %s%n", e)
}
