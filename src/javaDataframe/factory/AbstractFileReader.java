package javaDataframe.factory;

//Abstract Product

import java.io.IOException;
import java.util.*;

public interface AbstractFileReader {

    public <T> Map<String, List<T>> readFile(String pathname) throws IOException;

}
