package javaDataframe.mapreduce;

import javaDataframe.factory.Dataframe;

import java.util.*;

public interface Imap {
    public <T> void map(Dataframe df, List<Map<String, List<T>>> list);
}
