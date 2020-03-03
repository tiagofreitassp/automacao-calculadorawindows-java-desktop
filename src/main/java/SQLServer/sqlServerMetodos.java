package SQLServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.By;

public class sqlServerMetodos extends sqlServerConexoes{
	private static sqlServerScreenshot ss = new sqlServerScreenshot();
	private static String tipo,status;
	protected static int account;
	
	private static String URL ="jdbc:sqlserver://UTBRTRA01;" +  
			"databaseName=Embrace_BZ_UAT85;";//se não for acessar localmente mude localhost pelo nome do servidor  
	private static String usuario = "tiago.lopes";//esse usuário é um sysadmin ele tem todos os poderes, é bom que se crie um login e usuário a parte  
	private static String password = "T!@go@0718";  
	private static String driverSQL ="com.microsoft.sqlserver.jdbc.SQLServerDriver" ;//Esse é o nome do driver, que na internet você vai encontrar de varias maneiras, mas só esse resolveu meus problemas  
	public static Connection con = null;
	public static java.sql.Statement st;
	public static java.sql.PreparedStatement ps;
	public static ResultSet rs;
	public static String sql1,sql2;
	
	private static String SEXdb,FIRST_NAMEdb,LAST_NAMEdb,ADDRESS1db,CITYdb,EMAIL_ADDRESSdb,GroupEmployerNumberdb,DOBdb;
	private static int ACCOUNTdb,Level_IDdb,CupsMemberIddb, JOBIDdb;
	
	public sqlServerMetodos(int acc, String tp, String st) {
		this.account=acc;
		this.tipo=tp;
		this.status=st;
	}

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
				+ "SET @CODE = "+account+"; "
				+ "SELECT ACCOUNT, Level_ID, CupsMemberId, DOB, SEX, FIRST_NAME, LAST_NAME, ADDRESS1, CITY, EMAIL_ADDRESS, * FROM ah_member WHERE ACCOUNT = @CODE; "
				+ "SELECT a.EligibilityStartDate, a.EligibilityEndDate, c.GroupEmployerNumber, a.OUID, * FROM member.eligibility A, ah_master_organizational_unit C WHERE a.MemberID = @CODE AND a.OUID = c.OUID; "
				+ "SELECT * FROM ah_member_level_history WHERE ACCOUNT = @CODE; "
				+ "SELECT * FROM ah_member_productcode_eligibility WHERE ACCOUNT = @CODE;";

		driver.findElement(By.name("New Query")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.id("WpfTextView")).sendKeys(scriptSQL0);
		
		Thread.sleep(5000);
		
		driver.findElement(By.name("Execute")).click();
		
		Thread.sleep(1500);
		ss.gerarScreenshots("Passo01");
	}
	
	//--- 01 - VERIFICAR OS DADOS DO PARTICIPANTE NA BASE EMBRACE ---
	public void verificarDadosDoParticipanteNaBaseEmbrace2() throws InterruptedException {
		String scriptSQL0 = "USE [Embrace_BZ_UAT85]; "
				+ "SELECT m.ACCOUNT, m.Level_ID, m.CupsMemberId, m.DOB, m.SEX, m.FIRST_NAME, m.LAST_NAME, m.ADDRESS1, m.CITY, m.EMAIL_ADDRESS, e.OUID, m.OUID, u.GroupEmployerNumber, e.EligibilityTypeID, e.EligibilityEndDate FROM ah_member m INNER JOIN member.eligibility e on m.account = e.memberid LEFT OUTER JOIN AH_MASTER_ORGANIZATIONAL_UNIT u on coalesce(e.OUID, m.ouid) = u.OUID WHERE m.ACCOUNT = "+account+"";

		driver.findElement(By.name("New Query")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.id("WpfTextView")).sendKeys(scriptSQL0);
		
		Thread.sleep(5000);
		
		driver.findElement(By.name("Execute")).click();
		
		Thread.sleep(1500);
		ss.gerarScreenshots("Passo02");
	}
	
	//--- 02 - INSERIR DADOS DO PARTICIPANTE SAS NAS TABELAS STAGE ---
	public void inserirDadosDoParticipanteSASNasTabelasStage3() throws InterruptedException, SQLException {
		String scriptSQL0 = "USE Embrace_BZ_UAT85_Stage; "
				+ "DECLARE @CODE nvarchar(30); "
				+ "SET @CODE = '"+CupsMemberIddb+"'; "
				+ "DECLARE @Member [dbo].[MemberTbType]; "
				+ "INSERT INTO @Member(UniqueCode, DOB, SEX, MemberName, Address, CITY, STATE, EMAIL ) VALUES (@CODE, '"+DOBdb+"', '"+SEXdb+"', '"+FIRST_NAMEdb+"','"+LAST_NAMEdb+"', '"+ADDRESS1db+"', '"+CITYdb+"', '"+EMAIL_ADDRESSdb+"');"
				+ "DECLARE @Employer [dbo].[MemberEmployerTbType];	"
				+ "INSERT INTO @Employer(UniqueCode , EmployerGroupCode, EmployerType, EmployerStatus)VALUES (@CODE, '"+GroupEmployerNumberdb+"', '1', '1')\r\n" + 
				"DECLARE @Program [dbo].[MemberProgramTbType];"
				+ "INSERT INTO @Program(UniqueCode , ProgramName, ProgramStatus, OrigemIndicacao)VALUES (@CODE, '"+tipo+"', '"+status+"',''); "
				+ "EXEC SAS.usp_MemberStage_Load @Member, @Employer, @Program";
		driver.findElement(By.name("New Query")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.id("WpfTextView")).sendKeys(scriptSQL0);
		
		Thread.sleep(5000);
		
		driver.findElement(By.name("Execute")).click();
		
		Thread.sleep(1500);
		ss.gerarScreenshots("Passo03");
	}
	
	//--- 03 - VERIFICAR SE DADOS DO PARTICIPANTE FORAM INSERIDOS NAS TABELAS STAGE ---
	public void verificarSeDadosDoParticipanteFOramInseridosNasTabelasStage4() throws InterruptedException {
		String scriptSQL0 = "USE Embrace_BZ_UAT85_Stage; "
				+ "DECLARE @CODE nvarchar(30); "
				+ "SET @CODE = "+JOBIDdb+"; "
				+ "Select isProcessed, * from SAS.Member where JobID = @CODE; "
				+ "Select isProcessed, * from SAS.MemberEmployer where JobID = @CODE; "
				+ "Select isProcessed, * from SAS.MemberProgram where JobID = @CODE;";

		driver.findElement(By.name("New Query")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.id("WpfTextView")).sendKeys(scriptSQL0);
		
		Thread.sleep(5000);
		
		driver.findElement(By.name("Execute")).click();
		
		Thread.sleep(1500);
		ss.gerarScreenshots("Passo04");
	}
	
	//--- 04 - EXECUTAR PROCEDURE DE CLASSIFICAÇÃO SAS ---
	public void executarProcedureDeClassificacaoSAS5() throws InterruptedException {
		String scriptSQL0 = "EXEC SAS.usp_MemberConsolidated_Upsert @JobID = "+JOBIDdb+"";

		driver.findElement(By.name("New Query")).click();
				
		Thread.sleep(1000);
				
		driver.findElement(By.id("WpfTextView")).sendKeys(scriptSQL0);
				
		Thread.sleep(2000);
				
		driver.findElement(By.name("Execute")).click();
				
		Thread.sleep(3500);
		ss.gerarScreenshots("Passo05");
	}
	
	//--- 05 - VERIFICAR TABELA DE STATUS/RETRORNO SAS ---
	public void verificarTabelaDeStatusRetornoSAS6() throws InterruptedException {
		String scriptSQL0 = "USE Embrace_BZ_UAT85_Stage; "
				+ "Select * from Stage.StatusETL where JobID = "+JOBIDdb+";";

		driver.findElement(By.name("New Query")).click();
				
		Thread.sleep(1000);
				
		driver.findElement(By.id("WpfTextView")).sendKeys(scriptSQL0);
				
		Thread.sleep(2000);
				
		driver.findElement(By.name("Execute")).click();
				
		Thread.sleep(1500);
		ss.gerarScreenshots("Passo06");
	}
	
	//--- 06 - VERIFICAR OS DADOS DO PARTICIPANTE NA BASE EMBRACE ---
	public void verificarDadosDoParticipanteNaBaseEmbrace7() throws InterruptedException {
		String scriptSQL0 = "USE [Embrace_BZ_UAT85]; "
				+ "DECLARE @CODE nvarchar(30); "
				+ "SET @CODE = "+account+"; "
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
	
	public Connection conexaoDB() throws SQLException {
		try {   
	        Class.forName(driverSQL);  
	        con = DriverManager.getConnection(URL,usuario,password);
	        return con;  
	      } catch (ClassNotFoundException e) {  
	               throw new SQLException(e.getMessage());  
	      } 
    } 
	
	public void selecionarOsDadosDoParticipanteNaBaseEmbrace() throws SQLException {
		sql1 = "USE [Embrace_BZ_UAT85]; "
				+ "SELECT m.ACCOUNT, m.Level_ID, m.CupsMemberId, m.DOB, m.SEX, m.FIRST_NAME, m.LAST_NAME, m.ADDRESS1, m.CITY, m.EMAIL_ADDRESS, e.OUID, m.OUID, u.GroupEmployerNumber, e.EligibilityTypeID, e.EligibilityEndDate FROM ah_member m INNER JOIN member.eligibility e on m.account = e.memberid LEFT OUTER JOIN AH_MASTER_ORGANIZATIONAL_UNIT u on coalesce(e.OUID, m.ouid) = u.OUID WHERE m.ACCOUNT = '"+account+"';";
		st = con.createStatement();
		rs = st.executeQuery(sql1);
		
		rs.next();
		
		ACCOUNTdb = rs.getInt("ACCOUNT");
		Level_IDdb = rs.getInt("Level_ID");
		CupsMemberIddb = rs.getInt("CupsMemberId");
		DOBdb = rs.getString("DOB");
		SEXdb = rs.getString("SEX");
		FIRST_NAMEdb = rs.getString("FIRST_NAME");
		LAST_NAMEdb = rs.getString("LAST_NAME");
		ADDRESS1db = rs.getString("ADDRESS1");
		CITYdb = rs.getString("CITY");
		EMAIL_ADDRESSdb = rs.getString("EMAIL_ADDRESS");
		GroupEmployerNumberdb = rs.getString("GroupEmployerNumber");
		
		System.out.println("Dados do paciente: "+ACCOUNTdb+" > "+Level_IDdb+" > "+CupsMemberIddb+" > "+DOBdb+" > "+SEXdb+" > "+FIRST_NAMEdb+" > "+LAST_NAMEdb+" > "+ADDRESS1db+" > "+CITYdb+" > "+EMAIL_ADDRESSdb+" > "+GroupEmployerNumberdb);
	}
	
	public void selecionarJOBID() throws SQLException {
		sql2 = "USE Embrace_BZ_UAT85_Stage; "
				+"DECLARE @CODE nvarchar(30); " 
				+"SET @CODE = '71992'; "
				+"Select JobID from SAS.Member where JobID = @CODE";
		st = con.createStatement();
		rs = st.executeQuery(sql2);
		
		rs.next();
		JOBIDdb = rs.getInt("JobID");
		System.out.println("Número do JobID: "+JOBIDdb);
		fecharConexao();
	}
	
	public static void fecharConexao() {
		try {
			con.close();
			System.out.println("<<<Conexão encerrada com sucesso!>>>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
