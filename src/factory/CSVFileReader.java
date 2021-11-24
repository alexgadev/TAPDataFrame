package factory;

import java.io.*;
import java.util.*;

public class CSVFileReader implements AbstractFileReader{

    @Override
    public  Map<String, List<String>> readFile() throws FileNotFoundException {
        Map<String, List<String>> dataframe = new LinkedHashMap<>();
        Scanner sc = new Scanner(new File("cities.csv"));

        String str = sc.nextLine();
        String[] keys = str.split(",");
        for (int i = 0; i < keys.length; i++){
            keys[i] = keys[i].replaceAll("\"", "");
            keys[i] = keys[i].replaceAll("\\s", "");
        }

        while(sc.hasNext()){
            str = sc.nextLine();
            str = str.replaceAll("\"", "");
            str = str.replaceAll("\\s", "");
            String[] values = str.split(",");
            for (int i = 0; i < values.length; i++) {
                dataframe.putIfAbsent(keys[i], new LinkedList<>());
                dataframe.get(keys[i]).add(values[i]);
            }
        }
        sc.close();

        return dataframe;
    }
}