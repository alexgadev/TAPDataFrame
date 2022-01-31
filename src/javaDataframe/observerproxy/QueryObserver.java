package javaDataframe.observerproxy;

//TODO: add javadoc

import java.util.function.Predicate;

public class QueryObserver<T> extends Observer {
    private int queries = 0;
    private String label;
    private Predicate<T> function;

    public QueryObserver(String label, Predicate<T> function){
        this.label = label;
        this.function = function;
    }

    @Override
    public void update(Object[] args) {
        try {
            if (label.equalsIgnoreCase((String) args[1]) && function.toString().equalsIgnoreCase(args[2].toString())) {
                queries++;
            }
        }
        catch(ArrayIndexOutOfBoundsException ignored){}
    }

    public void getCount(){
        System.out.println("Queries with specified predicate executed: " + queries);
    }
}
