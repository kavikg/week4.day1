package week4.day1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://dev38607.service-now.com/navpage.do");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebElement fram = driver.findElement(By.id("gsft_main"));
		driver.switchTo().frame(fram);
		// driver.switchTo().frame(0);
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("Drithi@20");
		driver.findElement(By.id("sysverb_login")).click();
		driver.findElement(By.id("filter")).sendKeys("incident");
		driver.findElement(By.xpath("(//div[contains(text(),'Incidents')])[3]")).click();
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//span[@id='incident_breadcrumb']//b")).click();
		// driver.findElement(By.xpath("(//div[text()='All'])[2]"))).click();
		driver.findElement(By.xpath("//button[contains(text(),'New')]")).click();
		driver.findElement(By.id("sys_display.incident.caller_id")).sendKeys("abel", Keys.ENTER);
		driver.findElement(By.id("incident.short_description")).sendKeys("Short Desc");
		String incidentno = driver.findElement(By.id("incident.number")).getAttribute("value");
		System.out.println("Incident number: " +incidentno);
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@id='incident_table_header_search_control'])[1]")).sendKeys(incidentno,
				Keys.ENTER);
		WebElement createdincident = driver.findElement(By.xpath("//tbody[@class='list2_body']"));
		File src = createdincident.getScreenshotAs(OutputType.FILE);
		File dest = new File("./snaps/inci.png");
		FileUtils.copyFile(src, dest);

		String text = driver.findElement(By.xpath("//tbody[@class='list2_body']//tr//td[3]")).getText();
		System.out.println("incident number in the list: " +text);
		if (text.contentEquals(incidentno)) {
			System.out.println("Incident is created successfully");
		} else
			System.out.println("Incident is not created successfully");
	}

}
