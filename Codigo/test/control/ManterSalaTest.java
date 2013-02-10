package test.control;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import persistence.FactoryConnection;
import control.ManterSala;
import model.Sala;
import exception.PatrimonioException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;


public class ManterSalaTest {

	private static Sala sala;
	private static Sala sala_new;
	Vector<Sala> sala_vet;
	
	@Before
	public void setUp() throws SQLException, PatrimonioException{
		sala = new Sala("codigo_old", "descricao", "1");
		sala_new = new Sala("codigo", "descricao", "2");
	}
	
	@Before
	public void setUpIncluir() throws SQLException, PatrimonioException{
		sala = new Sala("codigo_old", "descricao", "1");
		sala_new = new Sala("codigo", "descricao", "2");
		
		this.executaNoBanco("INSERT INTO " +
				"sala (codigo, descricao, capacidade) VALUES (" +
				"\"" + sala.getCodigo() + "\", " +
				"\"" + sala.getDescricao() + "\", " +
				"\"" + sala.getCapacidade() + "\"); "
				);
	}
	
	@After
	public void tearDown() throws SQLException, PatrimonioException{
		this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + sala_new.getCodigo() + "\" and " +
				"sala.descricao = \"" + sala_new.getDescricao() +  "\" and " +
				"sala.capacidade = \"" + sala_new.getCapacidade() + "\";"
				);
		this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + sala.getCodigo() + "\" and " +
				"sala.descricao = \"" + sala.getDescricao() +  "\" and " +
				"sala.capacidade = \"" + sala.getCapacidade() + "\";"
				);				
		
	}
	
	@AfterClass
	public static void tearDownClass() throws SQLException, PatrimonioException{
		sala = null;
		sala_new = null;
	}
	
	@Test
	public void testGetInstance() {
		assertTrue("Verifica método getInstance().", ManterSala.getInstance() instanceof ManterSala);
	}
	
	@Test
	public void testSingleton() {
		ManterSala p = ManterSala.getInstance();
		ManterSala q = ManterSala.getInstance();
		assertSame("Testando o Padrao Singleton", p, q);
	}


	@Test
	public void testInserir() throws PatrimonioException, SQLException {
		setUp();
		ManterSala.getInstance().inserir("codigo", "descricao", "2");
		assertNotNull("Falha ao inserir", this.procurarNoVetor(sala_new));
		tearDown();
	}

	/*@Test
	public void testAlterar() throws PatrimonioException, SQLException {
		setUpIncluir();
		ManterSala.getInstance().alterar("codigo_old", "descricao", "1", sala_new);
		assertNotNull("Falha ao alterar", this.procurarNoVetor(sala_new));
		tearDown();
	}*/

	@Test
	public void testExcluir() throws SQLException, PatrimonioException {
		setUpIncluir();
		ManterSala.getInstance().excluir(sala);
		assertNull("Falha ao excluir", this.procurarNoVetor(sala));
		tearDown();
	}

	public Sala procurarNoVetor(Sala teste) throws PatrimonioException, SQLException {
		Vector<Sala> todos = ManterSala.getInstance().getSalas_vet();
		Iterator<Sala> i = todos.iterator();
		while(i.hasNext()){
			Sala e = i.next();
			if(e.equals(teste))
				return e;			
		}
		return null;
	}
	
	private void executaNoBanco(String msg) throws SQLException{
		Connection con =  FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(msg);
		pst.executeUpdate();
		pst.close();
		con.close();
	}
	
}
