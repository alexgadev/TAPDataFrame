package javaDataframe.mapreduce;

import javaDataframe.factory.CSVFactory;
import javaDataframe.factory.Dataframe;

import java.io.IOException;

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


        mp.mapQuery("LonS", p ->  (Integer.parseInt((String) p) == 0));

        mp.reduce().forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
