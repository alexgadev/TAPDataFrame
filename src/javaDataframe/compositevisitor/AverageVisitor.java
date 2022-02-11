package javaDataframe.compositevisitor;

import java.util.*;

public class AverageVisitor extends Visitor {

    private String col;
    private float average;
    private int fullSize;

    /**
     * Visitor constructor
     *
     * @param col name of the column
     */
    public AverageVisitor(String col){
        this.col = col;
        average = 0;
        fullSize = 0;
    }

    /**
     * Get average of a column
     *
     * @return average float
     */
    public float getAverage(){ return average / fullSize; }

    /**
     * Visit a file
     *
     * @param file file to be visited
     */
    @Override
    @SuppressWarnings("unchecked")
    public void visit(File file) {
        List l = file.sort(col, Comparator.naturalOrder());
        l.forEach(x -> average += Float.parseFloat((String) x));
        fullSize += l.size();
    }
}
