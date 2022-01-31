package javaDataframe.observerproxy;

import javaDataframe.factory.CSVFactory;
import javaDataframe.factory.Dataframe;
import javaDataframe.factory.IDataframe;


import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

public class MainTest {
    @SuppressWarnings("unchecked")
    public static <T> void main(String[] args) throws IOException {
        ObserverMonitor monitor = new ObserverMonitor();

        Predicate<T> f = p ->  (Integer.parseInt((String) p) == 0);

        monitor.attach(new QueryObserver("LonM" , f));
        monitor.attach(new LogObserver());

        IDataframe df = (IDataframe) DynamicProxy.newInstance(
                new Dataframe(new CSVFactory(), "cities.csv"), monitor);

        df.sort("LatD", Comparator.comparingInt(o -> Integer.parseInt((String) o)));
        df.sort("LatD", Comparator.comparingInt(o -> Integer.parseInt((String) o)));
        df.sort("LatD", Comparator.comparingInt(o -> Integer.parseInt((String) o)));
        df.query("LonM", f);
        df.query("City", p -> ((String) p).startsWith("U"));

        df.at("LonD", 4);
        df.at("LatS", 37);

        df.size();

        df.columns();
        df.columns();

        monitor.getState();

    }


}
