package website;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataDriven {

    public String getGlobalData(String key) throws IOException {
        String projectPath = System.getProperty("user.dir");
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(projectPath+"\\user.properties");
        prop.load(fis);
        String data = prop.getProperty(key);
        fis.close();
        return data;
    }
}
