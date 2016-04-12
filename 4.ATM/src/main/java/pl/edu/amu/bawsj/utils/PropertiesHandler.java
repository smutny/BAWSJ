package pl.edu.amu.bawsj.utils;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by Rafa� on 2016-04-11.
 */
public class PropertiesHandler {
    private static PropertiesHandler propertiesHandler;

    Properties prop = new Properties();
    OutputStream fileOutputStream;
    String propertiesFileName = "properties.properties";

    private PropertiesHandler() {
//        InputStream propertiesStream = PropertiesHandler.class.getClassLoader().getResourceAsStream(propertiesFileName);
//        URL resourceUrl = PropertiesHandler.class.getClassLoader().getResource(propertiesFileName);
//        File file = new File(resourceUrl.toURI().toString());
//        FileInputStream fileInputStream = new FileInputStream(propertiesFileName);
//        prop.load(fileInputStream);
//        fileOutputStream = new FileOutputStream(propertiesFileName);
//        FileOutputStream fileOutputStream = PropertiesHandler.class.getClassLoader().get("properties.properties");
//        prop.load(propertiesStream);
    }

    public static PropertiesHandler getInstance() {
        if (propertiesHandler == null)
            propertiesHandler = new PropertiesHandler();
        return propertiesHandler;
    }

    public void setProperty(String propertyName, String value) throws IOException {
        prop.setProperty(propertyName, value);
        prop.store(fileOutputStream, null);
    }

    public String getProperty(String propertyName) {
        return prop.getProperty(propertyName);
    }

    public boolean propertyExists(String propertyName) {
        return getProperty(propertyName) != null;
    }
}
