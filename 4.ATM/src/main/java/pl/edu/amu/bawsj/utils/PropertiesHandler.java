package pl.edu.amu.bawsj.utils;

import java.io.OutputStream;
import java.util.Properties;

/**
 * Created by Rafa� on 2016-04-11.
 */
public class PropertiesHandler {
    private static PropertiesHandler propertiesHandler;
    private String cardsFile;
    private String notesFile;
    Properties prop = new Properties();
    OutputStream fileOutputStream;
    String propertiesFileName = "properties.properties";

    private PropertiesHandler() {
    }

    public static PropertiesHandler getInstance() {
        if (propertiesHandler == null)
            propertiesHandler = new PropertiesHandler();
        return propertiesHandler;
    }

    public void setCardsFileUrl(String url) {
        cardsFile = url;
    }

    public void setNotesFileUrl(String url) {
        notesFile = url;
    }

    public String getCardsFile() {
        return cardsFile;
    }

    public String getNotesFile() {
        return notesFile;
    }

    public boolean isCardsFileSet() {
        return cardsFile != null;
    }

    public boolean isNotesFileSet() {
        return notesFile != null;
    }
}
