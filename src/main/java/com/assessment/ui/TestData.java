package com.assessment.ui;

import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class TestData {
    public static String firstName;
    public static String lastName;
    public static String email;
    public static String addressLine1;
    public static String mobileNumber;
    public static String password;
    public static String city;
    public static String aliasName;
    public static String postCode;
    public static List<Map<String, String>> selectedProductDetails = new ArrayList<Map<String, String>>();

    public void initialize() {
        firstName = generateRandomText(6, true, false);
        lastName = generateRandomText(4, true, false);
        password = generateRandomText(13, true, true);
        aliasName = generateRandomText(4, true, false);
        addressLine1 = generateRandomText(16, true, false);
        mobileNumber = generateRandomText(10, false, true);
        city = generateRandomText(6, true, false);
        postCode = generateRandomText(5, false, true);
        email = firstName + "_" + lastName + "_" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyHHmmss")) + "@example.com";


    }

    private String generateRandomText(int length, boolean useAlphabets, boolean useNumerics) {
        return RandomStringUtils.random(length, useAlphabets, useNumerics);
    }
}
