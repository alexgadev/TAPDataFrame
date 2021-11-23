package factory;

import com.google.gson.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class Test {
    public static <T> void main(String[] args) throws IOException {
        AbstractFileReaderFactory factory = new CSVFactory();

        AbstractFileReader dataframe = factory.fileReader();


        Map<String, List<String>> map = dataframe.readFile();

        dataframe.readFile().forEach((key, value) -> System.out.println(key + " " + value));

    }
}