package test.control;

import control.ManterEquipamento;
import exception.PatrimonioException;

import java.sql.SQLException;
import java.util.Vector;
import model.Equipamento;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ManterEquipamentoTest {

	ManterEquipamento instance;
	Vector<Equipamento> vector;
	static Equipamento e;

	public ManterEquipamentoTest() {
	}

	@BeforeClass
	public static void setUpClass() throws PatrimonioException {
		e = new Equipamento("codigo", "descricao");
	}

	@AfterClass
	public static void tearDownClass() {
		e = null;
	}

	@Before
	public void setUp() throws Exception {
		instance = ManterEquipamento.getInstance();
		vector = instance.getEquipamento_vet();
	}

	@After
	public void tearDown() {
	}
	
	@Test
	public void testGetEquipamento_vet() throws Exception {
		vector = instance.getEquipamento_vet();
		assertNotNull(vector);
	}
	
	@Test
	public void testGetInstance() {
		ManterEquipamento result = ManterEquipamento.getInstance();
		assertNotNull("Get Instance falhou",result);
	}
	
	@Test
	public void testSingleton(){
		ManterEquipamento me = ManterEquipamento.getInstance();
		ManterEquipamento me2 = ManterEquipamento.getInstance();
		assertSame("Instancias diferentes", me, me2);
		
	}

	@Test
	public void testIncluirVet() throws SQLException, PatrimonioException {
		instance.inserir("codigo","descricao");
		assertTrue("Teste de Inclusao no Equipamento Vet.", vector.lastElement().equals(e));
	}
	
	@Test
	public void testAlterarVet() throws SQLException, PatrimonioException {
		e = new Equipamento("codigo alterado", "descricao alterarda");
		instance.alterar("codigo alterado", "descricao alterarda", vector.lastElement());
		assertTrue("Teste de Inclusao no Equipamento Vet.", vector.lastElement().equals(e));
	}
	
	@Test
	public void testExcluirVet() throws SQLException, PatrimonioException {
		e = vector.lastElement();
		instance.excluir(e);
		assertFalse("Teste de Exclusao no Equipamento Vet.", vector.contains(e));
	}
	
}
