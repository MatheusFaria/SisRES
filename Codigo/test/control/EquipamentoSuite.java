/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.control;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.model.EquipamentoTest;
import test.persistence.EquipamentoDAOTest;

/**
 *
 * @author Parley
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ManterEquipamentoTest.class, EquipamentoTest.class, EquipamentoDAOTest.class})
public class EquipamentoSuite {

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
}
