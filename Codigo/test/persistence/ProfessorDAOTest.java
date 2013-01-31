package test.persistence;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Professor;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import exception.ClienteException;

import persistence.FactoryConnection;
import persistence.ProfessorDAO;

public class ProfessorDAOTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	
	@Test
	public void testInstance() {
		assertTrue("Instanciando ProfessorDAO", ProfessorDAO.getInstance() instanceof ProfessorDAO);
	}
	
	@Test
	public void testSingleton() {
		ProfessorDAO p = ProfessorDAO.getInstance();
		ProfessorDAO q = ProfessorDAO.getInstance();
		assertSame("Testando o Padrao Singleton", p, q);
	}
	
	
	
	@Test
	public void testIncluir() throws ClienteException, SQLException {
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		ProfessorDAO.getInstance().incluir(p);
		assertTrue("Testando Inclusao no Banco", ProfessorDAO.getInstance().inDB(p));
	}
	
	@Test
	public void testAlterar() throws ClienteException, SQLException {
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		Professor pn = new Professor("Nome para Alterar", "868.563.327-34", "098765", "(123)4567-8899", "email@Nome");
		ProfessorDAO.getInstance().alterar(p, pn);
		assertTrue("Testando Alteracao no Banco", ProfessorDAO.getInstance().inDB(pn));
	}
	
	@Test
	public void testExcluir() throws ClienteException, SQLException {
		Professor p = new Professor("Nome para Alterar", "868.563.327-34", "098765", "(123)4567-8899", "email@Nome");
		ProfessorDAO.getInstance().excluir(p);
		assertTrue("Testando Exclusao no Banco", !ProfessorDAO.getInstance().inDB(p));
	}
	
	
	
	@Test (expected= ClienteException.class)
	public void testIncluirExistente() throws ClienteException, SQLException {
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		ProfessorDAO.getInstance().incluir(p);
		ProfessorDAO.getInstance().incluir(p);
	}
	
	@Test (expected= ClienteException.class)
	public void testAlterarNaoExistente() throws ClienteException, SQLException {
		Professor p = new Professor("testAlterarNaoExistente", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		Professor pa = new Professor("Alterar", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		ProfessorDAO.getInstance().alterar(p, pa);
	}
	
	@Test (expected= ClienteException.class)
	public void testAlterarParaExistente() throws ClienteException, SQLException {
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		Professor pa = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		ProfessorDAO.getInstance().alterar(p, pa);
	}
	
	@Test (expected= ClienteException.class)
	public void testExcluirNaoExistente() throws ClienteException, SQLException {
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		ProfessorDAO.getInstance().excluir(p);
		ProfessorDAO.getInstance().excluir(p);
	}

}
