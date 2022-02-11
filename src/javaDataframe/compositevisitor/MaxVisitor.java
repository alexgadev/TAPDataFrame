package javaDataframe.compositevisitor;

import java.util.Comparator;

public class MaxVisitor extends Visitor{

    private String col;
    private int max;

    /**
     * Visitor constructor
     *
     * @param col name of the column
     */
    public MaxVisitor(String col){
        this.col = col;
        max = 0;
    }

    /**
     * Get maximum value of a column
     *
     * @return the maximum value in a column
     */
    public int getMax(){ return max; }

    /**
     * Visits a file
     *
     * @param file file to be visited
     */
    @Override
    public void visit(File file){
        max = (Integer.parseInt((String) file.sort(col,
                Comparator.comparingInt(o -> Integer.parseInt((String) o)).reversed()).get(0)));
    }

}
