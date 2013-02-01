package test.control;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Vector;

import model.Professor;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import control.ManterEquipamento;
import control.ManterProfessor;
import exception.ClienteException;

public class ManterProfessorTest {

	private static Vector<Professor> vet;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		vet = ManterProfessor.getInstance().getProfessores_vet();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	
	@Test
	public void testInstance() {
		assertTrue("Teste de Intancia de ManterProfessor", ManterProfessor.getInstance() instanceof ManterProfessor);
	}
	
	@Test
	public void testSingleton() {
		ManterProfessor p = ManterProfessor.getInstance();
		ManterProfessor q = ManterProfessor.getInstance();
		assertSame("Teste Singleton de ManterProfessor", p, q);
	}	
	
	@Test
	public void testIncluirVet() throws ClienteException, SQLException {
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		ManterProfessor.getInstance().inserir("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		
		assertTrue("Teste de Inclusao no Professor Vet.", vet.lastElement().equals(p));
	}
	
	@Test
	public void testAlterarVet() throws ClienteException, SQLException {
		Professor p = new Professor("Nome para Alterar", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		ManterProfessor.getInstance().alterar("Nome para Alterar", "868.563.327-34", "123456", "1234-5678", "Nome@email", vet.lastElement());

		assertTrue("Teste de Alteracao no Professor Vet.", vet.lastElement().equals(p));
	}
	
	@Test
	public void testExcluirVet() throws ClienteException, SQLException {
		Professor p = vet.lastElement();
		ManterProfessor.getInstance().excluir(p);
		
		assertTrue("Teste de Exclusao no Professor Vet.", !vet.contains(p));
	}

}
