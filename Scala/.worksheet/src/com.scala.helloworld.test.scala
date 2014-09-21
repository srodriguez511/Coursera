package com.scala.helloworld;

object test{;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(54); 
	val x = 1;System.out.println("""x  : Int = """ + $show(x ));$skip(32); 
	
	def increase(i: Int) = i + 1;System.out.println("""increase: (i: Int)Int""");$skip(15); val res$0 = 
	
	increase(x);System.out.println("""res0: Int = """ + $show(res$0))}
}
