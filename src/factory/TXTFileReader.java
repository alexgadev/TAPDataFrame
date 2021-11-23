package factory;

import java.io.*;
import java.util.*;

public class TXTFileReader implements AbstractFileReader{

    @Override
    public Map<String, List<String>> readFile() throws IOException {
        Map<String, List<String>> dataframe = new LinkedHashMap<>();
        Scanner sc = new Scanner(new File("cities.txt"));

        String str = sc.nextLine();
        String[] keys = str.split(",");
        while(sc.hasNext()){
            str = sc.nextLine();
            str = str.replaceAll("\"", "");
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
