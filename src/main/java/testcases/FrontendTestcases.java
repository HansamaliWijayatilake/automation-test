package testcases;

import data.TestData;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import pages.GamingLaptopPage;
import pages.LandingPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FrontendTestcases {

    WebDriver driver;
    Actions actions;


    @BeforeClass
    public void setup (){
        System.setProperty("webdriver.chrome.driver","ChromeDriver/chromedriver");
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

    @Test(dataProvider="DeviceList" ,dataProviderClass=TestData.class, priority = 1)
    public void testCase02(String device) {
        {
            LandingPage landingPage = new LandingPage(driver,actions);
            landingPage.hoverMainCategoryElement();
            landingPage.hoverCategories();
            List deviceList = new ArrayList();
            deviceList.add(device);
            Assert.assertTrue(landingPage.getDeviceList().containsAll(deviceList));
        }
    }

    @Test(dataProvider="childDeviceList" ,dataProviderClass=TestData.class, priority = 2)
    public void testCase03(String childDevice)  {
        {
            LandingPage landingPage = new LandingPage(driver,actions);
            landingPage.hoverMainCategoryElement();
            landingPage.hoverCategories();
            landingPage.hoverSubCategories();
            List childDeviceList = new ArrayList();
            childDeviceList.add(childDevice);
            Assert.assertTrue(landingPage.getChildDeviceList().containsAll(childDeviceList));
        }
    }


    @AfterTest
    public void afterTest() {

        driver.quit();
    }


}
