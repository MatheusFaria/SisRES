/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.model;

import exception.PatrimonioException;
import model.Equipamento;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Parley
 */
public class EquipamentoTest {

	public EquipamentoTest() {
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

	@Test()
	public void testEquipamento() throws PatrimonioException {
		Equipamento eq;	
		System.out.println("teste com duas Strings");
		eq = new Equipamento("abc", "abc");
		assertTrue(eq instanceof Equipamento);
	}
	
	@Test(expected = exception.PatrimonioException.class)
	public void testEquipamento2() throws PatrimonioException {
		Equipamento eq;
		System.out.println("teste com uma String e um campo vazio, respectivamente");
		eq = new Equipamento("abc", "");
		fail("Deveria lancar PatrimonioException");
	}
	
	@Test(expected = exception.PatrimonioException.class)
	public void testEquipamento3() throws PatrimonioException {
		Equipamento eq;
		System.out.println("teste com um campo vazio e uma String, respectivamente");
		eq = new Equipamento("", "abc");
		fail("Deveria lancar PatrimonioException");
	}
	
	@Test(expected = exception.PatrimonioException.class)
	public void testEquipamento4() throws PatrimonioException {
		Equipamento eq;
		System.out.println("teste com dois campos vazios");
		eq = new Equipamento("", "");
		fail("Deveria lancar PatrimonioException");
	}
	
	@Test(expected = RuntimeException.class)
	public void testEquipamento5() {
		Equipamento eq;
		System.out.println("teste com com tres campos no constutor, todos vazios");
		eq = new Equipamento("", "", "");
		fail("Deveria lancar RuntimeException");
	}
}
