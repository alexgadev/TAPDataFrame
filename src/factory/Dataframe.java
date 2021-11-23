package factory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Dataframe {
    private Map<String, List<String>> dataframe;

    public Dataframe(AbstractFileReaderFactory reader) throws IOException {
        dataframe = reader.fileReader().readFile();
    }

    public int at(){

        return 1;
    }

}
