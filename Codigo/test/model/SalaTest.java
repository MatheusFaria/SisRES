package test.model;

import static org.junit.Assert.*;
import model.Sala;

import org.junit.Test;

import exception.PatrimonioException;

public class SalaTest {

  @Test
  public void testInstance() throws PatrimonioException {
		assertTrue(new Sala("codigo", "descricao","capacidade") instanceof Sala);
	}
	
	@Test
	public void testCodigo() throws PatrimonioException {
		Sala eq = new Sala("codigo", "descricao","capacidade");
		assertEquals("codigo diferente instanciado", "codigo", eq.getCodigo());
	}
	
	@Test
	public void testDescricao() throws PatrimonioException {
		Sala eq = new Sala("codigo", "descricao","capacidade");
		assertEquals("Descricao diferente instanciada", "descricao", eq.getDescricao());
	}	
	
	@Test(expected = exception.PatrimonioException.class)
	public void testDescricaoVazia() throws PatrimonioException {
		new Sala("codigo", "", "capacidade");
	}
	
	@Test(expected = exception.PatrimonioException.class)
	public void testCapacidadeVazia() throws PatrimonioException {
		new Sala("codigo", "descricao", "");
	}
	
	@Test(expected = exception.PatrimonioException.class)
	public void testCodigoVazio() throws PatrimonioException {
		new Sala("", "descricao","capacidade");
	}
	
	@Test(expected = exception.PatrimonioException.class)
	public void testCodigoNulo() throws PatrimonioException {
		new Sala(null, "descricao", "capacidade");
	}
	
	@Test(expected = exception.PatrimonioException.class)
	public void testDescricaoNulo() throws PatrimonioException {
		new Sala("codigo", null,"capacidade");
	}
	
	@Test(expected = exception.PatrimonioException.class)
	public void testCapacidadeNulo() throws PatrimonioException {
		new Sala("codigo", "descricao", null);
	}
}
