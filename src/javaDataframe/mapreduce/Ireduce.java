package javaDataframe.mapreduce;

import java.util.List;
import java.util.Map;

public interface Ireduce {
    public <T> Map<String, List<T>> reduce(List<Map<String, List<T>>> df);
}
