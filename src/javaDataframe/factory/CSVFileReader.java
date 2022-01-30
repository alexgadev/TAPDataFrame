package javaDataframe.factory;

import java.io.*;
import java.util.*;

public class CSVFileReader implements AbstractFileReader{

    @Override
    @SuppressWarnings("unchecked")
    public <T> Map<String, List<T>> readFile(String pathname) throws FileNotFoundException {
        Map<String, List<T>> dataframe = new LinkedHashMap<>();
        Scanner sc = new Scanner(new File(pathname));

        String str = sc.nextLine();     // get first line

        /*
         first line will always have the keys, so we parse them first
         to avoid doing it every time inside the main loop
         and put them as keys of the dataframe object
        */
        String[] keys = str.split(",");
        for (int i = 0; i < keys.length; i++){
            keys[i] = keys[i].replaceAll("\"", ""); // get rid of unnecessary quotes
            keys[i] = keys[i].replaceAll("\\s", ""); // get rid of spaces to ease any future operations
            dataframe.putIfAbsent(keys[i], new LinkedList<>());
        }

        /*
         for each line and every column, get rid of unnecessary characters
         and put each column value into its column list
        */
        while(sc.hasNext()){
            str = sc.nextLine();
            str = str.replaceAll("\"", "");
            str = str.replaceAll("\\s", "");
            String[] values = str.split(",");
            for (int i = 0; i < values.length; i++) {
                dataframe.get(keys[i]).add((T) values[i]);
            }
        }
        sc.close();

        return dataframe;
    }
}