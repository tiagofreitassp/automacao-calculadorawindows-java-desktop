package calculadoraWindows;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;

public class calculadoraWinPage extends calculadoraWinCore {
	private String btnZero = "Zero";
	private String btnUm = "Um";
	private String btnDois = "Dois";
	private String btnTres = "Tr�s";
	private String btnQuatro = "Quatro";
	private String btnCinco = "Cinco";
	private String btnSeis = "Seis";
	private String btnSete = "Sete";
	private String btnOito = "Oito";
	private String btnNove = "Nove";
	private String btnCE = "clearEntryButton";
	private String btnC = "clearButton";
	private String btnVoltar = "backSpaceButton";
	private String btnVirg = "Separador Decimal";
	private String btnDiv = "divideButton";
	private String btnMult = "multiplyButton";
	private String btnSub = "minusButton";
	private String btnSom = "plusButton";
	private String btnIgual = "Igual a";
	private String btnFechar = "Close";
	
	private String lblResultado = "CalculatorResults";
	
	private String msgNaoEpossivelDividirPorZero = "Exibi��o � N�o � poss�vel dividir por zero";
	
	public calculadoraWinMethods me = new calculadoraWinMethods();
	
	public void soma() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		criarPastaEvidencia("Calc Win Soma");
		
		me.aguardar(3000);
		
		me.clicar(By.name(btnUm));
		me.clicar(By.name(btnDois));
		me.clicar(By.name(btnTres));
		me.clicar(By.id(btnSom));
		me.clicar(By.name(btnQuatro));
		me.clicar(By.name(btnCinco));
		me.clicar(By.name(btnSeis));
		me.clicar(By.name(btnIgual));
		
		me.validarTextoPorAtributo(By.id(lblResultado), "Name", "Exibi��o � 579");
		gerarScreenshots("Soma");

		me.aguardar(1000);
		gerarEvidenciaNoWord("Realizar uma Soma", "01", "Calculadora Windows");
	}	
	
	public void subtracao() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		criarPastaEvidencia("Calc Win Subtra��o");
		
		me.clicar(By.name(btnSete));
		me.clicar(By.name(btnOito));
		me.clicar(By.name(btnNove));
		me.clicar(By.id(btnSub));
		me.clicar(By.name(btnQuatro));
		me.clicar(By.name(btnCinco));
		me.clicar(By.name(btnSeis));
		me.clicar(By.name(btnIgual));
		
		me.validarTextoPorAtributo(By.id(lblResultado), "Name", "Exibi��o � 333");
		gerarScreenshots("Subtra��o");
		
		me.aguardar(1000);
		gerarEvidenciaNoWord("Realizar uma Subtra��o", "02", "Calculadora Windows");
	}
	
	public void multiplicao() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		criarPastaEvidencia("Calc Win Multiplica��o");
		
		me.clicar(By.name(btnTres));
		me.clicar(By.name(btnUm));
		me.clicar(By.id(btnMult));
		me.clicar(By.name(btnTres));
		me.clicar(By.name(btnIgual));
		
		me.validarTextoPorAtributo(By.id(lblResultado), "Name", "Exibi��o � 93");
		gerarScreenshots("Multiplica��o");
		
		me.aguardar(1000);
		gerarEvidenciaNoWord("Realizar uma Multiplica��o", "03", "Calculadora Windows");
	}
	
	public void divisao() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		criarPastaEvidencia("Calc Win Divis�o");
		
		me.clicar(By.name(btnTres));
		me.clicar(By.name(btnZero));
		me.clicar(By.id(btnDiv));
		me.clicar(By.name(btnZero));
		me.clicar(By.name(btnIgual));
		
		me.validarTextoPorAtributo(By.id(lblResultado), "Name", msgNaoEpossivelDividirPorZero);
		gerarScreenshots("Divis�o Erro");
		
		me.clicar(By.id(btnVoltar));
		
		me.clicar(By.name(btnTres));
		me.clicar(By.name(btnZero));
		me.clicar(By.id(btnDiv));
		me.clicar(By.name(btnTres));
		me.clicar(By.name(btnIgual));
		
		me.validarTextoPorAtributo(By.id(lblResultado), "Name", "Exibi��o � 10");
		gerarScreenshots("Divis�o");
		
		me.aguardar(1000);
		gerarEvidenciaNoWord("Realizar uma Divis�o", "04", "Calculadora Windows");
	}
}
