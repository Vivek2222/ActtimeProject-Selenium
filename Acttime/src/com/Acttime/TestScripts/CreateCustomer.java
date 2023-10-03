package com.Acttime.TestScripts;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Acttime.GenericLibrary.BaseClass;
import com.Acttime.GenericLibrary.FileLibrary;
import com.Acttime.GenericLibrary.ListernerImplementation;
import com.Acttime.pom.HomePage;
import com.Acttime.pom.TaskPage;

@Listeners(ListernerImplementation.class)
public class CreateCustomer extends BaseClass{
	
	@Test
	public void createcust() throws EncryptedDocumentException, IOException, InterruptedException {
	HomePage hp = new HomePage(driver);
	hp.getTasktab().click();
	TaskPage tp = new TaskPage(driver);
	tp.getAddnewbtn().click();
	tp.getNewcust().click();
	FileLibrary f = new FileLibrary();
	String customername = f.readDataFromExcel("customerdetails", 1, 1);
	Thread.sleep(5000);
	System.out.println(customername);
	Thread.sleep(5000);
	tp.getCustname().sendKeys(customername);
	String customerdesp = f.readDataFromExcel("customerdetails", 1, 2);
	tp.getCustdesp().sendKeys(customerdesp);
	tp.getCreatebtn().click();
	String Expectedresult = customername;
	String Actualresult = driver.findElement(By.xpath("(//div[.='"+customername+"'])[2]")).getText();
	SoftAssert s= new SoftAssert();
	s.assertEquals(Expectedresult, Actualresult);
	s.assertAll();
	Reporter.log("Customer Created", true);
	}
}
