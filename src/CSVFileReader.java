import java.io.*;
import java.util.*;

public class CSVFileReader implements AbstractFileReader{

    @Override
    public List<City> readFile() throws FileNotFoundException {
        List<City> cities = new LinkedList<City>();

        Scanner sc = new Scanner(new File("cities.csv"));
        sc.nextLine(); // avoid cell names
        while (sc.hasNext()) {
            String str = sc.nextLine();
            str = str.replaceAll("\"", "");
            str = str.replaceAll("\\s", "");
            String[] part = str.split(",");

            City c = new City(part);

            cities.add(c);
        }
        sc.close();

        return cities;
    }
}
