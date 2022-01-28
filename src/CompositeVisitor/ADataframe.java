package compositevisitor;

import java.util.*;
import java.util.function.Predicate;

public interface ADataframe {

    void ls();
    List<ADataframe> toList();
    void setParent(ADataframe parent);

    <T> List<T> sort(String name, Comparator<T> comparator);
    <T> Map<String, List<T>> query(String str, Predicate<T> f);

    void accept(Visitor v);
}
