package com.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.basePackage.BaseClass;
import com.extentManager.ExtentManager;

public class OrangeHRMTest extends BaseClass {
	
	/*@Test
	public void loginPageTest() throws InterruptedException {
		Thread.sleep(2000);
		WebElement imgElement=driver.findElement(By.xpath("//*[@id=\"divLogo\"]/img1"));
		Assert.assertTrue(imgElement.isDisplayed());
	}*/
	@Test
	public void loginTest() throws InterruptedException {
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("RM");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Jocata@111");
		driver.findElement(By.xpath("//button[@class='login-btn']")).click();
		Thread.sleep(10000);
		
		driver.findElement(By.xpath("//mat-label[contains(text(),' 872 ')]")).click();
		Thread.sleep(10000);

		driver.findElement(By.xpath("//a[contains(text(),' PERSONAL DISCUSSION')]")).click();
		Thread.sleep(10000);

		driver.findElement(By.xpath("//div[@class='calendar-btn-group']")).click();
		Thread.sleep(10000);

		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		String todayAsString = dateFormat.format(today);
		String tomorrowAsString = dateFormat.format(tomorrow);

		System.out.println(todayAsString);
		System.out.println(tomorrowAsString);
		
		selectDateFromCalendar(tomorrowAsString);
		
		Thread.sleep(23000);

		System.out.println("pass");
		
      	}
	
	
	public void selectDateFromCalendar(String date) throws InterruptedException {
		try {

			Thread.sleep(3000);
			WebElement dateMonth = driver.findElement(By.xpath(
					"//*[starts-with(@id,'owl-dt-picker-')]/div[2]/owl-date-time-calendar/div[1]/div/button/span"));
			Thread.sleep(3000);
			// WebElement
			// backward=driver.findElement(By.xpath("//*[@id=\"owl-dt-picker-1\"]/div[2]/owl-date-time-calendar/div[1]/button[1]/span"));
			WebElement backward = driver.findElement(By.xpath("//button[@aria-label='Previous month']"));
			Thread.sleep(3000);

			// WebElement
			// forward=driver.findElement(By.xpath("//*[@id=\"owl-dt-picker-1\"]/div[2]/owl-date-time-calendar/div[1]/button[2]/span"));
			WebElement forward = driver.findElement(By.xpath("//button[@aria-label='Next month']"));

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println(latestp);

			/*
			 * Date currentDate = new Date(); Date dateToBeSelected = new
			 * SimpleDateFormat("dd/MM/yyyy").parse(date); String day = new
			 * SimpleDateFormat("dd").format(dateToBeSelected); String month =
			 * new SimpleDateFormat("MMMM").format(dateToBeSelected); String
			 * year = new SimpleDateFormat("yyyy").format(dateToBeSelected);
			 * String monthYear=month+" "+year;
			 */
			Date currentDate = new Date();
			Date dateToBeSelected = new SimpleDateFormat("dd/MM/yyyy").parse(date);
			String day = new SimpleDateFormat("dd").format(dateToBeSelected);
			String month = new SimpleDateFormat("MMM").format(dateToBeSelected);
			String year = new SimpleDateFormat("yyyy").format(dateToBeSelected);
			String monthYear = month + "-" + year;

			System.out.println(monthYear);
			while (!dateMonth.getText().equals(monthYear)) {
				System.out.println(dateMonth.getText() + " --- " + monthYear);

				if (currentDate.compareTo(dateToBeSelected) > 0)
					backward.click();
				else if (currentDate.compareTo(dateToBeSelected) < 0)
					forward.click();

			}

			// *[@id="owl-dt-picker-0"]/div[2]/owl-date-time-calendar/div[2]/owl-date-time-month-view/table/tbody/tr/td/child::span[text()='16']
			driver.findElement(
					By.xpath("//*[@id=\"owl-dt-picker-0\"]/div[2]/owl-date-time-calendar/div[2]/owl-date-time-month-view/table/tbody/tr[3]/td[6]/span[text()='" + day + "']")).click();
			//
			// driver.findElement(By.xpath("//div[starts-with(@id,'cdk-overlay-')]//table/tbody/tr/td/child::span[text()='"+day+"']")).click();
			// driver.findElement(By.xpath("//td[text()='"+day+"'")).click();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	
	/*@Test
	public void sampleCase() {
		ExtentManager.test.createNode("Validation1");
		Assert.assertTrue(true);
		ExtentManager.test.createNode("Validation2");
		Assert.assertTrue(true);
		ExtentManager.test.createNode("Validation3");
		Assert.assertTrue(true);
		ExtentManager.test.createNode("Validation4--changes--");
		Assert.assertTrue(true);
	}*/
}
