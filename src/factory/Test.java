package factory;

import java.io.IOException;
import java.util.*;


public class Test {
    public static <T extends Comparable<T>> void main(String[] args) throws IOException {
        Dataframe<T> df = new Dataframe(new TXTFactory<T>());

        String str = "LonD";


        System.out.println(df.at(2, str));
        System.out.println(df.iat(2, 4));
        System.out.println(df.columns());
        System.out.println(df.size());
        System.out.println(df.sort(str, df.intAscending));

        //System.out.println(df.query());

    }
}