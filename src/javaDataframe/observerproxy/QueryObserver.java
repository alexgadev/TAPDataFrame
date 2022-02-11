package javaDataframe.observerproxy;

import java.util.function.Predicate;

public class QueryObserver<T> extends Observer {
    private int queries = 0;
    private String label;
    private Predicate<T> function;

    public QueryObserver(String label, Predicate<T> function){
        this.label = label;
        this.function = function;
    }

    /**
     * Increase the number of queries with a determined predicate exectued
     *
     * @param args array with name of function and parameters with which it has been executed
     */
    @Override
    public void update(Object[] args) {
        try {
            if (label.equalsIgnoreCase((String) args[1]) && function.toString().equalsIgnoreCase(args[2].toString())) {
                queries++;
            }
        }
        catch(ArrayIndexOutOfBoundsException ignored){}
    }

    /**
     * Show number of times the determined query has been executed
     */
    public void getCount(){
        System.out.println("Queries with specified predicate executed: " + queries);
    }

    /**
     * Gets total times the query has been executed
     *
     * @return the number of times a determined query has been executed
     */
    public int getTotal(){ return queries;}
}
