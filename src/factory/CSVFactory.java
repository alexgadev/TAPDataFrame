package factory;

public class CSVFactory<T> implements AbstractFileReaderFactory<T>{

    public AbstractFileReader<T> fileReader() { return new CSVFileReader<T>(); }

}
