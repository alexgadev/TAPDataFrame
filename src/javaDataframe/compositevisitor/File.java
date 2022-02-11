package javaDataframe.compositevisitor;

import javaDataframe.factory.Dataframe;

import java.util.*;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public class File<T> implements ADataframe {
    private String name;
    private Dataframe<T> df;

    private ADataframe parent;

    /**
     * File constructor
     *
     * @param name name of the file
     * @param df dataframe object
     */
    public File(String name, Dataframe<T> df){
        this.name = name;
        this.df = df;
    }

    /**
     * Sorts a column in a certain order
     *
     * @param name name (String) of the column
     * @param comparator sorting order (comparator)
     * @return a sorted list
     */
    @Override
    public List<T> sort(String name, Comparator comparator){
        return df.sort(name, comparator);
    }

    /**
     * Filter rows depending on a condition
     *
     * @param str name (String) of the column
     * @param f condition (Predicate) that need to be fulfilled
     * @return a map with the rows filtered
     */
    @Override
    public Map<String, List> query(String str, Predicate f) {
        return df.query(str, f);
    }

    /**
     * Shows file name
     */
    public void ls() {
        System.out.println(name);
    }

    /**
     * Get ADataframe object in a list
     *
     * @return list of ADataframe
     */
    public List<ADataframe> toList() {
        List<ADataframe> result = new LinkedList<>();
        result.add(this);
        return result;
    }

    /**
     * Set a directory  as a parent
     *
     * @param parent directory (ADataframe)
     */
    public void setParent(ADataframe parent) {
        this.parent = parent;
    }

    /**
     * Accepts a visitor to the file
     *
     * @param v visitor
     */
    public void accept(Visitor v) {
        v.visit(this);
    }

    public String toString(){
        String path = parent.toString()+ "/" + name;
        return path;
    }
}