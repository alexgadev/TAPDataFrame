package javaDataframe.mapreduce;

import javaDataframe.factory.CSVFactory;
import javaDataframe.factory.Dataframe;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@SuppressWarnings("unchecked")
public class UnitTesting<T> {
    MapReduce<T> mp = new MapReduce<T>();

    Dataframe df = new Dataframe(new CSVFactory(), "cities.csv");
    Dataframe df1 = new Dataframe(new CSVFactory(), "cities.csv");
    Dataframe df2 = new Dataframe(new CSVFactory(), "cities.csv");
    Dataframe df3 = new Dataframe(new CSVFactory(), "cities.csv");
    Dataframe df4 = new Dataframe(new CSVFactory(), "cities.csv");
    Dataframe df5 = new Dataframe(new CSVFactory(), "cities.csv");

    public UnitTesting() throws IOException {
        mp.addDataframe(df);
        mp.addDataframe(df1);
        mp.addDataframe(df2);
        mp.addDataframe(df3);
        mp.addDataframe(df4);
        mp.addDataframe(df5);
    }

    @Test
    public void testAverageMapReduceSort() {
        String name = "LonS";

        double expected = 26.9609375;

        AtomicReference<Double> result = new AtomicReference<Double>(0.0);

        mp.mapReduce(new MapSort(name, Comparator.comparingInt(o -> Integer.parseInt((String) o))), new Reduce())
                .values().forEach(x -> x.stream().mapToInt(i -> Integer.parseInt((String) i)).average().ifPresent(result::set));

        double r = result.get();
        System.out.println("-> TESTING AVERAGE OF ALL COLUMNS WITH SORT MAPREDUCE...");
        Assert.assertEquals(expected, r);
    }
}