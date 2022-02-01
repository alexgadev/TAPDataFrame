package javaDataframe.mapreduce;

import java.util.*;

public class Reduce implements Ireduce{
    @Override
    public <T> Map<String, List<T>> reduce(List<Map<String, List<T>>> df) {
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
