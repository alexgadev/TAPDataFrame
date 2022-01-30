package javaDataframe.observerproxy;

import javaDataframe.factory.CSVFactory;
import javaDataframe.factory.Dataframe;
import javaDataframe.factory.IDataframe;

import javaDataframe.factory.*;

import java.io.IOException;
import java.util.*;

public class MainTest {
    public static void main(String[] args) throws IOException {
        IDataframe df = (IDataframe) DynamicProxy.newInstance(
                new Dataframe(new CSVFactory(), "cities.csv"));
        System.out.println(df.sort("LatD", Comparator.comparingInt(o -> Integer.parseInt((String) o))));
    }
}
