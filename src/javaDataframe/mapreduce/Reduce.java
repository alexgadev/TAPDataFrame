package javaDataframe.mapreduce;

import java.util.*;

public class Reduce implements Ireduce{

    /**
     * Reduce operation which joins all the Map operation results in one map
     *
     * @param df list with the result of every Map operation
     * @param <T> generic type parameter
     * @return map results joined
     */
    @Override
    public <T> Map<String, List<T>> reduce(List<Map<String, List<T>>> df) {
        Map<String, List<T>> result = new LinkedHashMap<>();
        // for every map result
        for (Map<String, List<T>> map : df){
            // for every entry in a map
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
