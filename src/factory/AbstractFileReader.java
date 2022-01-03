package factory;

//Abstract Product

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AbstractFileReader<T> {

    public  Map<String, List<T>> readFile() throws FileNotFoundException, IOException;

}
