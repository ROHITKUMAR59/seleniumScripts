package com.test.selenium.basciscripts;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseClass {
	 
    //ThreadLocal will keep local copy of driver
    public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
 
    @BeforeTest
    //Parameter will get browser from testng.xml on which browser test to run
    @Parameters("myBrowser")
    public void beforeClass(String myBrowser) throws MalformedURLException{
 
        RemoteWebDriver driver = null;
 
        if(myBrowser.equals("chrome1")){
         
			DesiredCapabilities capability = DesiredCapabilities.chrome();
            capability.setBrowserName("chrome");
            capability.setPlatform(Platform.WINDOWS);
            driver = new RemoteWebDriver(new URL("http://172.16.124.213:5001/wd/hub"), capability);
           
        }
        else if(myBrowser.equals("chrome2")){
            new DesiredCapabilities();
			DesiredCapabilities capability = DesiredCapabilities.firefox();
            capability.setBrowserName("chrome");
            capability.setPlatform(Platform.WINDOWS);
            driver = new RemoteWebDriver(new URL("http://172.16.125.9:5001/wd/hub"), capability);
        }
 
        //setting webdriver
        setWebDriver(driver);
 
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 
    }
 
    public WebDriver getDriver() {
        return driver.get();
    }
 
    public void setWebDriver(RemoteWebDriver dr) {
    	driver.set(dr);
    }
 
    @AfterClass
    public void afterClass(){
        //getDriver().quit();
        driver.set(null);
 
    }
 
}
