
public class JSONFactory implements  AbstractFileReaderFactory{

    public AbstractFileReader fileReader() {
        return new JSONFileReader();
    }

}
