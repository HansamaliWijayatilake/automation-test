package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.List;

public class LandingPage {
    WebDriver driver;
    Actions actions;

    By mainCategoryElement = By.xpath(".//span[contains(text(),'Categories')]");
    By categoryText = By.xpath(".//span[contains(text(),'Electronic Devices')]");
    By subCategoryText = By.xpath("//li[@data-cate='cate_1_3']/a/span[text()='Laptops']");
    By leafCategoryText = By.xpath("//ul[@data-spm='cate_1_3']/li[2]/a/span[text()='Gaming Laptops']");


    public LandingPage(WebDriver driver, Actions actions) {
        this.driver = driver;
        this.actions = actions;
    }

    public void hoverMainCategoryElement(){
        WebElement mainCategory = driver.findElement(mainCategoryElement);
        actions.moveToElement(mainCategory).build().perform();
    }

    public void hoverCategories() {
        WebElement categoryText = driver.findElement(this.categoryText);
        actions.moveToElement(categoryText).build().perform();
    }

    public void hoverSubCategories() {
        WebElement subCategory = driver.findElement(subCategoryText);
        actions.moveToElement(subCategory).build().perform();
    }

    public void gotoLeafCategory() {
        driver.findElement(leafCategoryText).click();
    }

    public void completeNavigationToLeaf() {
        this.hoverCategories();
        this.hoverSubCategories();
        this.gotoLeafCategory();
    }


}
