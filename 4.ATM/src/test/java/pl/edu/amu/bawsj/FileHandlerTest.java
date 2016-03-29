package pl.edu.amu.bawsj;

import org.apache.logging.log4j.core.util.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.io.IOUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by rafal on 3/29/16.
 */
public class FileHandlerTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfBufferedReaderIsNull() throws IOException {
        FileHandler fileHandler = new FileHandler(null, ",");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldTrowExceptionIfSeparatorIsNull() throws IOException {
        File testFile = new File(this.getClass().getClassLoader().getResource("testFile.csv").getFile());
        FileHandler fileHandler = new FileHandler(testFile, null);
    }

    @Test
    public void shouldParseDataCorrectly() throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("testFile.csv");
//        File testFile = new File(getClass().getClassLoader().getResource("testFile.csv").getFile());
        File testFile = new File(url.getPath());
        FileHandler fileHandler = new FileHandler(testFile, ",");
        List<String[]> data = fileHandler.getData();
        Assert.assertNotNull(data);
        Assert.assertEquals(data.get(0)[0], "data1");
        Assert.assertEquals(data.get(0)[1], "data2");
        Assert.assertEquals(data.get(0)[2], "data3");
        Assert.assertEquals(data.get(1)[0], "data4");
        Assert.assertEquals(data.get(1)[1], "data5");
        Assert.assertEquals(data.get(1)[2], "data6");
    }

    @Test
    public void shouldAddNewRowCorrectly() throws IOException {
        File testFile = new File(this.getClass().getClassLoader().getResource("testFile.csv").getFile());
        FileHandler fileHandler = new FileHandler(testFile, ",");
        System.out.println(testFile.getAbsolutePath());
        fileHandler.addNewRow("data7,data8,data9");
        fileHandler = new FileHandler(testFile, ",");
        List<String[]> data = fileHandler.getData();
        String[] strings = data.get(data.size() - 1);
        Assert.assertEquals("data7", strings[0]);
        Assert.assertEquals("data8", strings[1]);
        Assert.assertEquals("data9", strings[2]);
    }

}
