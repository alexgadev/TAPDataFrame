package compositevisitor;

public abstract class Visitor {
    public abstract void visit(File file);
    public void visit(Directory dir){
        for(ADataframe elem : dir.getChildren()) {
            elem.accept(this);
        }
    }
}
