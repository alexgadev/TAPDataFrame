package javaDataframe.mapreduce;

import javaDataframe.factory.Dataframe;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    @SuppressWarnings("unchecked")
    public void mapQuery(String label, Predicate<T> f){
        dataframes.stream().parallel().forEach(o -> df.add(o.query(label, f)));
    }

    public Map<String, List<T>> reduce() {
        Map<String, List<T>> result = new LinkedHashMap<>();
        for (Map<String, List<T>> map : df){
            for(Map.Entry<String, List<T>> entry : map.entrySet()){
                result.putIfAbsent(entry.getKey(), new LinkedList<>());
                for(T val : entry.getValue()){
                    result.get(entry.getKey()).add(val);
                }
            }
        }
        return result;
    }

}
