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

    /**
     * Map a query operation
     *
     * @param df a dataframe object
     * @param list a list to save the result
     * @param <T> generic type parameter
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> void map(Dataframe df, List<Map<String, List<T>>> list) {
        list.add(df.query(label, f));
    }
}
