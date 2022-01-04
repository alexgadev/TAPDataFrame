package factory;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;


public class Test {
    public static <T extends Comparable<T>> void main(String[] args) throws IOException {
        Dataframe<T> df = new Dataframe(new TXTFactory<T>());

        String str = "City";

        System.out.println(df.at(2, str));
        System.out.println(df.iat(2, 8));
        System.out.println(df.columns());
        System.out.println(df.size());
        //System.out.println(df.sort("LonD", df.intAscending));


        Map<String, List<T>> result = df.query(str, Predicate.isEqual("Weed"));
        result.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}