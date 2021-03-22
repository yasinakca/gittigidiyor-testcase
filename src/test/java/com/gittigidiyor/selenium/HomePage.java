package com.gittigidiyor.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends PageObject {

    final static String BRAND_TITLE = "GittiGidiyor";
    final static String UN_AUTH_TEXT = "Giriş Yap";
    final static String AUTH_TEXT = "Hesabım";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='__next']/main/section/section/a")
    private WebElement popUpCloseButton;

    @FindBy(xpath = "//*[@id='main-header']/div[3]/div/div/div/div[1]/div/div/a")
    private WebElement brand;

    @FindBy(xpath = "//*[@id='main-header']/div[3]/div/div/div/div[3]/div/div[1]/div")
    private WebElement authSection;

    @FindBy(xpath = "//*[@id='main-header']/div[3]/div/div/div/div[3]/div/div[1]/div[2]/div/div/div")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id='main-header']/div[3]/div/div/div/div[2]/form/div/div[1]/div[2]/input")
    private WebElement searchBox;

    @FindBy(xpath = "//*[@id='main-header']/div[3]/div/div/div/div[2]/form/div/div[2]/button")
    private WebElement searchButton;


    public void closePopUp() {
        wait.until(ExpectedConditions.elementToBeClickable(popUpCloseButton));
        popUpCloseButton.click();
    }

    public String getBrandTitle() {
        return brand.getAttribute("title");
    }

    public String getAuthText() {
        return authSection.getText();
    }

    public LoginPage goLogin() {
        Actions builder = new Actions(driver);
        builder.moveToElement(authSection).perform();
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        builder.moveToElement(loginButton).click().perform();
        return new LoginPage(driver);
    }

    private void fillSearchBox(String searchText) {
        searchBox.clear();
        searchBox.sendKeys(searchText);
    }

    public ListPage goSearch(String searchText) {
        fillSearchBox(searchText);
        searchButton.click();
        return new ListPage(driver);
    }

}
