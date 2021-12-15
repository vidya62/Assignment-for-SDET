package com.demo.demo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UIInitiate {

	public static ChromeOptions chromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		return options;
	}

	/**
	 * @throws InterruptedException 
	 * @category Demo Test
	 */

	public static void main (String[] args) throws InterruptedException 
	{
		System.setProperty("webdriver.chrome.driver", "./src/main/resource/Drivers/chromedriver.exe");
		ChromeOptions options = chromeOptions();
		WebDriver driver = null;

		WebDriverManager.chromedriver().version("96.0.4664.45").setup();
		driver = new ChromeDriver();
		 driver.get ("https://restful-booker.herokuapp.com/");
		 WebElement apidocsElement =driver.findElement(By.xpath("//*[text()='API Docs']"));
		 apidocsElement.click();
		 Thread.sleep(5000);
		 List<WebElement> l1 = driver.findElements(By.xpath("//section/div/article/span"));		 
		 List<WebElement> l3 = driver.findElements(By.xpath("//section/div/article/pre/code"));
		 
		 for(int i=0;i<l1.size();i++)
		 {
			 WebElement data1=l1.get(i);
			 
			 WebElement data3=l3.get(i);
			 System.out.println(data1.getText());
			 System.out.println(data3.getText());
		 }
		 
		driver.quit();
	}
}
