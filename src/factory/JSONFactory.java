package factory;

public class JSONFactory<T> implements  AbstractFileReaderFactory<T>{

    public AbstractFileReader<T> fileReader() {
        return new JSONFileReader<T>();
    }

}
