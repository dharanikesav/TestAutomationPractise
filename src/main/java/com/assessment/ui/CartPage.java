package com.assessment.ui;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class CartPage extends BasePage implements ICartPage, IMenu {
    private void navigateToCart() {
        if(!isElementPresent(shoppingCartTab)) {
            moveToWebElement(viewCartLink, "view cart link");
            clickElement(viewCartLink, "View Cart Link");
            waitUntilDocumentIsReady();
            waitUntilVisible(shoppingCartTab);
        }
    }
    public void validateProductDetails() {
        ExtentReport.createAndGetNodeInstance("Cart Summary validation");
        navigateToCart();
        List<WebElement> selectedProducts = getElements(productRow);

        for(int i=1; i<=selectedProducts.size(); i++) {
            String selectedProduct = selectedRow.replace("DYNAMIC_INDEX", String.valueOf(i));
            String actualName = getText(selectedProduct + productName);
            String actualPrice = getText(selectedProduct + productPrice).replace("$", "");
            boolean foundProduct = false;
            moveToWebElement(selectedProduct, actualName);

            for(Map<String, String> product:TestData.selectedProductDetails) {
                if(product.get("price").toLowerCase().contains(actualName)) {
                    foundProduct = true;
                    if(!actualPrice.replace("$", "").equalsIgnoreCase(product.get("price"))) {
                        ExtentReport.reportError(new CustomException(
                                "Incorrect price in the cart. Expected price: " + product.get("price") +
                                        ", actual price: " + actualPrice
                        ));
                    }
                }

                if(foundProduct) {
                    break;
                }
            }
        }
    }

    public void removeProductFromCart(String name) {
        ExtentReport.createAndGetNodeInstance("Remove Product");
        navigateToCart();
        List<WebElement> selectedProducts = getElements(productRow);
        for(int i=1; i<=selectedProducts.size(); i++) {
            String selectedProduct = selectedRow.replace("DYNAMIC_INDEX", String.valueOf(i));
            String actualName = getText(selectedProduct + productName);
            boolean foundProduct = false;
            if(actualName.equalsIgnoreCase(name)) {
                clickElement(selectedProduct + deleteProductButton, "Delete Product Button");
                waitUntilDocumentIsReady();
                foundProduct = true;
            }
            if(foundProduct){
                ExtentReport.node.info("Product with name : " + name + " is successfully removed from cart");
                return;
            }
        }
        ExtentReport.reportError(new CustomException(
                "Product with name : " + name + " is not present in the cart"
        ));
    }

    public void proceedWithCheckOutProcess() {
        ExtentReport.createAndGetNodeInstance("Checkout Process");
        if(isElementPresent(shoppingCartTab)) {
            moveToWebElement(checkoutButton, "checkout button");
            clickElement(checkoutButton, "checkout button");
            waitUntilInvisible(shoppingCartTab);
            waitUntilDocumentIsReady();
        }

        if(isElementPresent(addressTab)) {
            selectValue(addressSelect, TestData.aliasName, "Address Select");
            clickElement(checkoutButton, "checkout button");
            waitUntilInvisible(shoppingCartTab);
            waitUntilDocumentIsReady();
        }

        if(isElementPresent(shippingTab)) {
            checkBoxClick(agreeTermsConditions, "Terms and conditions checkbox");
            clickElement(checkoutButton, "checkout button");
            waitUntilInvisible(shoppingCartTab);
            waitUntilDocumentIsReady();
        }

        if(isElementPresent(paymentTab)) {
            clickElement(payByChequeLink, "Pay by cheque link");
            waitUntilVisible(confirmOrder);
            clickElement(confirmOrder, "confirm order button");
            waitUntilDocumentIsReady();
        }
    }
}
