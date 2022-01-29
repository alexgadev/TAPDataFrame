package scala

import java.factory.Dataframe
import scala.collection.mutable.ListBuffer

trait ADataframe[T]{
  def ls(): Unit
  def toList : ListBuffer[ADataframe]
  def setParent(parent:ADataframe):Unit

  def at(name:String, row:Int) : T
  def columns() : Int
  def size() : Int
}

class File[T](val name:String, val dataframe:Dataframe[T]) extends ADataframe[T]{
  override def df: Dataframe[T] = dataframe
}

class Directory[T]() extends ADataframe[T]{
  private var children: ListBuffer[ADataframe]
}