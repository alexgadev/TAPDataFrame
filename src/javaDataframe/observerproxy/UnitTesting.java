package javaDataframe.observerproxy;

import javaDataframe.factory.CSVFactory;
import javaDataframe.factory.Dataframe;
import javaDataframe.factory.IDataframe;
import javaDataframe.mapreduce.MapReduce;
import javaDataframe.mapreduce.MapSort;
import javaDataframe.mapreduce.Reduce;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public class UnitTesting<T> {
    ObserverMonitor monitor = new ObserverMonitor();

    Predicate<T> f = p ->  (Integer.parseInt((String) p) == 0);

    IDataframe df = (IDataframe) DynamicProxy.newInstance(
            new Dataframe(new CSVFactory(), "cities.csv"), monitor);

    QueryObserver obsQ = new QueryObserver("LonM" , f);
    LogObserver obsL = new LogObserver();
    public UnitTesting() throws IOException {
        monitor.attach(obsQ);
        monitor.attach(obsL);
    }

    @Test
    public void testLogObserver() {
        int i = 0;
        while(i < 23) {
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
            i++;
        }

        Map<String, Integer> expected = new LinkedHashMap<>();
        expected.put("sort", 23 * 3);
        expected.put("query", 23 * 2);
        expected.put("at", 23 * 2);
        expected.put("size", 23);
        expected.put("columns", 23 * 2);

        System.out.println("-> TESTING LOG OBSERVER...");
        Assert.assertEquals(obsL.getLog(), expected);
    }

    @Test
    public void testQueryObserver(){
        int i = 0;
        while(i < 41){
            df.query("LonM", f);
            df.query("City", p -> ((String) p).startsWith("U"));
            i++;
        }
        int expected = 41;

        System.out.println("-> TESTING QUERY OBSERVER...");
        Assert.assertEquals(obsQ.getTotal(), expected);
    }
}