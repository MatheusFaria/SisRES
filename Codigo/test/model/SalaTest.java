package test.model;

import static org.junit.Assert.*;
import model.Sala;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import exception.PatrimonioException;

public class SalaTest {
	
	Sala sala;
	
	@Before
	public void setUp() throws PatrimonioException{
		sala = new Sala("codigo", "descricao", "1");
	}
	
	@After
	public void tearDown() throws PatrimonioException{
		sala = null;
	}
	
	@Test
    public void testInstance() throws PatrimonioException {
		assertTrue(new Sala("codigo", "descricao","1") instanceof Sala);
	}
	
	@Test
	public void testCodigo() throws PatrimonioException {
		setUp();
		assertEquals("codigo diferente instanciado", "codigo", sala.getCodigo());
		tearDown();
	}
	
	@Test
	public void testDescricao() throws PatrimonioException {
		setUp();
		assertEquals("Descricao diferente instanciada", "descricao", sala.getDescricao());
		tearDown();
	}	
	
	@Test
	public void testCapacidade() throws PatrimonioException {
		setUp();
		assertEquals("Capacidade diferente instanciada", "1", sala.getCapacidade());
		tearDown();
	}
	
	@Test(expected = exception.PatrimonioException.class)
	public void testDescricaoVazia() throws PatrimonioException {
		new Sala("codigo", "", "1");
	}
	
	@Test(expected = exception.PatrimonioException.class)
	public void testCapacidadeVazia() throws PatrimonioException {
		new Sala("codigo", "descricao", "");
	}
	
	@Test(expected = exception.PatrimonioException.class)
	public void testCodigoVazio() throws PatrimonioException {
		new Sala("", "descricao","1");
	}
	
	@Test(expected = exception.PatrimonioException.class)
	public void testCodigoNulo() throws PatrimonioException {
		new Sala(null, "descricao", "1");
	}
	
	@Test(expected = exception.PatrimonioException.class)
	public void testDescricaoNulo() throws PatrimonioException {
		new Sala("codigo", null,"1");
	}
	
	@Test(expected = exception.PatrimonioException.class)
	public void testCapacidadeNulo() throws PatrimonioException {
		new Sala("codigo", "descricao", null);
	}
}
