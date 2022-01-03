package factory;

public class TXTFactory<T> implements AbstractFileReaderFactory<T>{

    public AbstractFileReader<T> fileReader(){ return new TXTFileReader<T>(); }

}
