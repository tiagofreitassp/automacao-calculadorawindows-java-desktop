package SQLServer;

import java.sql.SQLException;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class sqlServerTest {
	private static int account = 3662377;
	private static String tipo = "COLUNA ATIVA";
	private static String status = "A1";
	
	private static sqlServerConexoes s = new sqlServerConexoes();
	private static sqlServerMetodos m = new sqlServerMetodos(account,tipo,status);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		s.abrirWinium();
			try {
				m.conexaoDB();
				System.out.println("<<<Conexão realizada com sucesso!>>>");
				
				m.selecionarOsDadosDoParticipanteNaBaseEmbrace();
				
				Thread.sleep(1000);
				
				m.selecionarJOBID();
			} catch (SQLException e) {
				System.out.println("<<<Não foi possivel a conexão com o banco de dados!>>>");
				e.printStackTrace();
			}
			s.conexao();
	}

	@After
	public void tearDown() throws Exception {
		s.fecharSqlServer();
	}

	@Test
	public void test() throws InterruptedException {
		m.autenticacao();
		m.verificarDadosDoParticipanteNaBaseEmbrace1();
		m.verificarDadosDoParticipanteNaBaseEmbrace2();
		m.verificarSeDadosDoParticipanteFOramInseridosNasTabelasStage4();
		m.executarProcedureDeClassificacaoSAS5();
		m.verificarTabelaDeStatusRetornoSAS6();
		m.verificarDadosDoParticipanteNaBaseEmbrace7();
	}
}
