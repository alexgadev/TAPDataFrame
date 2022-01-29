package java.compositevisitor;

import java.util.Comparator;

public class MaxVisitor extends Visitor{

    private String col;
    private int max;

    public MaxVisitor(String col){
        this.col = col;
        max = 0;
    }

    public int getMax(){ return max; }

    @Override
    public void visit(File file){
        max = (Integer.parseInt((String) file.sort(col,
                Comparator.comparingInt(o -> Integer.parseInt((String) o)).reversed()).get(0)));
    }

}
