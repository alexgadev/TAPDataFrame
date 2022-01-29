package factory;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

//TODO: Control NullPointerException when a label can't be found

public class Dataframe<T> implements IDataframe<T>, Iterator<String>{
    private Map<String, List<T>> dataframe;

    /**
     * factory.Dataframe object constructor
     *
     * @param reader -> factory type
     * @throws IOException - I/O Exception
     */
    public Dataframe(AbstractFileReaderFactory reader, String pathname) throws IOException {
        dataframe = reader.fileReader().readFile(pathname);
    }

    /**
     * at: search the value of a cell from a row and a column label
     *
     * @param name - column label
     * @param row - row number
     * @return the value of a single item (row) and column label (name)
     */
    public T at(String name, int row){
        return dataframe.get(name).get(row);
    }

    /**
     * iat: search the value of a cell from the integer position
     *
     * @param row - row number
     * @param col - col number
     * @return access a single value for a row and column by integer position
     */
    public T iat(int row, int col){
        int i = 0;
        for(Map.Entry<String, List<T>> entry : dataframe.entrySet()){
            String key = entry.getKey();
            if(i == col){
                return dataframe.get(key).get(row);
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
     * @return number of items (rows) or 0 if there are no rows
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
        try {
            List<T> list = dataframe.get(name);
            list.sort(comparator);
            return list;
        }
        catch (NullPointerException e){
            //print sth
            return null;
        }
    }

    /**
     * query: return all elements where a label value fulfills a certain condition
     *
     * @param str - name of a column
     * @param f - condition on which filter elements
     * @return a Map<String, List<T>> with all elements that fulfill the condition
     */
    public Map<String, List<T>> query(String str, Predicate<T> f){
        Map<String, List<T>> df = new LinkedHashMap<>();
        List<T> values = dataframe.get(str);
        try {
            int cont = 0;
            List<Integer> arr = new ArrayList<>();
            for (T value : values) {
                if (f.test(value)) {
                    arr.add(cont);
                }
                cont++;
            }

            for (Map.Entry<String, List<T>> entry : dataframe.entrySet()) {
                df.putIfAbsent(entry.getKey(), new LinkedList<>());
                int i = 0;
                cont = 0;
                for (T val : entry.getValue()) {
                    try {
                        if (arr.get(i).equals(cont)) {
                            df.get(entry.getKey()).add(val);
                            i++;
                        }
                        cont++;
                    } catch (IndexOutOfBoundsException e) {
                        break;
                    }
                }
            }
            return df;
        }
        catch (NullPointerException e){
            // print sth
            return null;
        }
    }


    private int pos = 0;

    @Override
    public boolean hasNext() {
        return pos < size();
    }

    @Override
    public String next() {
        if(this.hasNext()) {
            int i = 0;
            String str = "";
            while (i < this.columns() - 1) {
                str = str + (String) (this.iat(pos, i) + ", ");
                i++;
            }
            str = str + (String) (this.iat(pos, i));
            pos++;
            return str;
        }
        else{
            return null;
        }
    }
}
