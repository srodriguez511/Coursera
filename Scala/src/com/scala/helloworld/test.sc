package com.scala.helloworld;

object test{
	val x = 1                                 //> x  : Int = 1
	
	def increase(i: Int) = i + 1              //> increase: (i: Int)Int
	
	increase(x)                               //> res0: Int = 2
}