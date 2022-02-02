package scalaDataframe

import javaDataframe.factory.{CSVFactory, Dataframe}

import java.util
import scala.jdk.CollectionConverters._
import java.util.Comparator
import java.util.function.Predicate
import scala.collection.mutable.ListBuffer

trait ADataframe[T]{
  def setParent(parent: ADataframe[T]): Unit

  def sort(name:String, comparator: Comparator[T]): List[T]

  def query(label: String, p: Predicate[T]): Map[String, java.util.List[T]]

  def accept(v: visitor): Unit
}

class Directory[T](str: String) extends ADataframe[T] {
  private var name: String = str
  private val children: ListBuffer[ADataframe[T]] = new ListBuffer[ADataframe[T]]
  private var parent: ADataframe[T] = null

  def addChild(child: ADataframe[T]): Unit = children += child

  def removeChild(child: ADataframe[T]): Unit = children -= child

  def getChildren: ListBuffer[ADataframe[T]] = children

  override def setParent(parent: ADataframe[T]): Unit = this.parent = parent

  override def sort(name: String, comparator: Comparator[T]): List[T] = {
    val sorted: List[T] = Nil

    for(child <- children){
      sorted::child.sort(name, comparator)
    }
    sorted
  }

  override def query(label: String, p: Predicate[T]): Map[String, util.List[T]] = {
    val df: Map[String, java.util.List[T]] = null
    for(child <- children){
      val nDf: Map[String, java.util.List[T]] = child.query(label, p)
      for ((k, v) <- nDf){
        df map(k, v)
      }
    }
    df
  }

  override def accept(v: visitor): Unit = {
    v.visit(this)
    for(child <- children){
      child.accept(v)
    }
  }
}

class File[T](str:String, file:Dataframe[T]) extends ADataframe[T]{
  private var name: String = str
  private val df: Dataframe[T] = file
  private var parent: ADataframe[T] = null

  override def setParent(parent: ADataframe[T]): Unit = this.parent = parent

  override def sort(name: String, f: Comparator[T]): List[T] = df.sort(name, f).asScala.toList

  override def query(label: String, p: Predicate[T]): Map[String, java.util.List[T]] = df.query(label, p).asScala.toMap

  override def accept(v: visitor): Unit = v.visit(this)
}


object composite extends scala.App {
  val root = new Directory("root")
  val home = new Directory("home")
  val tap = new Directory("tap")
  val f1 = new File("f1", new Dataframe(new CSVFactory(), "cities.csv"))
  val f2 = new File("f2", new Dataframe(new CSVFactory(), "cities.csv"))
  val f3 = new File("f3", new Dataframe(new CSVFactory(), "cities.csv"))
  val f4 = new File("lp", new Dataframe(new CSVFactory(), "cities.csv"))

  root.addChild(home)
  root.addChild(f1)
  home.addChild(tap)
  home.addChild(f2)
  tap.addChild(f3)
  root.addChild(f4)

  println("-------------")

  println(f3)

  println("-------------")

  //println(home.sort("LatS", Comparator.comparingInt((o: Any) => o.asInstanceOf[Int])))

  //println(root.query("LonS", (p: Any) => p.asInstanceOf[String].toInt > 50).toString())

  println("-------------")

  println("Visitor code:-----filter")
  val v = new FilterVisitor(condition)
  root.accept(v)
  println("Filtered: " + v.elements)

  println("Visitor code:-----counter")
  val c = new CounterVisitor()
  root.accept(c)
  println("DataFrame files: " + c.files + " DataFrame dirs: " + c.dirs)
}
