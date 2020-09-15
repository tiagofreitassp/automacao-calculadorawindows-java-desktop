package calculadoraWindows;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

public class calculadoraWinMethods extends calculadoraWinCore{
    public Boolean isPresent;
    public WebDriverWait wait;

    public void aguardar(long seg) throws InterruptedException {
    	Thread.sleep(seg);
    }

    public void clicar(By by) throws IOException, InterruptedException {
		getDriver().findElement(by).click();
	}

	public void validarTextoPorAtributo(By by, String atributo, String texto) throws IOException, InterruptedException {
		aguardar(1000);
		String output = getDriver().findElement(by).getAttribute(atributo);
		Assert.assertEquals(texto, output);
	}
}
