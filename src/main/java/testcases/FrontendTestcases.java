package testcases;

import data.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.GamingLaptopPage;
import pages.LandingPage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FrontendTestcases {

    WebDriver driver;
    Actions actions;


    @BeforeClass
    public void setup (){
        System.setProperty("webdriver.chrome.driver","Libs/chromedriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://www.lazada.sg/#");

    }

    @Test(priority=0)
    public void testCase01(){

        actions = new Actions(driver);
        LandingPage landingPage = new LandingPage(driver,actions);
        landingPage.completeNavigationToLeaf();

        GamingLaptopPage gamingLaptopPage = new GamingLaptopPage(driver);
        int breadcrumIndex = 3;
        String breadcrumbValue = gamingLaptopPage.getBreadcrumbValue(breadcrumIndex);
        Assert.assertTrue(breadcrumbValue.contains("Gaming Laptops"));

        String prodcutName = gamingLaptopPage.getProductName();
        Assert.assertTrue(prodcutName.equals("Gaming"));

    }




    @AfterTest
    public void afterTest() throws Exception{

        driver.quit();
    }


}
