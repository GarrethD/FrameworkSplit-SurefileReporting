package Epic;


import Base.Driver;
import Reporting.ExtentReport.ExtentManager;
import Reporting.ExtentReport.ExtentTestManager;
import Reporting.ExtentReport.Report;
import Utilities.ExcelSheetReader.ExcelDataReader;
import Utilities.ExcelSheetReader.ExcelFile;
import Utilities.PropertiesReader.PropReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Reporting.ExtentReport.ExtentManager.extent;

import java.lang.reflect.Method;


public class Scenario {
    private Driver driver;
    private String browserName;
    private String baseURL = "URL goes here";


    @BeforeClass
    private void Setup() {
        browserName = "chrome";
        driver = new Driver(browserName);
        ExtentManager.getReport();
        PropReader.ReadPropertiesFile("Credential.properties");
    }
    @Test
    public void ExcelReaderExample() {

        ExcelDataReader reader = new ExcelDataReader(new ExcelFile("KeywordExcelFile", "Sheet1"), "Keyword1");
        String word1 = reader.getParameterValue("Variable1");
        String word2 = reader.getParameterValue("Variable2");
        System.out.println(word1 + " " + word2);
    }
    @Test
    public void SeleniumActionExample() {
        driver.navigateToURL(baseURL);
        driver.ReloadPage();
        String title = driver.GetTabTitle();
        System.out.println(title);
    }
    @Test
    public void PropReaderExample() {

        String username = PropReader.getPropertiesFile().getProperty("ExampleUsername");
        String password = PropReader.getPropertiesFile().getProperty("ExamplePassword");
        System.out.println("The username from the prop file is:" + username);
        System.out.println("The password from the prop file is:" + password);

    }
    @Test
    public void ExtentReportExample(Method method) {
        ExtentTestManager.startTest(method.getName(), "This test checks if the reporting works after stripping it out of the framework.");
        testingReproting();
    }
    private void testingReproting() {
        Report.Pass("This is a pass message");
        Report.InfoNoScreenshot("This is a info message");
        //  Report.Fail("This is a fail Message");
    }

    @AfterClass
    private void KillDriver() {
        extent.flush();
        driver.closeFocusedBrowserTab();
    }
}
