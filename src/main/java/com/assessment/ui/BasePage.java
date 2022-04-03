package com.assessment.ui;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;


public class BasePage extends BaseTest {
    private static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	private static final WebDriverWait waitWithTimeOut = new WebDriverWait(driver, Duration.ofSeconds(30));

    protected By getByXpath(String path) {
        return By.xpath(path);
    }

    protected boolean isElementPresent(String xPath){
        return (getElements(xPath).size() > 0);
    }

    protected void clickElement(String xPath, String elementName) {
        try {
            waitUntilClickable(xPath);
            try {
                waitUntilClickable(xPath).click();
            } catch(StaleElementReferenceException stale) {
                waitUntilClickable(xPath).click();
            }

            ExtentReport.node.info("Successfully clicked element : " + elementName);

        } catch (Exception e) {
            e.printStackTrace();
            ExtentReport.node.fail("Unable to click element with xpath : " + xPath);
            throw e;
        }

    }

    protected void setValue(String xPath, String value, String elementName) {
        try {
            WebElement element = waitUntilClickable(xPath);
            element.clear();
            element.sendKeys(Keys.BACK_SPACE);
            element.sendKeys(value);
            ExtentReport.node.info("Successfully set value of element : '" + elementName + "' to " + value);
        } catch (Exception e) {
            ExtentReport.reportError(e);
            throw e;
        }
    }

    protected void selectValue(String xPath, String optionValue, String elementName) {
        try {
            WebElement element = driver.findElement(getByXpath(xPath));
            Select selectList = new Select(element);
            List<WebElement> options = selectList.getOptions();
            for (WebElement option : options) {
                if (option.getAttribute("innerText").contains(optionValue)) {
                    selectList.selectByValue(option.getAttribute("value"));
                    ExtentReport.node.info("Successfully selected value : " + optionValue + " for element " + elementName);
                    return;
                }

            }
            throw new CustomException("Option : " + optionValue + " is not present in the list : " + elementName);
        } catch (Exception e) {
            ExtentReport.reportError(e);
            throw e;
        }
    }

    protected void moveToWebElement(String xpath, String elementName) {
        try {
            WebElement element = driver. findElement(getByXpath(xpath));
            Actions action = new Actions(driver);
            action.moveToElement(element).build().perform();

            ExtentReport.node.info("Moved to element : " + elementName + " Successfully");
        } catch (Exception e) {
            ExtentReport.reportError(e);
            throw e;
        }

    }

	protected void waitUntilDocumentIsReady() {
        delayInMillis(5000);
        ExpectedCondition<Boolean> pageLoadCondition = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        wait.until(pageLoadCondition);
    }

    protected WebElement waitUntilClickable(String xPath) {
        return wait.until(ExpectedConditions.elementToBeClickable(getByXpath(xPath)));
    }

    protected WebElement waitUntilVisible(String xPath) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xPath)));
    }

    protected WebElement waitUntilVisible(String xPath, long timeOutinSeconds) {
        return waitWithTimeOut
                .withTimeout(Duration.ofSeconds(timeOutinSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xPath)));
    }

    protected boolean waitUntilInvisible(String xPath) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xPath)));
    }

    protected boolean waitUntilInvisible(String xPath, long timeOutinSeconds) {
        return waitWithTimeOut
                .withTimeout(Duration.ofSeconds(timeOutinSeconds))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xPath)));
    }

	protected void delayInMillis(long milliSeconds) {
		try {
			Thread.currentThread().sleep(milliSeconds);
		} catch(Exception e) {
			ExtentReport.reportError(e);
		}
	}

    protected void scrollToElement(String xPath, String elementName) {
        try{
            WebElement element = driver.findElement(getByXpath(xPath));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);
            ExtentReport.node.info("Scrolled successfully to element : " + elementName);
        } catch(InterruptedException e) {
            throw new CustomException(e.getMessage());
        } catch (Exception e){
            ExtentReport.reportError(e);
            throw e;
        }
    }

    protected String getAttributeValue(String xPath, String attributeName) {
        try {
            WebElement element = driver.findElement(getByXpath(xPath));

            if(!element.getTagName().equalsIgnoreCase("Select")) {
                return element.getAttribute(attributeName).strip();
            } else {
                Select selectList = new Select(element);
                return selectList.getFirstSelectedOption().getAttribute(attributeName).strip();
            }
        } catch (Exception e) {
            ExtentReport.reportError(e);
            throw e;
        }
    }

    protected String getText(String xPath) {
        try {
            waitUntilVisible(xPath);
            WebElement element = driver.findElement(getByXpath(xPath));

            if(!element.getTagName().equalsIgnoreCase("Select")) {
                return element.getText().strip();
            } else {
                Select selectList = new Select(element);
                return selectList.getFirstSelectedOption().getText().strip();
            }

        } catch (Exception e) {
            ExtentReport.reportError(e);
            throw e;
        }
    }

    protected List<WebElement> getElements(String xpath) {
        return driver.findElements(getByXpath(xpath));
    }


    protected void validateAttributeValue(AttributeValidationType type, String xpath, String attributeName, String attributeValue, String elementName) {
        boolean validationSuccess = false;
        String actualValue = "";

        switch (type) {
            case Equals:
                actualValue = getAttributeValue(xpath, attributeName);
                validationSuccess = actualValue.replaceAll("\u00A0","").equalsIgnoreCase(attributeValue);
                break;

            case Contains:
                actualValue = getAttributeValue(xpath, attributeName);
                validationSuccess = actualValue.contains(attributeValue);
                break;
        }

        String message = elementName + ", Attribute : " + attributeName + ", actual value : " + actualValue + ", expected value : " + attributeValue;

        if (validationSuccess) {
            try {
                ExtentReport.node.pass("Validation '" + type + "'  successful. element: " + message, addBase64ScreenShotToReport());
            } catch(IOException e) {
                e.printStackTrace();
            }

        } else {
            ExtentReport.reportError(new CustomException("Validation '" + type + "'  failed. element: " + message));
            throw new CustomException("Validation '" + type + "'  failed. element: " + message);
        }
    }

    protected void validateElementState(StateValidationType type, String xpath, String elementName) {
        boolean validationSuccess = false;

        switch(type) {
            case Exists:
                validationSuccess = (getElements(xpath).size() > 0);
                break;
            case NotExists:
                validationSuccess = (getElements(xpath).size() < 1);
                break;
        }


        if (validationSuccess) {
            ExtentReport.node.pass("Validation type '" + type + "'  successful for element: " + elementName);
        } else {
            ExtentReport.reportError(new CustomException("Validation type '" + type + "'  failed for element: " + elementName));
            throw new CustomException("Validation type '" + type + "'  failed for element: " + elementName);
        }

    }

    public void checkBoxClick(String xPath, String elementName)
    {
        try {
            WebElement element = driver.findElement(getByXpath(xPath));
            if (element.isSelected()) {
                ExtentReport.node.info("Checked already: ");
            } else {
                element.click();
            }

            ExtentReport.node.info("Clicked checkbox : " + elementName + " Successfully");
        } catch (Exception e) {
            ExtentReport.reportError(e);
            throw e;
        }
    }

}
