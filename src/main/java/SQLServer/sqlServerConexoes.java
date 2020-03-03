package SQLServer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

public class sqlServerConexoes{
	static WiniumDriver driver;
	
	private static sqlServerScreenshot ss = new sqlServerScreenshot();

	public static void abrirWinium() {
		try {
	        String comando = ".//drivers//Winium.Desktop.Driver.exe";
	        Process processo = Runtime.getRuntime().exec(comando);
	    } catch (IOException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	public void conexao() throws MalformedURLException {
		DesktopOptions option = new DesktopOptions();
		option.setApplicationPath("C:\\Program Files (x86)\\Microsoft SQL Server\\120\\Tools\\Binn\\ManagementStudio\\Ssms.exe");

		driver = new WiniumDriver(new URL("http://localhost:9999"), option);
	}
	
	public void testeScript() throws InterruptedException {
		String scriptSQL0 = "SELECT ACCOUNT, Level_ID, CupsMemberId, DOB, SEX, FIRST_NAME, LAST_NAME, ADDRESS1, CITY, EMAIL_ADDRESS, "
				+ "* FROM ah_member WHERE ACCOUNT = 3662377";

		driver.findElement(By.name("New Query")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.id("WpfTextView")).sendKeys(scriptSQL0);
		
		Thread.sleep(2000);
		
		driver.findElement(By.name("Execute")).click();
		
		Thread.sleep(1500);
		ss.gerarScreenshots("imagemScript");
	}
	
	public void fecharSqlServer() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.name("Fechar")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.name("No")).click();
	}
}
