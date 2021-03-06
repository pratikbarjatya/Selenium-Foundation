package com.nordstrom.automation.selenium.core;

import org.openqa.selenium.By;

import com.nordstrom.automation.selenium.model.ComponentContainer.ByEnum;

/**
 * This utility class defines static methods and interfaces related to Selenium {@link By} objects.
 */
public final class ByType {
    
    private ByType() {
        throw new AssertionError("ByType is a static utility class that cannot be instantiated");
    }
    
    /**
     * Get the CSS locator string that reproduces the specified locator constant
     * 
     * @param constant locator constant
     * @return CSS locator string; 'null' if unconvertible
     */
    public static String cssLocatorFor(ByEnum constant) {
        return cssLocatorFor(constant.locator());
    }
    
    /**
     * Get the CSS locator string that reproduces the specified Selenium locator
     * 
     * @param locator Selenium locator
     * @return CSS locator string; 'null' if unconvertible
     */
    public static String cssLocatorFor(By locator) {
        
        String val = valueOf(locator);
        
        if (locator instanceof By.ByClassName) {
            return "." + val;
        } else if (locator instanceof By.ByCssSelector) {
            return val;
        } else if (locator instanceof By.ById) {
            return "#" + val;
        } else if (locator instanceof By.ByLinkText) {
            // unsupported
        } else if (locator instanceof By.ByName) {
            return "[name=" + val + "]";
        } else if (locator instanceof By.ByPartialLinkText) {
            // unsupported
        } else if (locator instanceof By.ByTagName) {
            return val;
        } else if (locator instanceof By.ByXPath) {
            // unsupported
        }
        
        return null;
    }
    
    /**
     * Get the XPath locator string that reproduces the specified locator constant
     * 
     * @param constant locator constant
     * @return XPath locator string; 'null' if unconvertible
     */
    public static String xpathLocatorFor(ByEnum constant) {
        return xpathLocatorFor(constant.locator());
    }
    
    /**
     * Get the XPath locator string that reproduces the specified Selenium locator
     * 
     * @param locator Selenium locator
     * @return XPath locator string; 'null' if unconvertible
     */
    public static String xpathLocatorFor(By locator) {
        
        String val = valueOf(locator);
        
        if (locator instanceof By.ByClassName) {
            return ".//*[contains(concat(' ',@class,' '),' " + val + " ')]";
        } else if (locator instanceof By.ByCssSelector) {
            // unsupported
        } else if (locator instanceof By.ById) {
            return ".//*[@id='" + val + "']";
        } else if (locator instanceof By.ByLinkText) {
            return ".//a[.='" + val + "']";
        } else if (locator instanceof By.ByName) {
            return ".//*[@name='" + val + "']";
        } else if (locator instanceof By.ByPartialLinkText) {
            return ".//a[text()[contains(.,'" + val + "')]]";
        } else if (locator instanceof By.ByTagName) {
            return ".//" + val;
        } else if (locator instanceof By.ByXPath) {
            return val;
        }
        
        return null;
    }
    
    /**
     * Get the underlying value of the specified Selenium locator
     * 
     * @param locator Selenium locator
     * @return value extracted from the specified locator
     */
    private static String valueOf(By locator) {
        String str = locator.toString();
        int i = str.indexOf(':');
        return str.substring(i + 1).trim();
    }
}
