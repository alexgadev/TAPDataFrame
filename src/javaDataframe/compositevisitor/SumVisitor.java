package javaDataframe.compositevisitor;

import java.util.*;

public class SumVisitor extends Visitor{
    private String col;
    private int sum;

    /**
     * Visitor constructor
     *
     * @param col name of the column
     */
    public SumVisitor(String col){
        this.col = col;
        sum = 0;
    }

    /**
     * Get the sum of a column
     *
     * @return the sum of all values in a column
     */
    public int getSum(){
        return sum;
    }

    /**
     * Visit a file
     *
     * @param file file to be visited
     */
    @Override
    @SuppressWarnings("unchecked")
    public void visit(File file){
        List l = file.sort(col, Comparator.naturalOrder());
        l.forEach(x -> sum += Integer.parseInt((String) x));
    }
}