package scalaDataframe

import javaDataframe.factory.Dataframe



trait ADataframe{

}

class File[T](val name:String, val df:Dataframe[T]) extends ADataframe{
  def sort(label:String ){

  }
}

object composite {




}
