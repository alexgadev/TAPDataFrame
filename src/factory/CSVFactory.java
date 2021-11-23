package factory;

public class CSVFactory implements AbstractFileReaderFactory{

    public AbstractFileReader fileReader() { return new CSVFileReader(); }

}
