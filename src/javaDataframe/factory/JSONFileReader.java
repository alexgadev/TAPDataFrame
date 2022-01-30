package javaDataframe.factory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import com.google.gson.*;

public class JSONFileReader implements AbstractFileReader{

    @Override
    @SuppressWarnings("unchecked")
     public <T> Map<String, List<T>> readFile(String pathname) throws IOException {
        Map<String, List<T>> dataframe = new LinkedHashMap<>();

        InputStream is = new FileInputStream(pathname);
        Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
        JsonStreamParser parser = new JsonStreamParser(reader);

        JsonElement elem = parser.next();
        JsonArray jarr = elem.getAsJsonArray();

        ArrayList<String> list = new ArrayList<>();

        /*
         add to 'list' every object removing unnecessary characters
        */
        for (int i = 0; i < jarr.size(); i++) {
            String str = jarr.get(i).toString();
            str = str.replaceAll("\"", "");
            str = str.replaceAll("\\{", "");
            str = str.replaceAll("}", "");
            list.add(str);
        }

        /*
         main loop: gets each object (a row) into 'obj' as separated values
         then divide values into 's' and separate each key from its value
        */
        for (String val : list){
           String[] obj = val.split(",");
            for (String s : obj) {
                String[] strings = s.split(":");
                dataframe.putIfAbsent(strings[0], new LinkedList<>());
                dataframe.get(strings[0]).add((T) strings[1]);
            }
        }

        return dataframe;
     }
}
