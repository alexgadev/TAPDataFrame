package javaDataframe.mapreduce;

import javaDataframe.factory.Dataframe;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class MapQuery implements Imap{
    private final String label;
    private final Predicate f;

    public MapQuery(String str, Predicate pre){
        label = str;
        f = pre;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void map(Dataframe df, List<Map<String, List<T>>> list) {
        list.add(df.query(label, f));
    }
}
