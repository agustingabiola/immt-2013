package immt.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton class to manage the configurations of the application.
 */
public class ConfigurationManager {

    private static Properties properties = null;
    private static Logger logger = LoggerFactory.getLogger(ConfigurationManager.class);

    private static Properties getInstance() {
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(new FileInputStream("config.properties"));
            } catch (IOException ex) {
                logger.error("Error retrieving config file.");
            }
        }
        return properties;
    }

    public static String getAppSetting(String appSetting) {
        Properties prop = getInstance();
        return prop.getProperty(appSetting);
    }
    
    public static void setAppSetting(String appsetting, String value)
    {
        Properties prop = getInstance();
        prop.setProperty(appsetting,value);    
    }
    
    public static void saveProperties()
    {        
        try {
            FileOutputStream out = new FileOutputStream("config.properties");
            properties.store(out, "");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}