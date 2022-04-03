package com.assessment.ui;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class ProductPage extends BasePage implements IMenu, IProductPage {
    private List<WebElement> productItems;

    public int searchProduct(String searchKey) {
        ExtentReport.createAndGetNodeInstance("Search Product");
        setValue(searchText, searchKey, "Search text");
        clickElement(searchButton, "Search button");
        waitUntilDocumentIsReady();
        productItems = getElements(productItem);
        if(productItems.size() > 0) {
            ExtentReport.node.info(String.valueOf(productItems.size()) + " are displayed in the page");
        } else {
            validateElementState(StateValidationType.Exists, alertText, "Alert Text");
        }
        return productItems.size();
    }

    public void addProductToCart(double maxPrice) {
        ExtentReport.createAndGetNodeInstance("Add Product To cart");
        for(int i=1; i<=productItems.size(); i++) {
            String selectedProduct = selectedProductItem.replace("DYNAMIC_INDEX", String.valueOf(i));
            scrollToElement(selectedProduct, "selected product");
            moveToWebElement(selectedProduct, "selected product");
            String itemPriceTextUpdated = selectedProduct + itemPriceText;
            String actualPrice = getText(itemPriceTextUpdated).replace("$", "");

            if(Double.parseDouble(actualPrice)<=maxPrice) {

                if(getText(selectedProduct + itemAvailabilityText).equalsIgnoreCase("in stock")) {
                    clickElement(selectedProduct + addToCartButton, "Add To Cart Button");
                    waitUntilVisible(confirmationDialog, 30);
                    validateAttributeValue(
                            AttributeValidationType.Contains,
                            confirmationText,
                            "innerText",
                            "Product successfully added to your shopping cart",
                            "Confirmation Text");

                    Map<String, String> currentProductDetails = Map.of(
                            "title", getText(selectedProduct + itemTitle),
                            "price", actualPrice
                    );
                    TestData.selectedProductDetails.add(currentProductDetails);
                    clickElement(continueShoppingButton, "Continue Shopping Button");
                    waitUntilInvisible(confirmationDialog, 30);
                    validateAttributeValue(
                            AttributeValidationType.Equals,
                            cartItemsCount,
                            "innerText",
                            String.valueOf(TestData.selectedProductDetails.size()),
                            "Cart Items Count"
                            );
                }

            }
        }
    }
}
