package org.gwgs.learningscalaz.day06

object worksheet {
  println("Day 6, Writer")                        //> Day 6, Writer
  
  import Writers._
  (3, "Smallish gang.") applyLog isBigGang        //> res0: (Boolean, String) = (false,Smallish gang.Compared gang size to 9.)
 
	import Readers._
	addStuff(3)                               //> res1: Int = 19
}