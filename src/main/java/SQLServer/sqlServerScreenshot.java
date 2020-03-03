package SQLServer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class sqlServerScreenshot extends sqlServerConexoes {
	static String nomePasta;
	
	public static void dataAtual() {
		Date dataAtual = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		nomePasta = sdf.format(dataAtual);
	}
	
	public void gerarScreenshots(String nomeImagem) {
		dataAtual();
		try {
			File f = new File("./Screenshots/"+nomePasta);
			f.mkdir();
			
			TakesScreenshot ts = (TakesScreenshot)driver;
			
			File source = ts.getScreenshotAs(OutputType.FILE);
			
			org.apache.commons.io.FileUtils.copyFile(source, new File(f+"\\"+nomeImagem+".png"));
			
			System.out.println("Screenshot capturado de "+nomeImagem+"!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
