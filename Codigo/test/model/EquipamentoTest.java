/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import model.Equipamento;

import org.junit.Test;

import exception.PatrimonioException;

/**
 *
 * @author Parley
 */
public class EquipamentoTest {

	@Test()
	public void testEquipamento() throws PatrimonioException {
		assertTrue(new Equipamento("codigo", "descricao") instanceof Equipamento);
	}
	
	@Test()
	public void testGetNome() throws PatrimonioException {
		String codigo = "codigo";
		Equipamento eq;	
		eq = new Equipamento(codigo, "descricao");
		assertTrue("codigo diferente instanciado", codigo == eq.getCodigo());
	}
	
	@Test()
	public void testGetDescricao() throws PatrimonioException {
		String descricao = "descricao";
		Equipamento eq;	
		eq = new Equipamento("codigo", descricao);
		assertTrue("Descricao diferente instanciada", descricao == eq.getDescricao());
	}
	
	@Test(expected = exception.PatrimonioException.class)
	public void testEquipamentoDescricaoVazia() throws PatrimonioException {
		new Equipamento("abc", "");
		fail("Deveria lancar PatrimonioException");
	}
	
	@Test(expected = exception.PatrimonioException.class)
	public void testEquipamentoCodigoVazio() throws PatrimonioException {
		new Equipamento("", "abc");
		fail("Deveria lancar PatrimonioException");
	}
	
	@Test(expected = exception.PatrimonioException.class)
	public void testEquipamentoCamposNulos() throws PatrimonioException {
		new Equipamento("", "");
		fail("Deveria lancar PatrimonioException");
	}
	
}
