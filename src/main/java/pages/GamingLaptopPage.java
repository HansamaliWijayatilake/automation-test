package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GamingLaptopPage {

    WebDriver driver;

    By breadcrumbList = By.cssSelector("ul.breadcrumb li span span");
    By productName = By.xpath("//div[@class='cUQuRr']/h1[text()]");

    public GamingLaptopPage(WebDriver driver){
        this.driver = driver;
    }

    public String getBreadcrumbValue(int index) {
        List<WebElement> breadcrumbs = driver.findElements(breadcrumbList);
        String breadcrumb = "";
        for (int i = 0; i < breadcrumbs.size(); i++) {
            breadcrumb = breadcrumbs.get(index).getText();
        }
        return breadcrumb;
    }

    public String getProductName(){
        String productNameString = driver.findElement(productName).getText();
        return productNameString;
    }
}
