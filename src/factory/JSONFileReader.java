package factory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import com.google.gson.*;

public class JSONFileReader implements AbstractFileReader{

    @Override
     public Map<String, List<String>> readFile() throws IOException {

        Map<String, List<String>> dataframe = new LinkedHashMap<>();

        InputStream is = new FileInputStream("cities.json");
        Reader r = new InputStreamReader(is, StandardCharsets.UTF_8);
        JsonStreamParser p = new JsonStreamParser(r);

        ArrayList<String> list = new ArrayList<String>();
        JsonElement e = p.next();
        JsonArray jarr = e.getAsJsonArray();

        int len = jarr.size();
        for (int i = 0; i < len; i++) {
           String str = jarr.get(i).toString();
           str = str.replaceAll("\"", "");
           str = str.replaceAll("\\{", "");
           str = str.replaceAll("}", "");
           list.add(str);
        }

        for (String val : list){
           String[] obj = val.split(",");
           for(int i = 0; i < obj.length; i++){
               String[] strings = obj[i].split(":");
               dataframe.putIfAbsent(strings[0], new LinkedList<>());
               dataframe.get(strings[0]).add(strings[1]);
           }
        }

        return dataframe;
     }
}
