package javaDataframe.factory;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

import org.junit.*;
import org.junit.Test;

public class UnitTesting {
    private String[] keys = {"LatD", "LatM", "LatS", "NS", "LonD", "LonM", "LonS", "EW", "City", "State"};

    @Test
    public void testAt() throws IOException{
        String name = "LonS";
        int row = 2;
        int result = 36;
        Dataframe df = new Dataframe(new CSVFactory(), "cities.csv");
        System.out.println("-> TESTING AT...");
        Assert.assertTrue((Integer.parseInt((String) df.at(name, row))==result));
    }

    @Test
    public void testIat() throws IOException {
        int row = 2;
        int col = 8;
        String result = "Yakima";
        Dataframe df = new Dataframe(new CSVFactory(), "cities.csv");
        System.out.println("-> TESTING IAT...");
        Assert.assertEquals(df.iat(row, col), result);
    }

    @Test
    public void testColumns() throws IOException {
        int result = 10;
        Dataframe df = new Dataframe(new CSVFactory(), "cities.csv");
        System.out.println("-> TESTING COLUMNS...");
        Assert.assertEquals(df.columns(), result);
    }

    @Test
    public void testSize() throws IOException {
        int result = 128;
        Dataframe df = new Dataframe(new CSVFactory(), "cities.csv");
        System.out.println("-> TESTING SIZE...");
        Assert.assertEquals(df.size(), result);
    }

    @Test
    public <T> void testSort() throws IOException {
        List<T> result = new LinkedList<>();
        int[] arr = {71, 72, 72, 72, 73, 73, 74, 75, 75, 75, 75, 75, 75, 75,
                76, 77, 77, 77, 77, 77, 77, 78, 79, 79, 79, 79, 79, 80, 80, 80,
                80, 80, 80, 81, 81, 81, 82, 82, 82, 82, 82, 82, 83, 83, 83, 83,
                84, 84, 84, 85, 86, 86, 87, 87, 87, 87, 87, 88, 89, 89, 89, 89,
                89, 89, 90, 90, 92, 92, 93, 93, 93, 93, 94, 94, 94, 95, 95, 95,
                96, 96, 96, 96, 97, 97, 97, 97, 97, 97, 97, 98, 98, 98, 100, 103,
                103, 103, 104, 104, 104, 105, 106, 106, 109, 110, 111, 112, 114,
                117, 117, 117, 117, 118, 119, 119, 120, 120, 121, 121, 121, 121,
                122, 122, 122, 122, 122, 122, 123, 123};
        for(int i = 0; i < arr.length; i++){
            result.add((T) String.valueOf(arr[i]));
        }
        Dataframe df = new Dataframe(new CSVFactory(), "cities.csv");
        System.out.println("-> TESTING SORT IN ASCENDING ORDER...");
        Assert.assertEquals(df.sort("LonD", Comparator.comparingInt(o -> Integer.parseInt((String) o))), result);
    }

    @Test
    public <T> void testSort2() throws IOException {
        List<T> result = new LinkedList<>();
        String[] arr = {"AL", "AL", "AZ", "BC", "CA", "CA", "CA", "CA", "CA", "CA", "CA", "CA", "CA", "CA",
                "CA", "CA", "CO", "CO", "CO", "CT", "DC", "DE", "FL", "FL", "FL", "FL", "FL", "GA", "GA", "GA",
                "GA", "IA", "IA", "ID", "IL", "IL", "IL", "IN", "IN", "IN", "IN", "KS", "KS", "KS", "LA", "MA",
                "MA", "MB", "MD", "MI", "MI", "MI", "MI", "MN", "MN", "MN", "MO", "MO", "MO", "MO", "MS", "MS",
                "NB", "NC", "NC", "NC", "ND", "ND", "NJ", "NM", "NM", "NV", "NY", "NY", "NY", "NY", "NY", "OH",
                "OH", "OH", "OH", "OH", "OH", "OK", "OK", "ON", "OR", "PA", "PA", "PA", "PA", "PA", "PA", "SA",
                "SC", "SD", "SD", "SD", "TX", "TX", "TX", "TX", "TX", "TX", "TX", "TX", "UT", "UT", "VA", "VA",
                "VA", "VA", "VT", "VT", "WA", "WA", "WA", "WA", "WA", "WA", "WI", "WI", "WI", "WI", "WV", "WV",
                "WY", "WY"};
        for(int i = 0; i < arr.length; i++){
            result.add((T) arr[i]);
        }
        Dataframe df = new Dataframe(new CSVFactory(), "cities.csv");
        System.out.println("-> TESTING SORT IN ALPHABETICAL ORDER...");
        Assert.assertEquals(df.sort("State", Comparator.comparing(o -> (String) o)), result);
    }

    @Test
    @SuppressWarnings("unchecked")
    public <T> void testQuery() throws IOException {
        Map<String, List<T>> result = new LinkedHashMap<>();
        Object[] obj ={String.valueOf(32), String.valueOf(20), String.valueOf(59),
                "N", String.valueOf(95), String.valueOf(18), String.valueOf(0), "W", "Tyler", "TX"};
        for(int i = 0; i < keys.length; i++){
            result.putIfAbsent(keys[i], new LinkedList<>());
        }
        for(int i = 0; i < keys.length; i++){
            result.get(keys[i]).add((T) obj[i]);
        }

        Dataframe df = new Dataframe(new CSVFactory(), "cities.csv");
        System.out.println("-> TESTING QUERY WITH CITY = TYLER...");
        Assert.assertEquals(df.query("City", Predicate.isEqual("Tyler")), result);
    }

    @Test
    @SuppressWarnings("unchecked")
    public <T> void testQuery2() throws IOException {
        Map<String, List<T>> result = new LinkedHashMap<>();
        Object[][] values = {{String.valueOf(41), String.valueOf(28), String.valueOf(46), String.valueOf(38)},
                {String.valueOf(15), String.valueOf(48), String.valueOf(55), String.valueOf(31)},
                {String.valueOf(0), String.valueOf(35), String.valueOf(11), String.valueOf(47)},
                {"N", "N", "N", "N"},
                {String.valueOf(77), String.valueOf(97), String.valueOf(98), String.valueOf(106)},
                {String.valueOf(0), String.valueOf(0), String.valueOf(0), String.valueOf(0)},
                {String.valueOf(0), String.valueOf(36), String.valueOf(36), String.valueOf(0)},
                {"W", "W", "W", "W"},
                {"Williamsport", "Victoria", "ValleyCity", "Salida"},
                {"PA", "TX", "ND", "CO"}};

        for(int i = 0; i < keys.length; i++){
            result.putIfAbsent(keys[i], new LinkedList<>());
            for(int j = 0; j < values[i].length; j++){
                result.get(keys[i]).add((T) values[i][j]);
            }
        }

        Dataframe df = new Dataframe(new CSVFactory(), "cities.csv");
        System.out.println("-> TESTING QUERY WITH LONM = 0...");
        Assert.assertEquals(df.query("LonM", p ->  (Integer.parseInt((String) p) == 0)), result);
    }


}