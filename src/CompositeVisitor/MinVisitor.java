package compositevisitor;

import java.util.Comparator;

public class MinVisitor extends Visitor{

    private String col;
    private int min;

    public MinVisitor(String col){
        this.col = col;
        min = Integer.MAX_VALUE;
    }

    public int getMin(){ return min; }

    @Override
    public void visit(File file){
        min = (Integer.parseInt((String) file.sort(col,
                Comparator.comparingInt(o -> Integer.parseInt((String) o))).get(0)));
    }
}
