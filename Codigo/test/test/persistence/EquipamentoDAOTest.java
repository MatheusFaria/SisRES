/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.persistence;

import java.util.Vector;
import model.Equipamento;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import persistence.EquipamentoDAO;
import static org.junit.Assert.*;

/**
 *
 * @author Parley
 */
public class EquipamentoDAOTest {
	
	public EquipamentoDAOTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of getInstance method, of class EquipamentoDAO.
	 */
	@Test
	public void testGetInstance() {
		System.out.println("getInstance");
		EquipamentoDAO expResult = null;
		EquipamentoDAO result = EquipamentoDAO.getInstance();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of incluir method, of class EquipamentoDAO.
	 */
	@Test
	public void testIncluir() throws Exception {
		System.out.println("incluir");
		Equipamento equipamento = null;
		EquipamentoDAO instance = null;
		instance.incluir(equipamento);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of alterar method, of class EquipamentoDAO.
	 */
	@Test
	public void testAlterar() throws Exception {
		System.out.println("alterar");
		Equipamento old_equipamento = null;
		Equipamento new_equipamento = null;
		EquipamentoDAO instance = null;
		instance.alterar(old_equipamento, new_equipamento);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of excluir method, of class EquipamentoDAO.
	 */
	@Test
	public void testExcluir() throws Exception {
		System.out.println("excluir");
		Equipamento equipamento = null;
		EquipamentoDAO instance = null;
		instance.excluir(equipamento);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of buscar method, of class EquipamentoDAO.
	 */
	@Test
	public void testBuscar() {
		System.out.println("buscar");
		EquipamentoDAO instance = null;
		Equipamento expResult = null;
		Equipamento result = instance.buscar();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of buscarTodos method, of class EquipamentoDAO.
	 */
	@Test
	public void testBuscarTodos() throws Exception {
		System.out.println("buscarTodos");
		EquipamentoDAO instance = null;
		Vector expResult = null;
		Vector result = instance.buscarTodos();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
