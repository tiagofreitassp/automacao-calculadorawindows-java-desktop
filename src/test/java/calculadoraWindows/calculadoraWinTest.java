package calculadoraWindows;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.*;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class calculadoraWinTest extends calculadoraWinCore {
	public calculadoraWinPage page = new calculadoraWinPage();
	
	@Before
	public void criarDriverWin() throws IOException, InterruptedException {
		getDriver();
	}
	
	@Test
	public void test1_Soma() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		page.soma();
	}
	
	@Test
	public void test2_Sutracao() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		page.subtracao();
	}
	
	@Test
	public void test3_Multiplicacao() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		page.multiplicao();
	}
	
	@Test
	public void test4_Divisao() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		page.divisao();
	}
	
	@After
	public void fecharDriver() throws IOException, InterruptedException {
		fecharDV();
	}
}
