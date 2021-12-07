package Utilities.PropertiesReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropReader {
    public static Properties CredentialProp = new Properties();

    public static void ReadPropertiesFile(String FileName) {
        String systemDelimter = File.separator;
        try {
            CredentialProp.load(new FileInputStream(System.getProperty("user.dir").concat((systemDelimter + "Properties" + systemDelimter + FileName))));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getPropertiesFile()
    {
        return CredentialProp;
    }

}
