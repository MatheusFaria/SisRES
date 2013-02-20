package test.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.Equipamento;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exception.PatrimonioException;

/**
 * Testa Patrimonio, de modo indireto por causa da Heranca de Equipamento
 * */

public class EquipamentoTest {

	Equipamento eq;
	
	@Before
	public void setUp() throws PatrimonioException{
		eq = new Equipamento("codigo", "descricao");
	}
	
	@After
	public void tearDown() throws PatrimonioException{
		eq = null;
	}
	
	@Test
	public void testInstance() throws PatrimonioException {
		assertTrue(eq instanceof Equipamento);
	}
	
	@Test
	public void testNome() throws PatrimonioException {
		assertTrue("codigo diferente instanciado", "codigo" == eq.getCodigo());
	}
	
	@Test
	public void testDescricao() throws PatrimonioException {
		assertTrue("Descricao diferente instanciada", "descricao" == eq.getDescricao());
	}
	
	@Test
	public void testEquals() throws PatrimonioException {
		Equipamento eq2 = new Equipamento("codigo", "descricao");
		assertTrue("Equipamentos deviam ser iguais", eq.equals(eq2));
	}
	
	@Test
	public void testEqualsCodigoDiferente() throws PatrimonioException {
		Equipamento eq2 = new Equipamento("codigo diferente", "descricao");
		assertFalse("Equipamentos deviam ser diferentes", eq.equals(eq2));
	}
	
	@Test
	public void testEqualsDescricaoDiferente() throws PatrimonioException {
		Equipamento eq2 = new Equipamento("codigo", "descricao diferente");
		assertFalse("Equipamentos deviam ser diferentes", eq.equals(eq2));
	}
	
	@Test(expected = PatrimonioException.class)
	public void testDescricaoVazia() throws PatrimonioException {
		new Equipamento("abc", "");
	}
	
	@Test(expected = PatrimonioException.class)
	public void testCodigoVazio() throws PatrimonioException {
		new Equipamento("", "abc");
	}
	
	@Test(expected = PatrimonioException.class)
	public void testCodigoNulo() throws PatrimonioException {
		new Equipamento(null, "abc");
	}
	
	@Test(expected = PatrimonioException.class)
	public void testDescricaoNulo() throws PatrimonioException {
		new Equipamento("abc", null);
	}

}
