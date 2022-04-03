package com.assessment.ui;

public interface IAuthenticationPage {
    String createEmailText = "//input[@id='email_create']";
    String createAccountButton = "//button[@id='SubmitCreate']";
    String emailText = "//input[@id='email']";
    String passwordText = "//input[@id='passwd']";
    String loginButton = "//button[@id='SubmitLogin']";

    String genderRadioButton = "//input[@id='id_gender']";
    String firstNameText = "//input[@id='customer_firstname']";
    String lastNameText = "//input[@id='customer_lastname']";

    String addressFirstNameText = "//input[@id='firstname']";
    String addressLastNameText = "//input[@id='lastname']";
    String addressLine1Text = "//input[@id='address1']";
    String cityText = "//input[@id='city']";
    String stateSelect = "//select[@id='id_state']";
    String postcodeText = "//input[@id='postcode']";
    String countrySelect = "//select[@id='id_country']";
    String mobileNumberText = "//input[@id='phone_mobile']";
    String addressAlias = "//input[@id='alias']";
    String registerButton = "//button[@id='submitAccount']";

}
