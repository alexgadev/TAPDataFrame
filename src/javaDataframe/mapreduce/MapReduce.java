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

    public void addDataframe(Dataframe df){
        dataframes.add(df);
    }

    public void removeDataframe(Dataframe df){
        dataframes.remove(df);
    }

    public Map<String, List<T>> mapReduce(Imap func1, Ireduce func2){
        dataframes.stream().parallel().forEach(d -> func1.map(d, df));
        return func2.reduce(df);
    }

}
