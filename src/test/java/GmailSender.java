import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GmailSender {

	public static void main(String[] args) throws InterruptedException, IOException, AWTException {
		// TODO Auto-generated method stub

		// Setup Webdriver
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		//Please put you First Gmail address and password 
		String firstemail = "Gmailaddress";
		String firstemail1pass ="GmailPassword";
		
		//Please put you Second Gmail address and password 
		String secondemail = "Gmailaddress";
		String secondemailpass ="GmailPassword";
		
		// Calling The function
		gmaillogin(driver,firstemail,firstemail1pass);
		Composemail(driver,secondemail);
		Uploadfiles(driver);
		ClickSendButton(driver);
		Logout(driver);
		deleteCookies(driver);
		gmaillogin(driver,secondemail,secondemailpass);
		emailread(driver);
		download(driver);
		closebrowser(driver);
		
		

	}

	public static void gmaillogin(WebDriver driver,String email,String pass ) {

		//System.out.println(email);
		// driver.findElement(By.xpath(""));

		driver.manage().window().maximize();
		driver.get("https://mail.google.com/?hl=en");

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("testAndcreated@gmail.com");

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@id='identifierNext']")).click();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("testcreated56%");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@id='passwordNext']")).click();

	}

	public static void Composemail(WebDriver driver,String email) {

		// driver.findElement(By.xpath(""));

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[7]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div//div[@role='button']"))
				.click();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//textarea[@rows='1'])[1]")).sendKeys(email);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys("This is a test email");

		String mailbody = "This is the mail body." + "This is the mail body" + "This is mail body\"";

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@role='textbox']")).sendKeys(mailbody);

	}

	public static void Uploadfiles(WebDriver driver) throws IOException, InterruptedException, AWTException {
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(
				By.xpath("//body/div[26]/div[1]//table[1]//table[1]//table//td[4]/div[1]/div[1]/div[1]/div[1]/div[1]")).click();
		
		
		StringSelection ss = new StringSelection("G:\\fileupload\\file.txt");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(5000);
		
		
	}

	public static void ClickSendButton(WebDriver driver) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(
				By.xpath("//body[1]/div[26]//table[1]//table[1]//table[1]//tr[1]/td[1]/div[1]/div[2]/div[1]")).click();
		
		Thread.sleep(5000);
	}
	
	public static void Logout(WebDriver driver) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//header/div[2]/div[3]/div[1]/div[2]/div[1]/a[1]/span[1]")).click();
		Thread.sleep(2000);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//header[1]/div[2]/div[5]/div[4]/div[2]/a[1]")).click();
		Thread.sleep(3000);
	}
	
	public static void deleteCookies(WebDriver driver) throws InterruptedException {
		driver.manage().deleteAllCookies();
		//driver.navigate().refresh();
		Thread.sleep(5000);
	}
	
	public static void emailread(WebDriver driver) {
		driver.findElement(By.xpath("//div[@class='UI']//table[1]/tbody[1]/tr[1]")).click();
	}
	
	public static void download(WebDriver driver) {
		
		Actions action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement hover = driver.findElement(By.xpath("//div[@role='listitem']//div[1]/div[2]/div[3]/div[5]/div[4]/span[1]"));
		action.moveToElement(hover).build().perform();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@role='listitem']//div[1]/div[2]/div[3]/div[5]/div[4]/span/div[1]/div[1]")).click();
		
		
		
	}

	
	public static void closebrowser(WebDriver driver) throws InterruptedException {
		Thread.sleep(15000);
		driver.close();
		driver.quit();
	}

}
