package com.gittigidiyor.selenium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCase {

    final static String SEARCH_TEXT = "bilgisayar";
    final static String EMPTY_BASKET_TEXT = "Sepetinizde ürün bulunmamaktadır.";
    final static Integer ITEM_COUNT = 2;
    private static final Logger logger = LogManager.getLogger(TestCase.class);
    private static WebDriver driver;


    @BeforeAll
    static void setup() {
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
        driver = new ChromeDriver();
        System.out.println("setup");
    }

    @AfterAll
    static void after() {
        driver.manage().deleteAllCookies();
        driver.close();
    }

    @Test
    void homePage() {
        driver.get(Utils.BASE_URL);
        HomePage homePage = new HomePage(driver);
        assertEquals(homePage.getBrandTitle(), HomePage.BRAND_TITLE);
        assertTrue(homePage.getAuthText().contains(HomePage.UN_AUTH_TEXT));
        homePage.closePopUp();
        LoginPage loginPage = homePage.goLogin();
        assertEquals(loginPage.getLoginHeader(), LoginPage.LOGIN_HEADER);
        HomePage authHomePage = loginPage.submit();
        assertEquals(authHomePage.getBrandTitle(), HomePage.BRAND_TITLE);
        assertTrue(authHomePage.getAuthText().contains(HomePage.AUTH_TEXT));
        ListPage listPage = authHomePage.goSearch(SEARCH_TEXT);
        listPage.clickPagination(1);
        DetailPage detailPage = listPage.clickRandomElement();
        String detailPrice = detailPage.getPrice();
        BasketPage basketPage = detailPage.addItemToBasket();
        assertEquals(basketPage.getPrice(), detailPrice);
        basketPage.selectItemCount(ITEM_COUNT);
        assertTrue(basketPage.getItemAmountSummary(ITEM_COUNT.toString())
                .contains(ITEM_COUNT.toString() + " Adet"));
        basketPage.deleteItem();
        assertTrue(basketPage.getEmptyBasketText().contains(EMPTY_BASKET_TEXT));
    }

}
