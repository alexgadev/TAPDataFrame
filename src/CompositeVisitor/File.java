package CompositeVisitor;

import factory.Dataframe;

import java.util.*;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public class File<T> implements ADataframe {
    private String name;
    private Dataframe<T> df;

    private ADataframe parent;

    public File(String name, Dataframe<T> df){
        this.name = name;
        this.df = df;
    }

    @Override
    public List<T> sort(String name, Comparator comparator){
        return df.sort(name, comparator);
    }

    @Override
    public Map<String, List> query(String str, Predicate f) {
        return df.query(str, f);
    }

    public void ls() {
        System.out.println(name);
    }

    public List<ADataframe> toList() {
        List<ADataframe> result = new LinkedList<>();
        result.add(this);
        return result;
    }

    public void setParent(ADataframe parent) {
        this.parent = parent;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public String toString(){
        String path = parent.toString()+ "/" + name;
        return path;
    }
}