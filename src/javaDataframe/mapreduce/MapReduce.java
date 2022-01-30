package javaDataframe.mapreduce;

import javaDataframe.factory.Dataframe;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

//TODO: mapping operation of sorted dataframes
//TODO: map the query operation
//TODO: reduce operation to join operations performed over the collection of dataframes into a single dataframe object

public class MapReduce<T> {
    private List<Dataframe> dataframes;

    public List<T> mapSort(String label, Comparator<T> comparator){
        List<T> l = new LinkedList<>();

        return null;
    }

    /*
    public Map<String, List<T>> reduce(){


    }

     */

}
