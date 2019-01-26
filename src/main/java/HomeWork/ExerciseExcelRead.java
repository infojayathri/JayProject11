package HomeWork;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ExcelREAD.ExcelRead;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import Utility.*;
import ExtentReport.*;



public class ExerciseExcelRead {
	WebDriver driver;
	ExcelRead excelFile= new ExcelRead();
	ExtentReport extentreport = new ExtentReport();
	 
@BeforeTest
	public void setup() throws IOException, ParseException
	{
	
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(excelFile.readExcel(1,0,".\\Data\\DataDriven.xlsx","Sheet1"));
		extentreport.setUpReport();
		
	}
@Test
public void testAdd() throws IOException, ParseException, InterruptedException
	{
	extentreport.startTestCase("New Telecom User");
		driver.findElement(By.xpath("//*[@id=\"one\"]/div/div[1]/div[1]/h3/a")).click();
		
		WebElement radio1 = driver.findElement(By.xpath("//*[@id=\"main\"]/div/form/div/div[1]/label"));
		WebElement radio2 = driver.findElement(By.xpath("//*[@id=\"main\"]/div/form/div/div[2]/label"));
		String Status = excelFile.readExcel(1,6,".\\data\\DataDriven.xlsx", "Sheet1");
		
		if (Status.equals(radio1))
				{
			radio1.click();
				}else
				{
					radio2.click();
				}
				
		
		//driver.findElement(By.xpath("//*[@id=\"main\"]/div/form/div/div[1]/label")).click();
		driver.findElement(By.id("fname")).sendKeys(excelFile.readExcel(1,1,".\\Data\\DataDriven.xlsx","Sheet1"));
		driver.findElement(By.id("lname")).sendKeys(excelFile.readExcel(1,2,".\\Data\\DataDriven.xlsx","Sheet1"));
		driver.findElement(By.id("email")).sendKeys(excelFile.readExcel(1,3,".\\Data\\DataDriven.xlsx","Sheet1"));
		driver.findElement(By.name("addr")).sendKeys(excelFile.readExcel(1,4,".\\Data\\DataDriven.xlsx","Sheet1"));
		driver.findElement(By.id("telephoneno")).sendKeys(excelFile.readExcel(1,5,".\\Data\\DataDriven.xlsx","Sheet1"));
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/form/div/div[9]/ul/li[1]/input")).click();
		//driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);		
		Thread.sleep(2000);
		String webTitle = driver.getTitle();
		
	if (webTitle.equals("Guru99 telecom"))
		{
			extentreport.logEventsPass("My titile is passing");			
		//Assert.assertEquals(webTitle, "Guru99 Bank New Customer Entry Page");
	
		}
		else 
		{
			extentreport.logEventsFail("This is failing");
			//Assert.assertEquals(webTitle, "Guru99 Bank Customer Registration Page");
		}
		
		extentreport.createFinalReport();
	}


@AfterTest
	public void tearDown() throws IOException
	{
	driver.close();
	driver.quit();
	}

}
