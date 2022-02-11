package javaDataframe.compositevisitor;

import javaDataframe.factory.CSVFactory;
import javaDataframe.factory.Dataframe;
import javaDataframe.factory.TXTFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public class UnitTesting {
    private String[] keys = {"LatD", "LatM", "LatS", "NS", "LonD", "LonM", "LonS", "EW", "City", "State"};
    // Dataframes
    Dataframe df = new Dataframe(new TXTFactory(), "cities.txt");
    Dataframe df1 = new Dataframe(new CSVFactory(), "cities.csv");
    Dataframe df2 = new Dataframe(new CSVFactory(), "cities.csv");

    // Nodes
    Directory root = new Directory("root");
    Directory home = new Directory("home");
    Directory eu = new Directory("EU");
    Directory jp = new Directory("Japan");

    // Leaves
    File f1 = new File("Spain", df);
    File f2 = new File("France", df1);
    File f3 = new File("Germany", df2);
    File f4 = new File("Tokyo", df);

    public UnitTesting() throws IOException {
        // Assign child to each parent
        root.addChild(home);
        home.addChild(eu);
        home.addChild(jp);
        eu.addChild(f1);
        eu.addChild(f2);
        eu.addChild(f3);
        jp.addChild(f4);
    }

    @Test
    public void testMaxVisitor() {
        String name = "LonD";

        MaxVisitor maxv = new MaxVisitor(name);
        root.accept(maxv);

        int result = 123;

        System.out.println("-> TESTING MAX VISITOR...");
        Assert.assertEquals(maxv.getMax(), result);
    }

    @Test
    public void testMinVisitor() {
        String name = "LatD";

        MinVisitor minv = new MinVisitor(name);
        root.accept(minv);

        int result = 26;

        System.out.println("-> TESTING MIN VISITOR...");
        Assert.assertEquals(minv.getMin(), result);
    }

    @Test
    public void testAverageVisitor() {
        String name = "LonS";

        AverageVisitor averv = new AverageVisitor(name);
        root.accept(averv);

        float result = (float) 26.960938;

        System.out.println("-> TESTING AVERAGE VISITOR...");
        Assert.assertEquals(averv.getAverage(), result);
    }

    @Test
    public void testSumVisitor() {
        String name = "LonS";

        SumVisitor sumv = new SumVisitor(name);
        eu.accept(sumv);

        int result = 10353;

        System.out.println("-> TESTING SUM VISITOR...");
        Assert.assertEquals(sumv.getSum(), result);
    }

    @Test
    @SuppressWarnings("unchecked")
    public <T> void testQuery() throws IOException {
        Map<String, List<T>> result = new LinkedHashMap<>();
        Object[] obj ={String.valueOf(32), String.valueOf(20), String.valueOf(59),
                "N", String.valueOf(95), String.valueOf(18), String.valueOf(0), "W", "Tyler", "TX"};

        for (String key : keys) {
            result.putIfAbsent(key, new LinkedList<>());
        }
        for(int i = 0; i < keys.length; i++){
            int j = 0;
            while (j < 3){
                result.get(keys[i]).add((T) obj[i]);
                j++;
            }
        }

        System.out.println("-> TESTING QUERY WITH CITY = TYLER...");
        Assert.assertEquals(eu.query("City", Predicate.isEqual("Tyler")), result);
    }
}