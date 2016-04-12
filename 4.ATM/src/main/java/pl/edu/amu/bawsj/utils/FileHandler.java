package pl.edu.amu.bawsj.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
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

        if (!file.exists())
            throw new FileNotFoundException();

        this.separator = separator;
        this.file = file;
        data = new ArrayList<String[]>();
    }

    private void parseData() throws IOException {
//        String line;
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//        while((line = bufferedReader.readLine()) != null)
        for (String line : Files.readAllLines(file.toPath(), StandardCharsets.UTF_8))
        {
            String[] split = line.split(separator);
            data.add(split);
        }
    }

    public List<String[]> getData() throws IOException {
        parseData();
        return data;
    }

    public void addNewRow(String row) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
//        bufferedWriter.write(row+"\n");
//        bufferedWriter.close();
        String text = row+"\n";
        Files.write(file.toPath(), text.getBytes(), StandardOpenOption.APPEND);
    }

    public void replaceTextInFile(String lineToReplace, String lineToReplaceWith) throws IOException {
        List<String> newLines = new ArrayList<>();
        for (String line : Files.readAllLines(file.toPath(), StandardCharsets.UTF_8)) {
            if (line.contains(lineToReplace)) {
                newLines.add(line.replace(lineToReplace, lineToReplaceWith));
            } else {
                newLines.add(line);
            }
        }
        Files.write(file.toPath(), newLines, StandardCharsets.UTF_8);
    }

    public boolean hasLineWithPrefix(String prefix) throws IOException {
        for (String line : Files.readAllLines(file.toPath(), StandardCharsets.UTF_8)) {
            if (line.startsWith(prefix))
                return true;
        }
        return false;
    }
}
