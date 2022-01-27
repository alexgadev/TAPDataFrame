package CompositeVisitor;

import java.util.*;
import java.util.function.Predicate;

public class Directory implements ADataframe{

    private final String name;
    private List<ADataframe> children;
    private ADataframe parent = null;


    public Directory(String name){
        this.name = name;
        children = new LinkedList<ADataframe>();
    }

    public void addChild(ADataframe child){
        children.add(child);
        child.setParent(this);
    }

    public void removeChild(ADataframe child){
        children.remove(child);
    }

    public List<ADataframe> getChildren(){
        return children;
    }

    public void ls(){
        System.out.println(name);
        for (ADataframe child : children)
            child.ls();
    }

    public void setParent(ADataframe parent){
        this.parent = parent;
    }

    public <T> List<T> sort(String name, Comparator<T> comparator){
        List<T> sorted = new LinkedList<>();
        for(ADataframe child : children){
            List<T> nSort = child.sort(name, comparator);
            sorted.addAll(nSort);
        }
        return sorted;
    }

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

    public List<ADataframe> toList(){
        List<ADataframe> result = new LinkedList<>();
        result.add(this);
        for (ADataframe child : children)
            result.addAll(child.toList());
        return result;
    }

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
