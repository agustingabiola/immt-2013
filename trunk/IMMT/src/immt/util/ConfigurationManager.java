package immt.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Singleton class to manage the configurations of the application.
 */
public class ConfigurationManager {

    private static Properties properties = null;

    private static Properties getInstance() {
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(new FileInputStream("config.properties"));
            } catch (IOException ex) {
                System.out.println("Error retrieving config file.");
            }
        }
        return properties;
    }

    public static String getAppSetting(String appSetting) {
        Properties prop = getInstance();
        return prop.getProperty(appSetting);
    }
}