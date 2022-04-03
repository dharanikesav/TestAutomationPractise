package com.assessment.ui;



public class PageNavigation extends BasePage implements IMenu {

    public void navigateToLogin() {
        ExtentReport.createAndGetNodeInstance("Log in");
        if(getElements(loginLink).size() <= 0) {
            ExtentReport.node.info("User already logged in");
        } else {
            clickElement(loginLink, "Login Link");
            waitUntilDocumentIsReady();
        }
    }

    public void navigateToHome() {
        ExtentReport.createAndGetNodeInstance("Home Page");
        clickElement(homePageLink, "Home Page Link");
        waitUntilDocumentIsReady();
    }
}
