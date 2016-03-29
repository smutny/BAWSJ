package pl.edu.amu.bawsj;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafal on 3/17/16.
 */
public class FileHandler {
    private List<String[]> data;
    private String separator;
    private File file;
    public FileHandler(File file, String separator) throws IOException {
        if (file == null || separator == null)
            throw new IllegalArgumentException();
        this.separator = separator;
        this.file = file;
        data = new ArrayList<String[]>();
        parseData();
    }

    private void parseData() throws IOException {
        String line;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while((line = bufferedReader.readLine()) != null)
        {
            String[] split = line.split(separator);
            data.add(split);
        }
        bufferedReader.close();
    }

    public List<String[]> getData() {
        return data;
    }

    public void addNewRow(String row) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(row);
        bufferedWriter.close();
    }
}
