package com.gittigidiyor.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class ListPage extends PageObject {

    private static final int TOTAL_ITEM = 48;

    public ListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='best-match-right']/div[4]/div[2]/ul/li")
    private List<WebElement> firstPageItems;

    @FindBy(xpath = "//*[@id='best-match-right']/div[3]/div[2]/ul/li")
    private List<WebElement> otherPageItems;

    @FindBy(xpath = "//*[@id='best-match-right']/div[5]/ul/li")
    private List<WebElement> paginationItems;

    public void clickPagination(int page) {
        WebElement element = paginationItems.get(page);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public DetailPage clickRandomElement() {
        int random = new Random().nextInt(TOTAL_ITEM);
        WebElement element = otherPageItems.get(random);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return new DetailPage(driver);
    }

    public void clickRandomElementAtFirstPage() {
        int random = new Random().nextInt(48);
        WebElement element = firstPageItems.get(random);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

}
