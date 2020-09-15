package calculadoraWindows;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.By;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class calculadoraWinCore{
	private static WiniumDriver driver;
	private static String nomePasta;
	public static ATUTestRecorder grav;

    private File pastaEvidencias;
	
	public static WiniumDriver getDriver() throws IOException, InterruptedException {
		if(driver == null) {
			criarDriverWindows();
		}
		return driver;
	}
	
	public static void criarDriverWindows() throws IOException {
		try {
			String comando = ".//drivers//Winium.Desktop.Driver.exe";
		    Process processo = Runtime.getRuntime().exec(comando);
	  
		    DesktopOptions option = new DesktopOptions();
			option.setApplicationPath("C:\\Windows\\System32\\calc.exe");
			driver = new WiniumDriver(new URL("http://localhost:9999"), option);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public static void fecharDV() throws IOException, InterruptedException {
		getDriver().findElement(By.id("Close")).click();
	}

	public void criarPastaEvidencia(String nPasta) throws InterruptedException, ATUTestRecorderException {
		Date dataAtual = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		nomePasta = sdf.format(dataAtual);
		
        pastaEvidencias = new File("./Screenshots/"+nPasta+" "+nomePasta);
        pastaEvidencias.mkdir();
        
        iniciarVideoEvidencia(nPasta);
    }
	
    //Tira screenshot apenas da tela principal caso tenha outro monitor
    public void gerarScreenshots(String nomeImagem) throws AWTException, IOException{
        try {
        	Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = new Robot().createScreenCapture(screenRect);
            ImageIO.write(screenFullImage, "png", new File(pastaEvidencias+"\\"+nomeImagem+".png"));
		} catch (Exception e) {
			e.getMessage();
		}
    }
	
	public void gerarEvidenciaNoWord(String cenario, String qa, String titulo) throws IOException, InvalidFormatException, IOException, ATUTestRecorderException {
        finalizarVideoEvidencia();
		
		XWPFDocument doc = new XWPFDocument(new FileInputStream(new File("Template.docx")));
        XWPFParagraph par = doc.createParagraph();
        XWPFRun run1 = par.createRun();
        XWPFRun run2 = par.createRun();
        XWPFRun run3 = par.createRun();

        run1.setText("CT" + String.valueOf(qa) + " - ");
        run1.setText(cenario);
        run1.setFontSize(11);
        run1.setColor("595959");
        run1.setFontFamily("Calibri Light");

        run2.addBreak();
        run2.addBreak();
        run2.setText("3. EVIDÊNCIAS DOS CASOS DE TESTE");
        run2.setBold(true);
        run2.setFontSize(11);
        run2.setColor("595959");
        run2.setFontFamily("Calibri Light");

        String[] paths = pastaEvidencias.list();

        for (String path : paths) {
            String imagem = pastaEvidencias + "\\" + path;
            FileInputStream is = new FileInputStream(imagem);

            run3.addBreak();
            run3.setText(imagem);
            run3.setFontSize(11);
            run3.setColor("595959");
            run3.setFontFamily("Calibri Light");
            run3.addBreak();
            run3.addPicture(is, Document.PICTURE_TYPE_PNG, imagem, Units.toEMU(513), Units.toEMU(313));
            run3.addBreak();
            is.close();

            String documento = pastaEvidencias + "\\" + "CT-" + qa + " - " + titulo +".doc";
            FileOutputStream fos = new FileOutputStream(documento);
            doc.write(fos);
            fos.close();
        }
    }
	
	public void iniciarVideoEvidencia(String nomeDoVideoEv) throws ATUTestRecorderException {
		DateFormat df = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
		Date date = new Date();
		
		grav = new ATUTestRecorder("./Screenshots/"+nomeDoVideoEv+" "+df.format(date),false);
		grav.start();
		//Manual em portugues
		//https://medium.com/@alanpaulooficial/como-gravar-evid%C3%AAncias-de-automa%C3%A7%C3%A3o-de-testes-em-v%C3%ADdeos-utilizando-o-selenium-webdriver-c12a0dab8452
	}
	
	public static void finalizarVideoEvidencia() throws ATUTestRecorderException {
		grav.stop();
	}
}
