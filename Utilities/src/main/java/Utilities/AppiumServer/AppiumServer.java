
package Utilities.AppiumServer;
import java.io.IOException;


public class AppiumServer {

    public static void startServer() {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
            Thread.sleep(8000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void startServer(String IpAddress, String PortNumber) {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a "+IpAddress+" -p "+PortNumber+"\"");
            Thread.sleep(8000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void stopServer() {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("taskkill /F /IM node.exe");
            runtime.exec("taskkill /F /IM cmd.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

