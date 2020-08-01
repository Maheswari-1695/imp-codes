package com.datadriven;

import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class Login {
	           String[][]data=null;
	           WebDriver driver;
	
	@DataProvider(name="loginData")
	           public String [][]loginDataProvider() throws BiffException, IOException{
	           data=getExcelData();
	           return data;
}
	private String[][] getExcelData() throws BiffException, IOException {
	FileInputStream excel=new FileInputStream("C:\\Users\\mahi\\Desktop\\data driven.xls");
	Workbook workbook=Workbook.getWorkbook(excel);
	Sheet sheet=workbook.getSheet(0);
	int rowCount=sheet.getRows();
	int coulCount=sheet.getColumns();
	String testData[][]=new String[rowCount-1][coulCount];
	for(int i=1;i<rowCount;i++)
	{
		for (int j=0;j<coulCount;j++) {
			testData[i-1][j]=sheet.getCell(j,i).getContents();
	}}

	return testData;
	}
	@BeforeTest
	public void beforeTest() {
		        System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
		
	            driver=new ChromeDriver();
	}
	@AfterTest
	public void afterTest() {
		        driver.quit();
	}
	
    @Test(dataProvider="loginData")
    public void loginWithBothCorrect(String username,String password) {
    	        driver.get("https://www.facebook.com/");
		
		        driver.manage().window().maximize();//method chaining
    			
    			WebElement UserName=driver.findElement(By.id("email"));
    			
    			UserName.sendKeys(username);
    			
    			WebElement passWord=driver.findElement(By.id("pass"));
    			
    			passWord.sendKeys(password);
    			
    			WebElement logIn=driver.findElement(By.id("loginbutton"));
    			
    			logIn.click(); 
    			
    }
}
