package com.gittigidiyor.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class BasketPage extends PageObject {
    public BasketPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='cart-price-container']/div[3]/p")
    private WebElement price;

    @FindBy(css = "select.amount")
    private WebElement itemAmount;

    @FindBy(xpath = "//*[@id='submit-cart']/div/div[2]/div[3]/div/div[1]/div/div[5]/div[1]/div/ul/li[1]/div[1]")
    private WebElement itemAmountSummary;

    @FindBy(className = "btn-delete")
    private WebElement deleteItem;

    @FindBy(id = "empty-cart-container")
    private WebElement emptyBasketText;

    public String getPrice() {
        wait.until(ExpectedConditions.elementToBeClickable(price));
        return price.getText();
    }

    public void selectItemCount(Integer itemCount) {
        wait.until(ExpectedConditions.elementToBeClickable(itemAmount));
        Select select = new Select(itemAmount);
        select.selectByValue(itemCount.toString());
    }

    public String getEmptyBasketText() {
        wait.until(ExpectedConditions.elementToBeClickable(emptyBasketText));
        return emptyBasketText.getText();
    }

    public String getItemAmountSummary(String itemAmount) {
        wait.until(ExpectedConditions.textToBePresentInElement(itemAmountSummary, itemAmount));
        return itemAmountSummary.getText();
    }

    public void deleteItem() {
        deleteItem.click();
    }

}
