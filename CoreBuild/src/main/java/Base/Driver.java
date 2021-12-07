package Base;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;

public class Driver {

    private final int GLOBAL_TIMEOUT = 40;
    public WebDriver webDriver;
    Actions actions;
    private String browserName;

    public Driver() {
    }

    public Driver(String browserName) {
        this.browserName = browserName;
        switch (this.browserName.toUpperCase()) {

            case "CHROME":
                initializeChromeDriver();
                break;
            case "FIREFOX":
                initializeFirefoxDriver();
                break;
        }
    }


    private void initializeChromeDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }


    private void initializeFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
        webDriver.manage().window().maximize();

    }
    public WebDriver getDriver() {
        return this.webDriver;
    }

    public void navigateToURL(String URL) {
        try {
            webDriver.get(URL);
            Thread.sleep(250);
        } catch (Exception e) {
          System.out.println("Failed to navigate to webpage. URL used : " + URL + "");
        }
    }
    public void ReloadPage() {
        try {
            webDriver.navigate().refresh();
            Thread.sleep(250);
        } catch (Exception e) {
          System.out.println("Failed to reload webpage.");
        }
    }
    public void clickByXPath(String XPath) {
        try {
            waitForElementVisible(XPath);
            webDriver.findElement(By.xpath(XPath)).click();
        } catch (Exception e) {
          System.out.println("Failed to click on the element. XPath : " + XPath);
        }
    }
    public void clearTextByXPath(String XPath) {
        try {
            waitForElementVisible(XPath);
            webDriver.findElement(By.xpath(XPath)).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        } catch (Exception e) {
          System.out.println("Failed to clear text from element.  XPath : " + XPath + "");
        }
    }
    public void enterTextByXpath(String XPath, String valueToType) {
        try {
            clearTextByXPath(XPath);
            webDriver.findElement(By.xpath(XPath)).sendKeys(valueToType);
        } catch (Exception e) {
          System.out.println("Failed to enter text into element.  XPath : " + XPath + " | Text to be entered: " + valueToType + "");
        }
    }
    public void switchToDefaultContent() {
        try {
            ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(0));
        } catch (Exception e) {
          System.out.println("Failed to switch to current browser tab.");
        }
    }
    public boolean isElementVisible(String XPath) {
        int timer = 0;
        Boolean isPresent = false;
        WebElement element;
        try {
            while (timer <= GLOBAL_TIMEOUT && isPresent == false) {
                Thread.sleep(1000);
                isPresent = webDriver.findElements(By.xpath(XPath)).size() > 0;


                if (isPresent) {
                    element = webDriver.findElement(By.xpath(XPath));
                    if (element.isDisplayed() && element.isEnabled()) {
                        timer++;
                        break;
                    }
                } else {
                    timer++;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return isPresent;
    }
    public boolean isElementNotVisible(String XPath, int timeInSeconds) {
        int timer = 0;
        Boolean isPresent = false;
        try {
            while (timer <= timeInSeconds) {
                Thread.sleep(1000);
                isPresent = webDriver.findElements(By.xpath(XPath)).size() == 0;
                if (isPresent) {
                    break;
                } else {
                    timer++;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return isPresent;
    }
    public void waitForElementVisible(String XPath) {
        if (!isElementVisible(XPath)) {
          System.out.println("Could not find element. Xpath : " + XPath);
        }
    }
    public void closeFocusedBrowserTab() {
        try {
            webDriver.close();
        } catch (Exception e) {
          System.out.println("Failed to close current browser tab in focus.");
        }
    }
    public void NavigateBack()
    {
        try {
            webDriver.navigate().back();
            Thread.sleep(250);
        } catch (Exception e) {
            System.out.println("Failed to navigate to previous webpage.");
        }
    }
    public String GetTabTitle() {
        String title = "";
        try {
            title = webDriver.getTitle();
        } catch (Exception e) {
            System.out.println("Failed to get page title");
        }
        return title;
    }
}
