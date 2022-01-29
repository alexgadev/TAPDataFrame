package factory;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

public class Test {
    @SuppressWarnings("unchecked")
    public static <T> void main(String[] args) throws IOException {
        Dataframe<T> df = new Dataframe(new CSVFactory(), "cities.csv");

        //System.out.println(df);
        System.out.println(df.iat(2, 8));
        /*while (df.hasNext()){
            System.out.println(df.next());
            
        }*/

        //System.out.println(df.at("LonS", 2));
        //System.out.println(df.iat(2, 8));
        //System.out.println(df.columns());
        //System.out.println(df.size());
        //System.out.println(df.sort("LonD", Comparator.comparingInt(o -> Integer.parseInt((String) o))));   // bug, if used it modifies the dataframe object

        //Map<String, List<T>> result = df.query("City", p -> ((String) p).startsWith("U")); // Cities que empiezan por U
        //Map<String, List<T>> result = df.query("LonS", p ->  (Integer.parseInt((String) p) > 50)); // lonD > 80
        //Map<String, List<T>> result = df.query("City", Predicate.isEqual("Weed")); // Cities == Weed
        //result.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}