package java.factory;

public class TXTFactory implements AbstractFileReaderFactory{

    public AbstractFileReader fileReader(){ return new TXTFileReader(); }

}
