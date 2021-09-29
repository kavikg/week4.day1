package week4.day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class framehandling {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		String title = driver.findElement(By.xpath("//span[contains(text(),'Friendly')]")).getText();
		System.out.println(title);
		driver.switchTo().frame("frame1");
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Input field");
		System.out.println("Topic entered");
		//driver.switchTo().defaultContent();
		driver.switchTo().frame("frame3");
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		System.out.println("Checkbox Clicked");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("frame2");
		WebElement animals = driver.findElement(By.id("animals"));
		Select a = new Select(animals);
		a.selectByValue("avatar");
		System.out.println("Dropdown Selected");
		driver.close();
	}

}
