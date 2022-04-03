package com.assessment.ui;

public interface IProductPage {
    //Please enter a search keyword
    //No results were found for your search "Classic"
    //Your order on My Store is complete
    String alertText= "//p[contains(@class, 'alert)]";
    String productItem= "//ul[contains(@class,'product_list')]/li";
    String selectedProductItem = "//ul[contains(@class,'product_list')]/li[DYNAMIC_INDEX]";
    String itemPriceText =  "//div[@class='left-block']//span[@itemprop='price']";
    String itemTitle = "//a[@class='product-name']";
    String addToCartButton = "//a[@title='Add to cart']";
    String itemAvailabilityText = "//span[@class='availability']";
    String cartItemsCount = "//a[@title='View my shopping cart']//span[contains(@class,'quantity')]";


    String confirmationDialog = "//div[@id='layer_cart']";
    //Product successfully added to your shopping cart
    String confirmationText = "//div[@id='layer_cart']//div[contains(@class,'cart_product')]//h2";

    String continueShoppingButton = "//span[@title='Continue shopping']";
    String proceedToCheckOutButton = "//span[@title='Proceed to checkout']";


}
