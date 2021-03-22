package com.gittigidiyor.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject {

    public static final String LOGIN_HEADER = "Üye Girişi";
    public static final String IDENTITY  = "yasinakcadev@gmail.com"; //email adresim gelecek
    public static final String PASSWORD = "Asdfg55^^"; //ve tabiki sifre

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='gg-login-box']/div[1]/div/h1")
    private WebElement loginHeader;

    @FindBy(id = "L-UserNameField")
    private WebElement identityField;

    @FindBy(id = "L-PasswordField")
    private WebElement passwordField;

    @FindBy(id = "gg-login-enter")
    private WebElement loginButton;


    public String getLoginHeader() {
        System.out.println(loginHeader);
        return loginHeader.getText();
    }

    private void fillTheField() {
        identityField.clear();
        identityField.sendKeys(IDENTITY);
        passwordField.clear();
        passwordField.sendKeys(PASSWORD);
    }

    public HomePage submit() {
        fillTheField();
        loginButton.click();
        return new HomePage(driver);
    }
}
