package factory;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;


public class Dataframe<T extends Comparable<T>> {
    private Map<String, List<T>> dataframe;

    /**
     * Dataframe object constructor
     *
     * @param reader -> factory type
     * @throws IOException - I/O Exception
     */
    public Dataframe(AbstractFileReaderFactory<T> reader) throws IOException {
        dataframe = reader.fileReader().readFile();
    }

    /**
     * at: search the value of a cell from a row and a column label
     *
     * @param row - row number
     * @param name - column label
     * @return the value of a single item (row) and colum label (name)
     */
    public String at(int row, String name){
        return (String) dataframe.get(name).get(row);
    }

    /**
     * iat: search the value of a cell from the integer position
     *
     * @param row - row number
     * @param col - col number
     * @return access a single value for a row and column by integer position
     */
    public String iat(int row, int col){
        int i = 0;
        for(Map.Entry<String, List<T>> entry : dataframe.entrySet()){
            String key = entry.getKey();
            if(i == col){
                return (String) dataframe.get(key).get(row);
            }
            i++;
        }
        return null;
    }

    /**
     * columns: returns the number of columns
     *
     * @return number of labels
     */
    public int columns(){
        return dataframe.size();
    }

    /**
     * size: number of rows
     *
     * @return number of items (rows) or 0 if there isn't any rows
     */
    public int size(){
        int i = 0;
        for(Map.Entry<String, List<T>> entry : dataframe.entrySet()) {
            List<T> value = entry.getValue();
            for (T val : entry.getValue())
                i++;
            return i;
        }
        return 0;
    }

    /**
     * sort: sorts a specified column label in a certain order
     *
     * @param name - column label to be sorted
     * @param comparator - sorting desired
     * @return the values of a column in the DataFrame following a certain order
     */
    public List<T> sort(String name, Comparator<T> comparator){
        List<T> list = dataframe.get(name);
        list.sort(comparator);
        return list;
    }

    public final Comparator<T> intAscending = new Comparator<T>() {
        @Override
        public int compare(T o1, T o2) {
            int n = Integer.parseInt((String) o1);
            int m = Integer.parseInt((String) o2);
            return Integer.compare(n, m);
        }
    };

    public final Comparator<T> intDescending = new Comparator<T>() {
        @Override
        public int compare(T o1, T o2) {
            int n = Integer.parseInt((String) o1);
            int m = Integer.parseInt((String) o2);
            return -(Integer.compare(n, m));
        }
    };



    public Map<String, List<String>> query(String str, Predicate<T> f){
        Map<String, List<String>> df = new LinkedHashMap<>();
        for(Map.Entry<String, List<T>> entry : dataframe.entrySet()) {
            df.putIfAbsent(entry.getKey(), new LinkedList<>());
            List<T> value = entry.getValue();
            for (T val : entry.getValue())
                if (f.test(val))
                    df.get(entry.getKey()).add((String) val);
        }
        return df;
    }

    /*
    public Iterator<Map.Entry<String, List<String>>> iterator(){
        return dataframe.entrySet().iterator();
    }
    */

    public String toString(){
        dataframe.forEach((k, v) -> System.out.println("Key: " + k + ", Value: " +v));
        return null;
    }


}
