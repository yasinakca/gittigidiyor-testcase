package com.gittigidiyor.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DetailPage extends PageObject {

    public DetailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "add-to-basket")
    private WebElement addBasket;

    @FindBy(xpath = "//*[@id='header_wrapper']/div[4]/div[3]")
    private WebElement basketButton;

    @FindBy(id = "sp-price-discountPrice")
    private WebElement price;

    @FindBy(xpath = "/html/body/div[8]/div/img[1]")
    private WebElement popUp;

    private void closePopUp() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(popUp));
            popUp.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPrice() {
        wait.until(ExpectedConditions.elementToBeClickable(price));
        return price.getText();
    }

    public BasketPage addItemToBasket() {
        closePopUp();
        wait.until(ExpectedConditions.elementToBeClickable(addBasket));
        Actions actions = new Actions(driver);
        actions.moveToElement(addBasket);
        actions.perform();
        addBasket.click();
        actions.moveToElement(basketButton);
        actions.perform();
        basketButton.click();
        return new BasketPage(driver);
    }
}
