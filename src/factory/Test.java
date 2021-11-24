package factory;

import java.io.*;
import java.util.*;


public class Test {
    public static void main(String[] args) throws IOException {
        Dataframe df = new Dataframe(new TXTFactory());

        //String str = new String("LonD");

        //System.out.println(df.at(2, str));
        //System.out.println(df.iat(2, 4));
        //System.out.println(df.columns());
        //System.out.println(df.size());
        System.out.println(df.sort("LonD", Comparator.naturalOrder()));
    }
}