package SQLServer;

import org.openqa.selenium.By;

public class sqlServerMetodosBKP extends sqlServerConexoes{
	private static sqlServerScreenshot ss = new sqlServerScreenshot();

	public void autenticacao() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("password")).sendKeys("T!@go@0718");
		Thread.sleep(1000);
		driver.findElement(By.id("connect")).click();
		Thread.sleep(3000);
	}
	
	//--- 06 - VERIFICAR OS DADOS DO PARTICIPANTE NA BASE EMBRACE ---
	public void verificarDadosDoParticipanteNaBaseEmbrace1() throws InterruptedException {
		String scriptSQL0 = "USE [Embrace_BZ_UAT85]; "
				+ "DECLARE @CODE nvarchar(30); "
				+ "SET @CODE = '3662377'; "
				+ "SELECT ACCOUNT, Level_ID, CupsMemberId, DOB, SEX, FIRST_NAME, LAST_NAME, ADDRESS1, CITY, EMAIL_ADDRESS, * FROM ah_member WHERE ACCOUNT = @CODE; "
				+ "SELECT a.EligibilityStartDate, a.EligibilityEndDate, c.GroupEmployerNumber, a.OUID, * FROM member.eligibility A, ah_master_organizational_unit C WHERE a.MemberID = @CODE AND a.OUID = c.OUID; "
				+ "SELECT * FROM ah_member_level_history WHERE ACCOUNT = @CODE; "
				+ "SELECT * FROM ah_member_productcode_eligibility WHERE ACCOUNT = @CODE;";

		driver.findElement(By.name("New Query")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.id("WpfTextView")).sendKeys(scriptSQL0);
		
		Thread.sleep(6000);
		
		driver.findElement(By.name("Execute")).click();
		
		Thread.sleep(1500);
		ss.gerarScreenshots("Passo01");
	}
	
	//--- 01 - VERIFICAR OS DADOS DO PARTICIPANTE NA BASE EMBRACE ---
	public void verificarDadosDoParticipanteNaBaseEmbrace2() throws InterruptedException {
		String scriptSQL0 = "USE [Embrace_BZ_UAT85]; "
				+ "SELECT m.ACCOUNT, m.Level_ID, m.CupsMemberId, m.DOB, m.SEX, m.FIRST_NAME, m.LAST_NAME, m.ADDRESS1, m.CITY, m.EMAIL_ADDRESS, e.OUID, m.OUID, u.GroupEmployerNumber, e.EligibilityTypeID, e.EligibilityEndDate FROM ah_member m INNER JOIN member.eligibility e on m.account = e.memberid LEFT OUTER JOIN AH_MASTER_ORGANIZATIONAL_UNIT u on coalesce(e.OUID, m.ouid) = u.OUID WHERE m.ACCOUNT = '3662377';";

		driver.findElement(By.name("New Query")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.id("WpfTextView")).sendKeys(scriptSQL0);
		
		Thread.sleep(6000);
		
		driver.findElement(By.name("Execute")).click();
		
		Thread.sleep(1500);
		ss.gerarScreenshots("Passo02");
	}
	
	//--- 02 - INSERIR DADOS DO PARTICIPANTE SAS NAS TABELAS STAGE ---
	public void inserirDadosDoParticipanteSASNasTabelasStage3() throws InterruptedException {
		String scriptSQL0 = "USE Embrace_BZ_UAT85_Stage; "
				+ "DECLARE @CODE nvarchar(30); "
				+ "SET @CODE = '8643616'; "
				+ "DECLARE @Member [dbo].[MemberTbType]; "
				+ "INSERT INTO @Member(UniqueCode, DOB, SEX, MemberName, Address, CITY, STATE, EMAIL ) VALUES (@CODE, '19780119', 'M', 'LUCIANO SANTOS DA CONCEICAO', 'JOAO JOSE DE CARVALHO 34', 'ARACRUZ', '', 'flavio.eduardo@healthways.com');"
				+ "DECLARE @Employer [dbo].[MemberEmployerTbType];	"
				+ "INSERT INTO @Employer(UniqueCode , EmployerGroupCode, EmployerType, EmployerStatus)VALUES (@CODE, '8DWVD', '1', '1')\r\n" + 
				"DECLARE @Program [dbo].[MemberProgramTbType];"
				+ "INSERT INTO @Program(UniqueCode , ProgramName, ProgramStatus, OrigemIndicacao)VALUES (@CODE, 'COLUNA ATIVA', 'A1',''); "
				+ "EXEC SAS.usp_MemberStage_Load @Member, @Employer, @Program";
		driver.findElement(By.name("New Query")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.id("WpfTextView")).sendKeys(scriptSQL0);
		
		Thread.sleep(6000);
		
		driver.findElement(By.name("Execute")).click();
		
		Thread.sleep(1500);
		ss.gerarScreenshots("Passo03");
	}
	
	//--- 03 - VERIFICAR SE DADOS DO PARTICIPANTE FORAM INSERIDOS NAS TABELAS STAGE ---
	public void verificarSeDadosDoParticipanteFOramInseridosNasTabelasStage4() throws InterruptedException {
		String scriptSQL0 = "USE Embrace_BZ_UAT85_Stage; "
				+ "DECLARE @CODE nvarchar(30); "
				+ "SET @CODE = '71992'; "
				+ "Select isProcessed, * from SAS.Member where JobID = @CODE; "
				+ "Select isProcessed, * from SAS.MemberEmployer where JobID = @CODE; "
				+ "Select isProcessed, * from SAS.MemberProgram where JobID = @CODE;";

		driver.findElement(By.name("New Query")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.id("WpfTextView")).sendKeys(scriptSQL0);
		
		Thread.sleep(6000);
		
		driver.findElement(By.name("Execute")).click();
		
		Thread.sleep(1500);
		ss.gerarScreenshots("Passo04");
	}
	
	//--- 04 - EXECUTAR PROCEDURE DE CLASSIFICAÇÃO SAS ---
	public void executarProcedureDeClassificacaoSAS5() throws InterruptedException {
		String scriptSQL0 = "EXEC SAS.usp_MemberConsolidated_Upsert @JobID = '71992'";

		driver.findElement(By.name("New Query")).click();
				
		Thread.sleep(1000);
				
		driver.findElement(By.id("WpfTextView")).sendKeys(scriptSQL0);
				
		Thread.sleep(2500);
				
		driver.findElement(By.name("Execute")).click();
				
		Thread.sleep(9000);
		ss.gerarScreenshots("Passo05");
	}
	
	//--- 05 - VERIFICAR TABELA DE STATUS/RETRORNO SAS ---
	public void verificarTabelaDeStatusRetornoSAS6() throws InterruptedException {
		String scriptSQL0 = "USE Embrace_BZ_UAT85_Stage; "
				+ "Select * from Stage.StatusETL where JobID = '71992';";

		driver.findElement(By.name("New Query")).click();
				
		Thread.sleep(1000);
				
		driver.findElement(By.id("WpfTextView")).sendKeys(scriptSQL0);
				
		Thread.sleep(2500);
				
		driver.findElement(By.name("Execute")).click();
				
		Thread.sleep(1500);
		ss.gerarScreenshots("Passo06");
	}
	
	//--- 06 - VERIFICAR OS DADOS DO PARTICIPANTE NA BASE EMBRACE ---
	public void verificarDadosDoParticipanteNaBaseEmbrace7() throws InterruptedException {
		String scriptSQL0 = "USE [Embrace_BZ_UAT85]; "
				+ "DECLARE @CODE nvarchar(30); "
				+ "SET @CODE = '3662377'; "
				+ "SELECT ACCOUNT, Level_ID, CupsMemberId, DOB, SEX, FIRST_NAME, LAST_NAME, ADDRESS1, CITY, EMAIL_ADDRESS, * FROM ah_member WHERE ACCOUNT = @CODE; "
				+ "SELECT a.EligibilityStartDate, a.EligibilityEndDate, c.GroupEmployerNumber, a.OUID, * FROM member.eligibility A, ah_master_organizational_unit C WHERE a.MemberID = @CODE AND a.OUID = c.OUID; "
				+ "SELECT * FROM ah_member_level_history WHERE ACCOUNT = @CODE; "
				+ "SELECT * FROM ah_member_productcode_eligibility WHERE ACCOUNT = @CODE;";

		driver.findElement(By.name("New Query")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.id("WpfTextView")).sendKeys(scriptSQL0);
		
		Thread.sleep(6000);
		
		driver.findElement(By.name("Execute")).click();
		
		Thread.sleep(1500);
		ss.gerarScreenshots("Passo07");
	}	
}
