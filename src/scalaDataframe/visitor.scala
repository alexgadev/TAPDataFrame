package scalaDataframe

import java.util.function.Predicate
import scala.jdk.CollectionConverters._

/**
 * Abstract definition of visitor for files and directories
 */
abstract class visitor[T] {
  def visit[R](file: File[T]): Unit
  def visit[R](dir: Directory[T]): Unit = {
    for(child <- dir.getChildren){
      child.accept(this)
    }
  }
}

/**
 * Collects all elements that fulfill a condition
 *
 * @param label - column to search
 * @param p - predicate to filter items
 * @tparam T - type parameter
 */
class FilterVisitor[T](label: String, p: Predicate[T]) extends visitor[T] {
  var df: scala.collection.mutable.Map[String, List[T]] = null

  /**
   * Get all elements that fulfill a certain condition
   *
   * @return - elements in "label" that fulfill the "p" condition
   */
  def elements: Map[String, List[T]] = df.toMap

  /**
   * Queries the dataframe visited and saves results in df
   *
   * @param file - file visited
   * @tparam R - type parameter
   */
  override def visit[R](file: File[T]): Unit = {
    for((k, v) <- file.query(label, p)){
      df.get(k) match {
        case None => df +=  (k -> v.asScala.toList)
        case Some(x:List[T]) =>
          val list: List[T] = x ::: v.asScala.toList
          df.update(k, list)
      }
    }
  }

  /**
   * Queries recursively through directories and files and saves results in df
   *
   * @param dir - directory visited
   * @tparam R - type parameter
   */
  override def visit[R](dir: Directory[T]): Unit = {
    for (mp <- dir.getChildren.map(_.query(label, p))){
      for((k, v) <- mp){
        df.get(k) match {
          case None => df +=  (k -> v.asScala.toList)
          case Some(x:List[T]) =>
            val list: List[T] = x ::: v.asScala.toList
            df.update(k, list)
        }
      }
    }
  }
}

/**
 * Counter visitor: counts how many times files or directories have been visited
 */
class CounterVisitor[T]() extends visitor[T] {
  var dirN: Int = 0
  var fileN: Int = 0

  /**
   * Number of times any file has been visited
   *
   * @return number of times any file has been visited
   */
  def files: Int = fileN

  /**
   * Times any directory has been visited
   *
   * @return number of times any directory has been visited
   */
  def dirs: Int = dirN

  /**
   * Add 1 to fileN each time any file has been visited
   *
   * @param file - file being visited
   */
  override def visit[R](file: File[T]): Unit = {
    fileN = fileN + 1
  }

  /**
   * Add 1 to dirN each time any directory has been visited
   *
   * @param dir - directory being visited
   */
  override def visit[R](dir: Directory[T]): Unit = {
    dirN = dirN + 1
  }
}
