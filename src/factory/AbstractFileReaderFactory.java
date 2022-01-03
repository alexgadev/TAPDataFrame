package factory;

public interface AbstractFileReaderFactory<T> {

    public abstract AbstractFileReader<T> fileReader();

}
