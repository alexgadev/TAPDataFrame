package java.compositevisitor;

import java.util.*;

public class SumVisitor extends Visitor{

    private String col;
    private int sum;

    public SumVisitor(String col){
        this.col = col;
        sum = 0;
    }

    public int getSum(){
        return sum;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void visit(File file){
        List l = file.sort(col, Comparator.naturalOrder());
        l.forEach(x -> sum += Integer.parseInt((String) x));
    }
}