/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.control;

import control.ManterEquipamento;
import exception.PatrimonioException;
import java.util.Vector;
import model.Equipamento;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Parley
 */
public class ManterEquipamentoTest {

	ManterEquipamento instance;
	Vector<Equipamento> vector;

	public ManterEquipamentoTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() throws Exception {
		instance = ManterEquipamento.getInstance();
		testGetEquipamento_vet();
	}

	@After
	public void tearDown() {
		instance = null;
	}

	/**
	 * Test of getInstance method, of class ManterEquipamento.
	 */
	@Test
	public void testGetInstance() {
		System.out.println("teste getInstance");
		ManterEquipamento result = ManterEquipamento.getInstance();
		assertNotNull(result);
	}

	/**
	 * Test of getEquipamento_vet method, of class ManterEquipamento.
	 */
	@Ignore
	public void testGetEquipamento_vet() throws Exception {
		//System.out.println("teste getEquipamento_vet");
		vector = instance.getEquipamento_vet();
		assertNotNull(vector);
	}

	//@Ignore
	@Test(expected = PatrimonioException.class)
	public void testInserir() throws Exception {
		System.out.println("inserir com campos nulos");
		String codigo = "";
		String descricao = "";
		instance.inserir(codigo, descricao);

	}

	//@Ignore
	@Test(expected = PatrimonioException.class)
	public void testInserir2() throws Exception {
		System.out.println("inserir com codigo preenchido e descricao nula");
		String codigo = "abc";
		String descricao = "";
		instance.inserir(codigo, descricao);
	}

	//@Ignore
	@Test(expected = PatrimonioException.class)
	public void testInserir3() throws Exception {
		System.out.println("inserir com codigo nulo e descricao preenchida");
		String codigo = "";
		String descricao = "sala";
		instance.inserir(codigo, descricao);
	}

	//verificar porque esse equals não esta dizendo retornando TRUE.
	@Test
	public void testInserir4() throws Exception {
		System.out.println("inserir com codigo e descricao preenchidos");
		String codigo = "codigo";
		String descricao = "descricao";
		instance.inserir(codigo, descricao);
		Equipamento equipamento = new Equipamento(codigo, descricao);
		testGetEquipamento_vet();
		System.out.println(equipamento);
		System.out.println(vector.get(2));
		System.out.println(vector.get(2).equals(equipamento));
		System.out.println(vector.contains(equipamento));
		testExcluir2();
	}

	
	@Test(expected = PatrimonioException.class)
	public void testAlterar() throws Exception {
		System.out.println("alterar com campos nulos");
		String codigo = "";
		String descricao = "";
		Equipamento equipamento = null;
		instance.alterar(codigo, descricao, equipamento);
	}

	@Test(expected = PatrimonioException.class)
	public void testAlterar2() throws Exception {
		System.out.println("alterar com codigo nulo, descricao nula e equipamento nao nulo");
		String codigo = "";
		String descricao = "";
		Equipamento equipamento = new Equipamento("teste", "teste Alterar");
		instance.alterar(codigo, descricao, equipamento);
	}

	@Test(expected = PatrimonioException.class)
	public void testAlterar3() throws Exception {
		System.out.println("alterar com codigo nao nulo, descricao nula e equipamento nao nulo");
		String codigo = "code";
		String descricao = "";
		Equipamento equipamento = new Equipamento("teste", "teste Alterar");
		instance.alterar(codigo, descricao, equipamento);
	}

	@Test(expected = PatrimonioException.class)
	public void testAlterar4() throws Exception {
		System.out.println("alterar com codigo nao nulo, descricao nula e equipamento nao nulo");
		String codigo = "";
		String descricao = "description";
		Equipamento equipamento = new Equipamento("teste", "teste Alterar");
		instance.alterar(codigo, descricao, equipamento);
		testExcluir2();
	}
//editar esse teste
	@Test
	public void testAlterar5() {
		try {
			System.out.println("alterar com todos os campos preenchidos");
			String codigo = "code";
			String descricao = "description";
			Equipamento equipamento = new Equipamento("teste", "teste Alterar");
			instance.alterar(codigo, descricao, equipamento);
			fail("nao devia alterar o que nao existe");
		} catch (Exception e) {
			System.out.println("Excecao - " + e.getMessage());
		}
	}

	@Test(expected = PatrimonioException.class)
	public void testExcluir() throws Exception {
		System.out.println("excluir com equipamento nulo");
		Equipamento equipamento = null;
		instance.excluir(equipamento);
	}

	@Ignore
	//@Test
	public void testExcluir2() throws Exception {
		System.out.println("excluir com equipamento do testeInserir4");
		Equipamento equipamento = new Equipamento("codigo", "descricao");
		instance.excluir(equipamento);
		assertFalse(instance.getEquipamento_vet().contains(equipamento));
	}
}
