package com.seleium.driver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.selenium.util.PoiXlReader;
import com.selenium.util.PoiXlWriter;

public class Driver{
	WebDriver driver; 
	String res ;
	String path ;
	String result = "pathToResultXL" ;
	
	String TSData[][] ;
		
	@Before
	public void setup(){
		driver = new FirefoxDriver();
		KeyWords.driver = driver;
			}
	
	@Test
	public void mainTest() throws Exception{
		String TCId,keyWord,input1,input2;
		path = "pathToXl";
		TSData = PoiXlReader.read(path, "TestSteps");
		int TSrows = TSData.length;
		int TScols = TSData[0].length;
		System.out.println("TSrows..."+TSrows+"TScols....."+TScols);
		for(int i=1 ; i < TSrows ; i++){
				res="pass" ;
				TCId = TSData[i][0];
				keyWord = TSData[i][3];
				input1 = TSData[i][4];
				input2 = TSData[i][5];
				//System.out.println("....>"+keyWord+"....>"+input1+"....>."+input2);
			try{
				runTest(keyWord,input1,input2);
			
				if(res.equals("pass"))
						TSData[i][6] = "pass" ;
				else
						TSData[i][6] = "Fail" ;
			}catch(Exception e){
				System.out.println("Error occured "+e);
				takeScreenShot("pathToStore"+TSData[i][0]+"_"+i+".jpg");
			}
	 	}
		System.out.println("Done excuting the test"+TSData.length);
				
		}
	
   private void takeScreenShot(String path) {
	  File srcFile =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  try {
		FileUtils.copyFile(srcFile, new File(path));
	} catch (IOException e) {
		System.out.println("Error occured in taking a screen shot"+e);	}
			 	}
public void runTest(String keyWord,String input1,String input2){
	   
	   String  key = keyWord;
	switch(key)
	   {
	   case "BrowserOpen" :
						   KeyWords.browserOpen(input1);
						   break;
	   case "BroserClose" :
		   				KeyWords.browserClose();
		   				break;
	   case "BrowserMaximize" :
	   					KeyWords.browserMaximize();
	   					break;
	   case "TypeText" :
		   				KeyWords.typeText(input1, input2);
		   				break;
	   case "ClearText" :
		   				KeyWords.clearText(input1);
		   				break;
	   case "ImplicitWait " :
		   				KeyWords.driverImplicitWait();
		   				break;
	   case "ClickElement" :
		   				KeyWords.clickElement(input1);
		   				break;
	   case "Verify" :
		   				res = KeyWords.verifyText(input1, input2);
		   				break;
	   case "GetText" :
		   			KeyWords.getText(input1);
		   			break;
		   
	   }
	   
   }
@After
public void teardown(){
	PoiXlWriter.writeXl(result, "TSResult", TSData);
}
}

