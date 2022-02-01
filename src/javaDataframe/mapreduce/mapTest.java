package javaDataframe.mapreduce;

import javaDataframe.factory.CSVFactory;
import javaDataframe.factory.Dataframe;

import java.io.IOException;
import java.util.Comparator;
import java.util.function.Predicate;

public class mapTest {
    public static <T> void main(String[] args) throws IOException {
        MapReduce<T> mp = new MapReduce<T>();

        Dataframe df = new Dataframe(new CSVFactory(), "cities.csv");
        Dataframe df1 = new Dataframe(new CSVFactory(), "cities.csv");
        Dataframe df2 = new Dataframe(new CSVFactory(), "cities.csv");
        Dataframe df3 = new Dataframe(new CSVFactory(), "cities.csv");
        Dataframe df4 = new Dataframe(new CSVFactory(), "cities.csv");
        Dataframe df5 = new Dataframe(new CSVFactory(), "cities.csv");

        mp.addDataframe(df);
        mp.addDataframe(df1);
        mp.addDataframe(df2);
        mp.addDataframe(df3);
        mp.addDataframe(df4);
        mp.addDataframe(df5);


        // Average of all columns "LonS"
        mp.mapReduce(new MapSort("LonS", Comparator.comparingInt(o -> Integer.parseInt((String) o))), new Reduce())
                .values().forEach(x -> x.stream().mapToInt(i -> Integer.parseInt((String) i)).average().ifPresent(avg -> System.out.println("Average found is: " + avg)));


        // All rows where state = "OH" in a group of DataFrames
        mp.mapReduce(new MapQuery("State", Predicate.isEqual("OH")), new Reduce())
                .forEach((k, v) -> System.out.println(k + ": " +v));



    }
}