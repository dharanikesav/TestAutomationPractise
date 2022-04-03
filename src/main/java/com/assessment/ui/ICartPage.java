package com.assessment.ui;

public interface ICartPage {
    String productRow = "//tr[contains(@id,'product')]";
    String selectedRow = "//tr[contains(@id,'product')][DYNAMIC_INDEX]";
    String productName = "//p[contains(@class, 'product-name')]";
    String productPrice = "//span[@class='price']";
    String productQuantity = "//input[contains(@class,'quantity')]";
    String decreaseQuantityButton = "//a[contains(@id,'quantity_down')]";
    String increaseQuantityButton = "//a[contains(@id,'quantity_up')]";
    String productTotalPrice = "//span[contains(@id,'total_product_price')]";
    String deleteProductButton = "//a[@class='cart_quantity_delete']";
    String totalPrice = "//td[@id='total_product']";

    String deliveryAddressNameText = "//ul[contains(@class,'address first_item')]//span[@class,'address_name']";
    String deliveryAddressLine1Text = "//ul[contains(@class,'address first_item')]//span[@class,'address_address1']";
    String deliveryAddressMobileText = "//ul[contains(@class,'address first_item')]//span[@class,'address_phone_mobile']";
    String checkoutButton = "//p[contains(@class, 'cart_navigation')]//*[@title='Proceed to checkout' or contains(text(),'Proceed to checkout')]";
    String addressSelect = "//select[@id='id_address_delivery']";

    String agreeTermsConditions = "//input[@id='cgv']";

    //Summary, Sign in, Address, Shipping, Payment
    String currentStep = "//ul[@id='order_step']//li[contains(@class,'step_current')]";
    String payByChequeLink = "//a[contains(@class, 'cheque')]";
    String confirmOrder = "//p[contains(@class, 'cart_navigation')]//button[@type='submit']";

    String shoppingCartTab = "//span[@class='navigation_page' and contains(text(),'Your shopping cart')]";
    String addressTab = "//span[@class='navigation_page' and contains(text(),'Addresses')]";
    String shippingTab = "//span[@class='navigation_page' and contains(text(),'Shipping')]";
    String paymentTab = "//span[@class='navigation_page' and contains(text(),'Your payment method')]";



}
