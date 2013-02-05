package test.persistence;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Vector;

import model.Sala;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.SalaDAO;
import exception.PatrimonioException;


public class SalaDAOTest {
  
	@BeforeClass
	public static void setUpClass() throws PatrimonioException, SQLException {
	
	}
	
	@AfterClass
	public static void tearDownClass() throws SQLException, PatrimonioException {
		
	}	
	
	@Test
	public void testInstance() {
		assertTrue("Instanciando SalaDAO", SalaDAO.getInstance() instanceof SalaDAO);
	}
	
	@Test
	public void testSingleton() {
		SalaDAO inst1 = SalaDAO.getInstance();
		SalaDAO inst2 = SalaDAO.getInstance();
		assertSame("Testando o Padrao Singleton", inst2, inst1);
	}
}
