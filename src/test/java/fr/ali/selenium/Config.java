package fr.ali.selenium;

import java.io.File;
import java.text.MessageFormat;

public final class Config {
    public static String getUrl() {
        return MessageFormat.format(
                "http://localhost:8080/{0}", getProjectName());
    }

    public static String getProjectName(){
    	File f = new File(System.getProperty("user.dir"));
    	return f.getName();
    }
}
