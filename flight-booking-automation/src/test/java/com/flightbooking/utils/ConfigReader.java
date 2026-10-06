package com.flightbooking.utils;

import java.io.InputStream;
import java.util.Properties;
 
public class ConfigReader {

    private static Properties prop = new Properties();

    static {
        try {
            InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
            prop.load(is);
        } catch (Exception e) {
            throw new RuntimeException("could not load config.properties", e);
        }
    }

    public static String get(String key) {
        
        String fromCmd = System.getProperty(key);
        if (fromCmd != null) {
            return fromCmd;
        }
        return prop.getProperty(key);
    }
}
