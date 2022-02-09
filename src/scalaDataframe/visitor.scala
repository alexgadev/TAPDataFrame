package scalaDataframe

abstract class visitor {
  def visit[T](file: File[T]): Unit
  def visit[T](dir: Directory[T]) = {
    for(child <- dir.getChildren){
      child.accept(this)
    }
  }
}

class FilterVisitor[T](label: String , f: T => Boolean) extends visitor {
  var df: Map[String, List[T]] = null

  def elements: Map[String, List[T]] = df

  override def visit[T](file: File[T]): Unit = {
    //file.getDataframe

  }
}

class CounterVisitor() extends visitor {
  var dirN: Int = 0
  var fileN: Int = 0

  def files: Int = fileN

  def dirs: Int = dirN

  override def visit[T](file: File[T]): Unit = {
    fileN = fileN + 1
  }

  override def visit[T](dir: Directory[T]): Unit = {
    dirN = dirN + 1
  }
}
