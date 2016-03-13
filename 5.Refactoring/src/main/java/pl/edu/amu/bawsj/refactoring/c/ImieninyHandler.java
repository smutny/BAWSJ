package pl.edu.amu.bawsj.refactoring.c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafal on 3/13/16.
 */
public class ImieninyHandler {
    private List<String> names = new ArrayList<String>();

    public ImieninyHandler(BufferedReader reader) throws IOException{
        if (reader == null)
            throw new IllegalArgumentException();

        prepareNames(reader);
    }

    private void prepareNames(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            names.add(line);
        }
    }

    public boolean isNameInReader(String name) {
        return names.contains(name);
    }
}
