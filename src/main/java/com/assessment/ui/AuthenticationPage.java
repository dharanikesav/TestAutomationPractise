package com.assessment.ui;

public class AuthenticationPage extends BasePage implements IAuthenticationPage, IMenu {
    public void login() {
        ExtentReport.createAndGetNodeInstance("Login");
        setValue(emailText, TestData.email, "Email Id");
        setValue(passwordText, TestData.password, "Password");
        clickElement(loginButton, "login button");
    }

    public void signUp() {
        ExtentReport.createAndGetNodeInstance("Sign up");
        setValue(createEmailText, TestData.email, "Signup with Email");
        clickElement(createAccountButton, "Sign Up");
        waitUntilDocumentIsReady();

        setValue(firstNameText, TestData.firstName, "First Name");
        setValue(lastNameText, TestData.lastName, "Last Name");
        setValue(emailText, TestData.email, "Email Id");
        setValue(passwordText, TestData.password, "Password");
        setValue(addressFirstNameText, TestData.firstName, "First Name");
        setValue(addressLastNameText, TestData.lastName, "Last Name");
        setValue(addressLine1Text, TestData.addressLine1, "Address Line 1");
        setValue(cityText, TestData.city, "Address Line 1");
        selectValue(stateSelect, "Alaska", "State");
        setValue(postcodeText, TestData.postCode, "postCode");
        selectValue(countrySelect, "United States", "Country");
        setValue(mobileNumberText, TestData.mobileNumber, "Mobile Number");
        setValue(addressAlias, TestData.aliasName, "Alias Name");
        clickElement(registerButton, "Register button");
        waitUntilDocumentIsReady();
    }
}
