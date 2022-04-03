package com.assessment.ui;

public interface IMenu {
    String loginLink = "//a[@class='login']";
    String accountUserName = "//a[@class='account']/span";
    String signoutButton = "//div[@class='header_user_info']//a[contains(@href,'mylogout')]";
    String homePageLink = "//a[@title='My Store']";
    String searchText = "//input[@id='search_query_top']";
    String searchButton = "//button[@name='submit_search']";
    String viewCartLink = "//a[@title='View my shopping cart']";
}
