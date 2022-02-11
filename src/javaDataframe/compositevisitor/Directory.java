package javaDataframe.compositevisitor;

import java.util.*;
import java.util.function.Predicate;

public class Directory implements ADataframe{
    private final String name;
    private List<ADataframe> children;
    private ADataframe parent = null;

    /**
     * Directory constructor
     *
     * @param name name of the directory
     */
    public Directory(String name){
        this.name = name;
        children = new LinkedList<>();
    }

    /**
     * Add child under the directory
     *
     * @param child either a file or directory
     */
    public void addChild(ADataframe child){
        children.add(child);
        child.setParent(this);
    }

    /**
     * Remove a certain child
     *
     * @param child either a file or directory to be removed
     */
    public void removeChild(ADataframe child){
        children.remove(child);
    }

    /**
     * Get all children under the directory
     *
     * @return a list of all children under the directory
     */
    public List<ADataframe> getChildren(){
        return children;
    }

    /**
     * Show path names under the directory
     */
    public void ls(){
        System.out.println(name);
        for (ADataframe child : children)
            child.ls();
    }

    /**
     * Set a Directory as a prent of this directory
     *
     * @param parent either a directory or null if the directory is the root
     */
    public void setParent(ADataframe parent){
        this.parent = parent;
    }

    /**
     * Sorts a column in all dataframes under the directory
     *
     * @param name String with the column name
     * @param comparator sorting order
     * @param <T> generic type parameter
     * @return a list with all the sorted results of sorting every child
     */
    public <T> List<T> sort(String name, Comparator<T> comparator){
        List<T> sorted = new LinkedList<>();
        for(ADataframe child : children){
            List<T> sort = child.sort(name, comparator);
            sorted.addAll(sort);
        }
        return sorted;
    }

    /**
     * Queries the rows of all dataframes under the directory
     *
     * @param str string with the column name
     * @param f condition (Predicate) that needs to be fulfilled
     * @param <T> generic type parameter
     * @return a Map with all the results of querying every child
     */
    public <T> Map<String, List<T>> query(String str, Predicate<T> f){
        Map<String, List<T>> df = new LinkedHashMap<>();
        for (ADataframe child : children){
            Map<String, List<T>> nDf = child.query(str, f);
            for(Map.Entry<String, List<T>> entry : nDf.entrySet()){
                df.putIfAbsent(entry.getKey(), new LinkedList<>());
                for(T val : entry.getValue()){
                    df.get(entry.getKey()).add(val);
                }
            }
        }
        return df;
    }

    /**
     * All ADataframes under the directory including the directory
     *
     * @return list of ADataframe
     */
    public List<ADataframe> toList(){
        List<ADataframe> result = new LinkedList<>();
        result.add(this);
        for (ADataframe child : children)
            result.addAll(child.toList());
        return result;
    }

    /**
     * Accept a visitor to the directory
     *
     * @param v visitor
     */
    public void accept(Visitor v){
        v.visit(this);
    }

    public String toString(){
        String path = "Path: /";
        if (parent != null)
            path = parent.toString()+"/";
        return path + name;
    }
}
