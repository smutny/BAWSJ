package pl.edu.amu.bawsj;

import org.apache.logging.log4j.core.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by rafal on 3/29/16.
 */
public class FileHandlerTest {
    private final String TEST_FILE_PATH = "/home/rafal/Projects/budowa-aplikacji-w-rodowisku-java/files/testFile.csv";

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfBufferedReaderIsNull() throws IOException {
        new FileHandler(null, ",");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldTrowExceptionIfSeparatorIsNull() throws IOException {
        File testFile = new File(TEST_FILE_PATH);
        new FileHandler(testFile, null);
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldThrowExceptionIfFileDoesntExists() throws IOException {
        new FileHandler(new File("someNonExistingFile"), ",");
    }

    @Test
    public void shouldParseDataCorrectly() throws IOException {
        File testFile = new File(TEST_FILE_PATH);
        FileHandler fileHandler = new FileHandler(testFile, ",");
        List<String[]> data = fileHandler.getData();
        Assert.assertNotNull(data);
        Assert.assertEquals("data1", data.get(0)[0]);
        Assert.assertEquals("data2", data.get(0)[1]);
        Assert.assertEquals("data3", data.get(0)[2]);
        Assert.assertEquals("data4", data.get(1)[0]);
        Assert.assertEquals("data5", data.get(1)[1]);
        Assert.assertEquals("data6", data.get(1)[2]);
    }

    @Test
    public void shouldAddNewRowCorrectly() throws IOException {
        File testFile = File.createTempFile("test", ".csv");
        FileHandler fileHandler = new FileHandler(testFile, ",");
        fileHandler.addNewRow("data7,data8,data9");
        fileHandler = new FileHandler(testFile, ",");
        List<String[]> data = fileHandler.getData();
        String[] strings = data.get(data.size() - 1);
        Assert.assertEquals("data7", strings[0]);
        Assert.assertEquals("data8", strings[1]);
        Assert.assertEquals("data9", strings[2]);
    }

    @Test
    public void shouldReplaceLineCorrectly() throws IOException {
        File testFile = File.createTempFile("test", ".csv");
        FileHandler fileHandler = new FileHandler(testFile, ",");
        fileHandler.addNewRow("10,3");
        fileHandler.addNewRow("20,4");
        fileHandler.addNewRow("50,5");
        fileHandler.replaceTextInFile("10,3", "10,6");
        List<String[]> data = fileHandler.getData();
        Assert.assertEquals("10", data.get(0)[0]);
        Assert.assertEquals("6", data.get(0)[1]);
        Assert.assertEquals("20", data.get(1)[0]);
        Assert.assertEquals("4", data.get(1)[1]);
        Assert.assertEquals("50", data.get(2)[0]);
        Assert.assertEquals("5", data.get(2)[1]);
    }
}
