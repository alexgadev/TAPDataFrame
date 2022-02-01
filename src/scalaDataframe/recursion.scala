package scalaDataframe

import scala.jdk.CollectionConverters._
import javaDataframe.factory.{CSVFactory, Dataframe}

import scala.::

class DataFrame [T]{
  private var df:Dataframe[T] = new Dataframe[T](new CSVFactory(), "cities.csv")

  def getColumn(label:String):List[T] = df.getColumn(label).asScala.toList

}

object Run extends scala.App{

  val df = new DataFrame

  // stack implementation
  def listFilterMap[T](f:T => Boolean, f2:T => T, l:List[T]) : List[T] = l match {
    case Nil => Nil
    case x :: xs => if (f(x)) f2(x)::listFilterMap(f, f2, xs)
                    else x::listFilterMap(f, f2, xs)
  }

  // tail recursion
  def listFilterMap2[T](f:T => Boolean, f2:T => T, l:List[T]) : List[T] = {
    def listFilterMapAccum[T](f:T => Boolean, f2:T => T, l:List[T], accum: List[T]): List[T] = {
      l match {
        case Nil => accum
        case x :: xs => if (f(x)) listFilterMapAccum(f, f2, xs, f2(x)::accum)
                        else listFilterMapAccum(f, f2, xs, x::accum)
      }
    }
    listFilterMapAccum(f, f2, l, Nil)
  }

  // Replace a certain word from a string-type column on the elements that contain that word
  val str : String = "NOT A STATE"

  def replacei = (elem:String) => elem.replace(elem, str)

  def compare = (elem:String) => elem.equals("OH")

  //println(listFilterMap(compare, replacei, df.getColumn("State")))
  println(listFilterMap2(compare, replacei, df.getColumn("State")))

}

