package factory;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;


public class Dataframe<T> {
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
     * @return the value of a single item (row) and column label (name)
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

    /**
     * query: return all elements where a label value fulfills a certain condition
     *
     * @param str - name of a column
     * @param f - condition on which filter elements
     * @return a Map<String, List<T>> with all elements that fulfill the conition
     */
    public Map<String, List<T>> query(String str, Predicate<T> f){
        Map<String, List<T>> df = new LinkedHashMap<>();
        List<T> values = dataframe.get(str);

        int cont = 0;
        List<Integer> arr = new ArrayList<>();
        for (T value : values){
            if (f.test(value)) {
                arr.add(cont);
            }
            cont++;
        }

        for(Map.Entry<String, List<T>> entry : dataframe.entrySet()) {
            df.putIfAbsent(entry.getKey(), new LinkedList<>());
            int i = 0; cont = 0;
            for(T val : entry.getValue()){
                try {
                    if (arr.get(i).equals(cont)) {
                        df.get(entry.getKey()).add(val);
                        i++;
                    }
                    cont++;
                }
                catch (IndexOutOfBoundsException e){
                    break;
                }
            }
        }
        return df;
    }

    /**
     * Makes the DataFrame object Iterable
     *
     * @return the dataframe object iterator
     */
    public Iterator<Map.Entry<String, List<T>>> iterator(){
        return dataframe.entrySet().iterator();
    }


    public String toString(){
        dataframe.forEach((k, v) -> System.out.println(k + ": " + v));
        return null;
    }


}
