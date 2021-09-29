package week4.day1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Leafgroundframes {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://leafground.com/pages/frame.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.switchTo().frame(0);
		WebElement ClickMe = driver.findElement(By.id("Click"));
		
		File src = ClickMe.getScreenshotAs(OutputType.FILE);
		File dest = new File("./snaps/ClickMe.png");
		FileUtils.copyFile(src, dest);
		
		driver.switchTo().defaultContent();
		List<WebElement> framecount = driver.findElements(By.tagName("iframe"));
		int size = framecount.size();
		System.out.println("Number of the frame: " +size);
		driver.close();
	}

}
