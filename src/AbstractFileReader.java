//Abstract Product

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface AbstractFileReader {

    public List<City> readFile() throws FileNotFoundException, IOException;

}
