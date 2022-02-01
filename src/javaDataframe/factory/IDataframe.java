package javaDataframe.factory;

import java.util.*;
import java.util.function.Predicate;

public interface IDataframe<T> {
    T at(String name, int row);
    T iat(int row, int col);
    List<T> getColumn(String label);
    int columns();
    int size();
    List<T> sort(String label, Comparator<T> comparator);
    Map<String, List<T>> query(String label, Predicate<T> f);
}
