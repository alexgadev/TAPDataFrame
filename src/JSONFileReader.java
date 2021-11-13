import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JSONFileReader implements AbstractFileReader{

     public List<City> readFile() throws IOException {

          Gson gson = new Gson();

          Reader reader = Files.newBufferedReader(Paths.get("cities.json"));

          List<City> cities = new Gson().fromJson(reader,new TypeToken<List<City>>() {}.getType());

          reader.close();

          return cities;

     }

}
