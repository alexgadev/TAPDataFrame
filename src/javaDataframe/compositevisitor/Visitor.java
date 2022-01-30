package javaDataframe.compositevisitor;

//TODO: Control unchecked cast exceptions and forbidden executions of each operation

public abstract class Visitor {
    public abstract void visit(File file);
    public void visit(Directory dir){
        for(ADataframe elem : dir.getChildren()) {
            elem.accept(this);
        }
    }
}
