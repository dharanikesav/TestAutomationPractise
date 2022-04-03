package com.assessment.ui.ui;

import com.assessment.ui.*;
import org.junit.Test;

import java.util.List;

public class ECommerceUITest extends BaseTest {

    @Test
    public void sampleTest() {
        try {
            new TestData().initialize();
            driver.get("http://automationpractice.com/index.php");
            PageNavigation pageNavigation = new PageNavigation();
            pageNavigation.navigateToLogin();
            AuthenticationPage authenticationPage = new AuthenticationPage();
            authenticationPage.signUp();

            AccountPage accountPage = new AccountPage();
            accountPage.isAccountLoggedIn();
            pageNavigation.navigateToHome();

            ProductPage productPage = new ProductPage();
            List.of(
                    "casual",
                    "evening",
                    "summer"
            ).forEach((k) -> {
                if (productPage.searchProduct(k) > 0) {
                    productPage.addProductToCart(30.00);
                }
            });

            CartPage cartPage = new CartPage();
            cartPage.validateProductDetails();;
            cartPage.removeProductFromCart(TestData.selectedProductDetails.get(0).get("title"));
            cartPage.proceedWithCheckOutProcess();
        } catch (Exception e) {
            e.printStackTrace();
            ExtentReport.reportError(e);
            ExtentReport.flushReport();
            throw e;
        }
    }
}
