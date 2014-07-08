package immt;

import immt.ui.ShellWindow;
import immt.ui.WizardWindow;
import immt.util.ConfigurationManager;
import java.io.File;
import java.io.IOException;

public class IMMT {

    public static void main(String[] args) {     
        //initializeLogger();
        
        WizardWindow wizard = new WizardWindow();
        wizard.setVisible(true);
        
        //ShellWindow shell = new ShellWindow();
        //shell.setVisible(true);
    }
    
    private static void initializeLogger(){
        String loggerPath = ConfigurationManager.getAppSetting("log_path");
        String loggerFileName = ConfigurationManager.getAppSetting("log_fileName");
        
        File dir = new File(loggerPath);
        File file = new File(loggerPath + loggerFileName);
        try{
            if(!dir.exists() || !dir.isDirectory()){
               dir.mkdir();
            }
            if (!file.exists()) {
                file.createNewFile();
            }        
        }catch (IOException ex){
            System.out.println("Exception trying to initialize logger");
            ex.printStackTrace();
        }
    }

}
