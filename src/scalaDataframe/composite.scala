package scalaDataframe

import javaDataframe.factory.{CSVFactory, Dataframe}

import scala.jdk.CollectionConverters._
import java.util.Comparator
import java.util.function.Predicate
import scala.collection.mutable.ListBuffer
import scala.jdk.FunctionConverters.enrichAsJavaToIntFunction

trait ADataframe[T]{
  def setParent(parent: ADataframe[T]): Unit

  def sort(name:String, comparator: Comparator[T]): List[T]

  def query(label: String, p: Predicate[T]): Map[String, java.util.List[T]]

  def accept(v: visitor[T]): Unit
}

/**
 * Directory implementation
 *
 * @param str - name of the directory
 * @tparam T - generic type parameter
 */
class Directory[T](str: String) extends ADataframe[T] {
  private var name: String = str
  private val children: ListBuffer[ADataframe[T]] = new ListBuffer[ADataframe[T]]
  private var parent: ADataframe[T] = null

  /**
   * Adds a child to the directory
   *
   * @param child - either a file or another directory
   */
  def addChild(child: ADataframe[T]): Unit = {
    children += child
    child.setParent(this)
  }

  /**
   * Removes a certain child
   *
   * @param child - file or directory to be removed
   */
  def removeChild(child: ADataframe[T]): Unit = children -= child

  /**
   * Gets all children under this directory
   *
   * @return all children under the directory
   */
  def getChildren: ListBuffer[ADataframe[T]] = children

  /**
   * Set a parent to the directory
   *
   * @param parent - either another directory or null if it's root directory
   */
  override def setParent(parent: ADataframe[T]): Unit = this.parent = parent

  /**
   * Sorts all columns with label "name" depending on "comparator" order for
   * all childs recursively
   *
   * @param name - label to be sorted
   * @param comparator - order to be sorted
   * @return a List[T] with all the results of sorting all childs
   */
  override def sort(name: String, comparator: Comparator[T]): List[T] = {
    var sorted: List[T] = Nil

    // converts the comparator to ordering to sort final sort result
    implicit def comparatorToOrdering[T](implicit cmp: Comparator[T]): Ordering[T] = new Ordering[T] {
      def compare(x: T, y: T): Int = cmp.compare(x, y)
    }

    // gets all sort results and appends them in sorted
    for (l <- children.map(_.sort(name, comparator))){
      sorted = (sorted ::: l)
    }
    sorted.sorted(comparatorToOrdering(comparator))
  }

  /**
   * Queries all rows depending if its column label fulfill a condition
   *
   * @param label - name of column
   * @param p - predicate that needs to be fulfilled
   * @return a Map with the result of querying through all childs
   */
  override def query(label: String, p: Predicate[T]): Map[String, java.util.List[T]] = {
    val df = scala.collection.mutable.Map[String, java.util.List[T]]()

    // gets all child query results
    // also an example of for loops utilisation
    for (mp <- children.map(_.query(label, p))){
      // for each result update or add it to the Map
      for((k, v) <- mp){
        df.get(k) match {
          case None => df +=  (k -> v)
          case Some(x:java.util.List[T]) =>
            val jList: java.util.List[T] = (x.asScala.toList ::: v.asScala.toList).asJava
            df.update(k, jList)
        }
      }
    }
    df.toMap
  }

  /**
   * Accepts a visitor of the directory
   *
   * @param v - visitor
   */
  override def accept(v: visitor[T]): Unit = {
    v.visit(this)
    for(child <- children){
      child.accept(v)
    }
  }

  override def toString: String = {
    var path: String = "Path: /"
    if (parent != null){
      path = parent.toString + "/"
    }
    path + name
  }
}

/**
 * File implementation with a java dataframe
 *
 * @param str - file name
 * @param file - dataframe object
 * @tparam T - type parameter
 */
class File[T](str:String, file:Dataframe[T]) extends ADataframe[T]{
  private val name: String = str
  private val df: Dataframe[T] = file
  private var parent: ADataframe[T] = null

  /**
   * Set a parent
   *
   * @param parent - directory parent
   */
  override def setParent(parent: ADataframe[T]): Unit = this.parent = parent

  /**
   * Sort a column depending on a comparator
   *
   * @param name - column to sort
   * @param f - order to sort
   * @return column name sorted
   */
  override def sort(name: String, f: Comparator[T]): List[T] = df.sort(name, f).asScala.toList

  /**
   * Query rows depending on a predicate of a column
   *
   * @param label - column to test the predicate
   * @param p - condition on which query will filter
   * @return a dataframe filtered by p
   */
  override def query(label: String, p: Predicate[T]): Map[String, java.util.List[T]] = df.query(label, p).asScala.toMap

  /**
   * Accept a visitor to the file
   *
   * @param v - visitor
   */
  override def accept(v: visitor[T]): Unit = v.visit(this)

  override def toString: String = {
    val path: String = parent.toString + "/" + name
    path
  }
}

object composite extends scala.App {
  // Nodes
  val root = new Directory("root")
  val home = new Directory("home")
  val tap = new Directory("tap")

  // Leaves
  val f1 = new File("f1", new Dataframe(new CSVFactory(), "cities.csv"))
  val f2 = new File("f2", new Dataframe(new CSVFactory(), "cities2.csv"))
  val f3 = new File("f3", new Dataframe(new CSVFactory(), "cities.csv"))
  val f4 = new File("lp", new Dataframe(new CSVFactory(), "cities.csv"))

  // Build dependency tree
  root.addChild(home)
  root.addChild(f1)
  home.addChild(tap)
  home.addChild(f2)
  tap.addChild(f3)
  root.addChild(f4)

  println("-------------")

  println(f3)

  println("-------------")

  // Must comment either sort or query so that the results shown will be the correct ones
  // Both functions are examples of runtime multiple inheritance
  println("Sort code:-----intAscending")
  println(home.sort("LonS", Comparator.comparingInt(((o:Any) => o.asInstanceOf[String].toInt).asJavaToIntFunction)))

  //println("Query code:-----LonS > 50")
  println("Query: " +home.query("LonS", (p: Any) => p.asInstanceOf[String].toInt > 50).toString())

  println("-------------")

  // FILTERVISITOR DOESN'T WORK !!!!
  //println("Visitor code:-----filter")

  //val label: String = "LonS"
  //val v = new FilterVisitor(label, (p: Any) => p.asInstanceOf[String].toInt > 50)
  //home.accept(v)
  //println("Filtered: " + v.elements)

  println("Visitor code:-----counter")
  val c = new CounterVisitor()
  home.accept(c)
  println("DataFrame files: " + c.files + " DataFrame dirs: " + c.dirs)

}
