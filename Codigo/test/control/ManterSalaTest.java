package test.control;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import control.ManterSala;
import model.Sala;
import exception.PatrimonioException;
import java.sql.SQLException;


public class ManterSalaTest {

  private static Sala sala;
	private static Sala sala_new;
	
	@Before
	public void setUp() throws SQLException, PatrimonioException{
		sala = new Sala("codigo", "descricao", "66");
		sala_new = new Sala("novo_codigo","nova_descricao","88");
	}
	
	@After
	public void tearDown() throws SQLException, PatrimonioException{
		ManterSala.getInstance().excluir(sala);
		ManterSala.getInstance().excluir(sala_new);
		
	}
	
	@AfterClass
	public static void tearDownClass() throws SQLException, PatrimonioException{
		sala = null;
		sala_new = null;
	}
	
	@Test
	public void testGetInstance() {
		assertTrue("Verifica método getInstance().", ManterSala.getInstance() instanceof ManterSala);
	}
	
	@Test
	public void testSingleton() {
		ManterSala p = ManterSala.getInstance();
		ManterSala q = ManterSala.getInstance();
		assertSame("Testando o Padrao Singleton", p, q);
	}


	@Test
	public void testInserir() throws PatrimonioException, SQLException {
		setUp();
		ManterSala.getInstance().inserir("codigo", "descricao", "66");
		assertNotNull("Falha ao inserir", ManterSala.getInstance().procurarNoVetor(sala));
		tearDown();
	}

	@Test
	public void testAlterar() throws PatrimonioException, SQLException {
		setUp();
		ManterSala.getInstance().inserir("codigo", "descricao", "66");
		ManterSala.getInstance().alterar("codigo", "descricao", "88", sala);
		assertNotNull("Falha ao alterar", ManterSala.getInstance().procurarNoVetor(sala));
		tearDown();
	}

	@Test
	public void testExcluir() throws SQLException, PatrimonioException {
		setUp();
		ManterSala.getInstance().inserir("codigo", "descricao", "66");
		ManterSala.getInstance().excluir(sala);
		assertNull("Falha ao excluir", ManterSala.getInstance().procurarNoVetor(sala));
		tearDown();
	}

}
