package com.assessment.ui;


public class AccountPage extends BasePage implements IAccountPage,IMenu {
    public AccountPage() {
        ExtentReport.createAndGetNodeInstance("Account Page");
    }

    public void isAccountLoggedIn() {
        waitUntilVisible(accountTab, 30);
        validateAttributeValue(AttributeValidationType.Contains, accountUserName, "innerText", TestData.firstName, "Account User Name");
        validateAttributeValue(AttributeValidationType.Contains, accountUserName, "innerText", TestData.lastName, "Account User Name");
    }
}
