package compositevisitor;

import java.util.*;

public class AverageVisitor extends Visitor {

    private String col;
    private float average;
    private int fullSize;

    public AverageVisitor(String col){
        this.col = col;
        average = 0;
        fullSize = 0;
    }

    public float getAverage(){ return average / fullSize; }

    @Override
    @SuppressWarnings("unchecked")
    public void visit(File file) {
        List l = file.sort(col, Comparator.naturalOrder());
        l.forEach(x -> average += Float.parseFloat((String) x));
        fullSize += l.size();
    }
}
