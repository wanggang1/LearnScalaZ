package org.gwgs.learningscalaz.day03

import scalaz._

object TaggedType {
  
  sealed trait KiloGram
  sealed trait JoulePerKiloGram
  
  def KiloGram[A](a: A): A @@ KiloGram = Tag[A, KiloGram](a)
  
  def JoulePerKiloGram[A](a: A): A @@ JoulePerKiloGram = Tag[A, JoulePerKiloGram](a)
  
  def energyR(m: Double @@ KiloGram): Double @@ JoulePerKiloGram =
    JoulePerKiloGram(299792458.0 * 299792458.0 * Tag.unwrap(m))
    
  def demo = {
    println("============== Tagged Type ===============")
    
    val mass = KiloGram(21.3)
    println("Tagged mass: " + mass)
    println("Tagged mass 2X value: " + 2 * Tag.unwrap(mass))

    val energy = energyR(mass)
    println("Tagged energy: " + energy)
    println("Tagged energy 2X value: " + 2 * Tag.unwrap(energy))
    
    println("")
  }

}