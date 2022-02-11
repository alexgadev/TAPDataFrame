package javaDataframe.mapreduce;

import javaDataframe.factory.Dataframe;

import java.util.*;


public class MapReduce<T> {
    private List<Dataframe> dataframes;
    private List<Map<String, List<T>>> df; // reduce result

    public MapReduce(){
        dataframes = new LinkedList<>();
        df = new LinkedList<>();
    }

    /**
     * Add a dataframe to the collection of dataframes
     *
     * @param df a dataframe object
     */
    public void addDataframe(Dataframe df){
        dataframes.add(df);
    }

    /**
     * Remove a certain dataframe from the collection
     *
     * @param df a dataframe object
     */
    public void removeDataframe(Dataframe df){
        dataframes.remove(df);
    }

    /**
     * Execute the mapreduce operation with a map function and a reduce function
     *
     * @param func1 a map operation
     * @param func2 a reduce operation
     * @return the mapreduce result
     */
    public Map<String, List<T>> mapReduce(Imap func1, Ireduce func2){
        dataframes.stream().parallel().forEach(d -> func1.map(d, df));
        return func2.reduce(df);
    }

}
