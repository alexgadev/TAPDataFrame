package compositevisitor;

import factory.CSVFactory;
import factory.Dataframe;
import factory.TXTFactory;

import java.io.IOException;
import java.util.List;

public class CompositeTest {

    public static void main(String[] args) throws IOException {
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

        // Assign child to each parent
        root.addChild(home);
        home.addChild(eu);
        home.addChild(jp);
        eu.addChild(f1);
        eu.addChild(f2);
        eu.addChild(f3);
        jp.addChild(f4);

        root.ls();
        System.out.println("---------------");


        System.out.println(f3);

        System.out.println("---------------");


        eu.query("LonS", p ->  (Integer.parseInt((String) p) > 50)).forEach((k, v) -> System.out.println(k + ": " + v));


        System.out.println("---------------");

        List<ADataframe> result = root.toList();
        result.forEach(System.out::println);

        System.out.println("Visitor code:-----max");
        MaxVisitor maxv = new MaxVisitor("LonD");
        root.accept(maxv);
        System.out.println("Max: " + maxv.getMax());

        System.out.println("Visitor code:-----min");
        MinVisitor minv = new MinVisitor("LatD");
        root.accept(minv);
        System.out.println("Min: " + minv.getMin());

        System.out.println("Visitor code:-----average");
        AverageVisitor averv = new AverageVisitor("LonS");
        root.accept(averv);
        System.out.println("Average: " + averv.getAverage());

        System.out.println("Visitor code:-----sum");
        SumVisitor sumv = new SumVisitor("LonS");
        root.accept(sumv);
        System.out.println("Sum: " + sumv.getSum());
    }
}
