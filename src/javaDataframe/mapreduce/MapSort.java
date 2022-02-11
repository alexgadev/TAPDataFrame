package javaDataframe.mapreduce;

import javaDataframe.factory.Dataframe;

import java.util.*;

public class MapSort implements Imap{
    private String label;
    private Comparator comp;

    public MapSort(String str, Comparator comp){
        label = str;
        this.comp = comp;
    }

    /**
     * Map a sort operation
     *
     * @param df a dataframe object
     * @param list a list to save the result
     * @param <T> generic typ parameter
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> void map(Dataframe df, List<Map<String, List<T>>> list) {
        Map<String, List<T>> sorted = new LinkedHashMap<>();
        sorted.put(label, new LinkedList<>());
        df.sort(label, comp).forEach(o -> sorted.get(label).add((T) o));
        list.add(sorted);
    }
}
