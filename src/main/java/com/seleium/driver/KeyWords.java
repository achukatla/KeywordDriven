package com.seleium.driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class KeyWords {
	public static WebDriver driver;
	public static void browserOpen( String url){
		driver.get(url);
		
	}
public static void browserClose(){
		driver.quit();
	}
public static void driverImplicitWait(){
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}
public static void browserMaximize(){
	driver.manage().window().maximize();
}
public static void clickElement(String xpath){
	driver.findElement(By.xpath(xpath)).click();
}
public static void clearText(String xpath){
	driver.findElement(By.xpath(xpath)).clear();
}
public static void typeText(String xpath,String text){
	driver.findElement(By.xpath(xpath)).sendKeys(text);
}
public static String getText(String xpath){
	return "driver.findElement(By.xpath(xpath)).getText()";
}

public static String verifyText(String xpath,String expectedText){
	String actualText  = driver.findElement(By.xpath(xpath)).getText();
	if(expectedText.endsWith(actualText))
		return "Pass" ;
	else
		return "Fail" ;
}

}
