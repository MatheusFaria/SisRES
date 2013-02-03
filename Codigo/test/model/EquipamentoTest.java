package test.model;

import static org.junit.Assert.assertTrue;
import model.Equipamento;

import org.junit.Test;

import exception.PatrimonioException;

public class EquipamentoTest {

	@Test
	public void testInstance() throws PatrimonioException {
		assertTrue(new Equipamento("codigo", "descricao") instanceof Equipamento);
	}
	
	@Test
	public void testNome() throws PatrimonioException {
		String codigo = "codigo";
		Equipamento eq = new Equipamento(codigo, "descricao");
		assertTrue("codigo diferente instanciado", codigo == eq.getCodigo());
	}
	
	@Test
	public void testDescricao() throws PatrimonioException {
		String descricao = "descricao";
		Equipamento eq = new Equipamento("codigo", descricao);
		assertTrue("Descricao diferente instanciada", descricao == eq.getDescricao());
	}
	
	
	
	
	@Test(expected = exception.PatrimonioException.class)
	public void testDescricaoVazia() throws PatrimonioException {
		new Equipamento("abc", "");
	}
	
	@Test(expected = exception.PatrimonioException.class)
	public void testCodigoVazio() throws PatrimonioException {
		new Equipamento("", "abc");
	}
	
	@Test(expected = exception.PatrimonioException.class)
	public void testCodigoNulo() throws PatrimonioException {
		new Equipamento(null, "abc");
	}
	
	@Test(expected = exception.PatrimonioException.class)
	public void testDescricaoNulo() throws PatrimonioException {
		new Equipamento("abc", null);
	}
	
}
