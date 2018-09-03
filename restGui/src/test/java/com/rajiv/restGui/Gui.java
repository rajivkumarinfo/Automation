package com.rajiv.restGui;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.ChromeDriverManager;


/* Just Hard coded the script, haven't used any Function or Properties file*/

public class Gui {

	WebDriver driver;

	@Test
	public void validate() throws InterruptedException {
		ChromeDriverManager.getInstance().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Wait wt = new WebDriverWait(driver, 10);
		// driver.navigate().to("https://in.puma.com");
		driver.get("https://in.puma.com");
		String s = driver.getTitle();
		System.out.println(s);
		Assert.assertEquals(s, "PUMA.COM | Forever Faster");
		System.out.println("Title is showing correct");

		WebElement wb = driver.findElement(By.xpath("//a[@data-subnav='men-subnav']//parent::li"));

		Actions act = new Actions(driver);
		act.moveToElement(wb).build().perform();

		WebElement wbl = driver.findElement(
				By.xpath("//li[@id='men-subnav']//div[@class='digimeg-group-inner']//div[2]//li//a[text()='Running']"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", wbl);

		//System.out.println("OK I am here!");

		WebElement secondSchoe = driver.findElement(By.xpath("//img[@alt='Hybrid Runner FUSEFIT']"));
		String schoe1 = secondSchoe.getAttribute("alt");
		//System.out.println("ho--->" + schoe1);
		secondSchoe.click();

		Set<String> window = driver.getWindowHandles();
		Iterator<String> itr = window.iterator();
		// System.out.println(window.size());

		String mainWindow = itr.next();
		// System.out.println("After 1st Iterator");
		String subWindow = itr.next();
		driver.switchTo().window(subWindow);

		Thread.sleep(9000);
		WebElement size = driver.findElement(By.id("select_label_size"));
		wt.until(ExpectedConditions.visibilityOf(size));

		driver.findElement(By.xpath("//div[@id='size_label']//div//div//span")).click();

		Thread.sleep(5000);

		js.executeScript("document.getElementById('180~229').click();");

		//String getSize1 = size.getText();
		// System.out.println("shoes size is ----> "+getSize1);
		String getPrice1 = driver.findElement(By.xpath("//span[@id='product-price-90855']//span")).getText();

		String getQuantity1 = driver.findElement(By.xpath("//select[@id='qty']//option[1]")).getText();

		wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//span//span[contains(text(), 'Add to Cart')]")));

		driver.findElement(By.xpath("//span//span[contains(text(), 'Add to Cart')]")).click();

		String schoe2 = driver.findElement(By.xpath("//h2//a[contains(text(), 'Hybrid Runner FUSEFIT')]")).getText();
		//System.out.println(schoe2);
		String getPrice2 = driver
				.findElement(By.xpath("//td[@class='product-cart-total last']//span[@class='cart-price']/span"))
				.getText();
		System.out.println(getPrice2);

		String getQuantity2 = driver.findElement(By.xpath("//select//option[1]")).getAttribute("value");
		System.out.println(getQuantity2);

		Assert.assertEquals(schoe1, schoe2);
		Assert.assertEquals(getPrice1, getPrice2);
		Assert.assertEquals(getQuantity1, getQuantity2);

		System.out.println("Name, Price and Quatity, has got successfully verified");
	}
}
