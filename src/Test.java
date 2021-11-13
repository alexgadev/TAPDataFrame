import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        AbstractFileReaderFactory factory = new CSVFactory();

        AbstractFileReader file = factory.fileReader();

        file.readFile().forEach(System.out::println);

        /*
        factory = new JSONFactory();

        file = factory.fileReader();

        file.readFile().forEach(System.out::println);

        factory = new TXTFactory();

        file = factory.fileReader();

        file.readFile().forEach(System.out::println);
        */
    }
}