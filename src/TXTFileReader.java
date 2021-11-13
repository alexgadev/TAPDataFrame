import java.io.*;
import java.util.*;

public class TXTFileReader implements AbstractFileReader{

    @Override
    public List<City> readFile() throws IOException {
        List<City> cities = new LinkedList<City>();

        Scanner scanner = new Scanner(new File("cities.txt"));
        scanner.nextLine();
        while (scanner.hasNext()){
            String str = scanner.nextLine();
            str = str.replaceAll(" ", "");
            String[] parts = str.split(",");

            City c = new City(parts);

            cities.add(c);
        }
        scanner.close();

        return cities;
    }
}
