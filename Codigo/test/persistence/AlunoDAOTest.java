package test.persistence;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Aluno;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import exception.ClienteException;

import persistence.AlunoDAO;
import persistence.FactoryConnection;

public class AlunoDAOTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	
	@Test
	public void testInstance() {
		assertTrue("Instanciando AlunoDAO", AlunoDAO.getInstance() instanceof AlunoDAO);
	}
	
	@Test
	public void testSingleton() {
		AlunoDAO p = AlunoDAO.getInstance();
		AlunoDAO q = AlunoDAO.getInstance();
		assertSame("Testando o Padrao Singleton", p, q);
	}
	

	@Test
	public void testIncluir() throws ClienteException, SQLException {
		boolean resultado = false;
		Aluno aluno = new Aluno("Incluindo", "040.757.021-70", "098765", "9999-9999", "aluno@email");
		AlunoDAO.getInstance().incluir(aluno);
		
		resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
		"aluno.nome = \"" + aluno.getNome() + "\" and " +
		"aluno.cpf = \"" + aluno.getCpf() + "\" and " +
		"aluno.telefone = \"" + aluno.getTelefone() + "\" and " +
		"aluno.email = \"" + aluno.getEmail() + "\" and " +
		"aluno.matricula = \"" + aluno.getMatricula() + "\";");
		
		if(resultado){
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno.getNome() + "\" and " +
					"aluno.cpf = \"" + aluno.getCpf() + "\" and " +
					"aluno.telefone = \"" + aluno.getTelefone() + "\" and " +
					"aluno.email = \"" + aluno.getEmail() + "\" and " +
					"aluno.matricula = \"" + aluno.getMatricula() + "\";");
		}
		assertTrue("Teste de Inclusão.", resultado);
	}
	
	@Test (expected= ClienteException.class)
	public void testIncluirNulo() throws ClienteException, SQLException {
		AlunoDAO.getInstance().incluir(null);
	}
	
	@Test (expected= ClienteException.class)
	public void testIncluirComMesmoCpf() throws ClienteException, SQLException {
		boolean resultado = true;
		Aluno aluno = new Aluno("Incluindo", "040.757.021-70", "098765", "1111-1111", "aluno@email");
		Aluno aluno2 = new Aluno("Incluindo CPF Igual", "040.747.021-70", "987654", "2222-2222", "aluno2@email");
		AlunoDAO.getInstance().incluir(aluno);
		try{
			AlunoDAO.getInstance().incluir(aluno2);
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno2.getNome() + "\" and " +
					"aluno.cpf = \"" + aluno2.getCpf() + "\" and " +
					"aluno.telefone = \"" + aluno2.getTelefone() + "\" and " +
					"aluno.email = \"" + aluno2.getEmail() + "\" and " +
					"aluno.matricula = \"" + aluno2.getMatricula() + "\";");
			
		} finally {
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno.getNome() + "\" and " +
					"aluno.cpf = \"" + aluno.getCpf() + "\" and " +
					"aluno.telefone = \"" + aluno.getTelefone() + "\" and " +
					"aluno.email = \"" + aluno.getEmail() + "\" and " +
					"aluno.matricula = \"" + aluno.getMatricula() + "\";");
			resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
					"aluno.nome = \"" + aluno2.getNome() + "\" and " +
					"aluno.cpf = \"" + aluno2.getCpf() + "\" and " +
					"aluno.telefone = \"" + aluno2.getTelefone() + "\" and " +
					"aluno.email = \"" + aluno2.getEmail() + "\" and " +
					"aluno.matricula = \"" + aluno2.getMatricula() + "\";");
		}
		
		assertFalse("Teste de Inclusão.", resultado);
	}
	@Test (expected= ClienteException.class)
	public void testIncluirComMesmaMatricula() throws ClienteException, SQLException {
		boolean resultado = true;
		Aluno aluno = new Aluno("Incluindo", "040.757.021-70", "111111", "1111-1111", "aluno@email");
		Aluno aluno2 = new Aluno("Incluindo Matricula Igual", "490.491.781-20", "111111", "2222-2222", "aluno2@email");
		AlunoDAO.getInstance().incluir(aluno);
		try{
			AlunoDAO.getInstance().incluir(aluno2);
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno2.getNome() + "\" and " +
					"aluno.cpf = \"" + aluno2.getCpf() + "\" and " +
					"aluno.telefone = \"" + aluno2.getTelefone() + "\" and " +
					"aluno.email = \"" + aluno2.getEmail() + "\" and " +
					"aluno.matricula = \"" + aluno2.getMatricula() + "\";");
			
		} finally {
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno.getNome() + "\" and " +
					"aluno.cpf = \"" + aluno.getCpf() + "\" and " +
					"aluno.telefone = \"" + aluno.getTelefone() + "\" and " +
					"aluno.email = \"" + aluno.getEmail() + "\" and " +
					"aluno.matricula = \"" + aluno.getMatricula() + "\";");
			resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
					"aluno.nome = \"" + aluno2.getNome() + "\" and " +
					"aluno.cpf = \"" + aluno2.getCpf() + "\" and " +
					"aluno.telefone = \"" + aluno2.getTelefone() + "\" and " +
					"aluno.email = \"" + aluno2.getEmail() + "\" and " +
					"aluno.matricula = \"" + aluno2.getMatricula() + "\";");
		}
		
		assertFalse("Teste de Inclusão.", resultado);
	}
	@Test (expected= ClienteException.class)
	public void testIncluirJaExistente() throws ClienteException, SQLException {
		boolean resultado = true;
		Aluno aluno = new Aluno("Incluindo", "040.757.021-70", "58801", "3333-3333", "aluno@email");
		Aluno aluno2 = new Aluno("Incluindo", "040.757.021-70", "58801", "3333-3333", "aluno@email");
		AlunoDAO.getInstance().incluir(aluno);
		try{
			AlunoDAO.getInstance().incluir(aluno2);
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno2.getNome() + "\" and " +
					"aluno.cpf = \"" + aluno2.getCpf() + "\" and " +
					"aluno.telefone = \"" + aluno2.getTelefone() + "\" and " +
					"aluno.email = \"" + aluno2.getEmail() + "\" and " +
					"aluno.matricula = \"" + aluno2.getMatricula() + "\";");
			
		} finally {
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno.getNome() + "\" and " +
					"aluno.cpf = \"" + aluno.getCpf() + "\" and " +
					"aluno.telefone = \"" + aluno.getTelefone() + "\" and " +
					"aluno.email = \"" + aluno.getEmail() + "\" and " +
					"aluno.matricula = \"" + aluno.getMatricula() + "\";");
			resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
					"aluno.nome = \"" + aluno2.getNome() + "\" and " +
					"aluno.cpf = \"" + aluno2.getCpf() + "\" and " +
					"aluno.telefone = \"" + aluno2.getTelefone() + "\" and " +
					"aluno.email = \"" + aluno2.getEmail() + "\" and " +
					"aluno.matricula = \"" + aluno2.getMatricula() + "\";");
		}
		
		assertFalse("Teste de Inclusão.", resultado);
	}
	
	
	
	@Test
	public void testAlterar() throws ClienteException, SQLException {
		boolean resultado = false;
		Aluno a = new Aluno("Incluindo", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		Aluno an = new Aluno("Alterando", "387.807.647-97", "098765", "(123)4567-8899", "email@Nome");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getNome() + "\", " +
						"\"" + a.getCpf()+ "\", " +
						"\"" + a.getTelefone() + "\", " +
						"\"" + a.getEmail() + "\", " +
						"\"" + a.getMatricula() + "\"); ");
		
		AlunoDAO.getInstance().alterar(a, an);
		
		resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + an.getNome() + "\" and " +
				"aluno.cpf = \"" + an.getCpf() + "\" and " +
				"aluno.telefone = \"" + an.getTelefone() + "\" and " +
				"aluno.email = \"" + an.getEmail() + "\" and " +
				"aluno.matricula = \"" + an.getMatricula() + "\";");
		boolean resultado2 =  this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + a.getNome() + "\" and " +
				"aluno.cpf = \"" + a.getCpf() + "\" and " +
				"aluno.telefone = \"" + a.getTelefone() + "\" and " +
				"aluno.email = \"" + a.getEmail() + "\" and " +
				"aluno.matricula = \"" + a.getMatricula() + "\";");
		if(resultado)
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + an.getNome() + "\" and " +
					"aluno.cpf = \"" + an.getCpf() + "\" and " +
					"aluno.telefone = \"" + an.getTelefone() + "\" and " +
					"aluno.email = \"" + an.getEmail() + "\" and " +
					"aluno.matricula = \"" + an.getMatricula() + "\";");
		if(resultado2)
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getNome() + "\" and " +
					"aluno.cpf = \"" + a.getCpf() + "\" and " +
					"aluno.telefone = \"" + a.getTelefone() + "\" and " +
					"aluno.email = \"" + a.getEmail() + "\" and " +
					"aluno.matricula = \"" + a.getMatricula() + "\";");
		
		assertTrue("Teste de Alteração.", resultado == true && resultado2 == false);
	}
	
	@Test (expected= ClienteException.class)
	public void testAlterarPrimeiroArgNulo() throws ClienteException, SQLException {
		Aluno an = new Aluno("Alterando", "00.757.021-70", "123456", "(999)9999-9999", "aluno@email");
		AlunoDAO.getInstance().alterar(null, an);
	}
	
	@Test (expected= ClienteException.class)
	public void testAlterarSegundoArgNulo() throws ClienteException, SQLException {
		Aluno an = new Aluno("Alterando", "00.757.021-70", "123456", "(999)9999-9999", "aluno@email");
		AlunoDAO.getInstance().alterar(an, null);
	}
	@Test (expected= ClienteException.class)
	public void testAlterarNaoExistente() throws ClienteException, SQLException {
		boolean resultado = true;
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "123456", "1111-1111", "aluno@email");
		Aluno an = new Aluno("Alterando", "490.491.781-20", "098765", "(999)9999-9999", "email@aluno");
		
		try{
			AlunoDAO.getInstance().alterar(a, an);
		} finally {
			resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + an.getNome() + "\" and " +
				"aluno.cpf = \"" + an.getCpf() + "\" and " +
				"aluno.telefone = \"" + an.getTelefone() + "\" and " +
				"aluno.email = \"" + an.getEmail() + "\" and " +
				"aluno.matricula = \"" + an.getMatricula() + "\";");
			if(resultado)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + an.getNome() + "\" and " +
					"aluno.cpf = \"" + an.getCpf() + "\" and " +
					"aluno.telefone = \"" + an.getTelefone() + "\" and " +
					"aluno.email = \"" + an.getEmail() + "\" and " +
					"aluno.matricula = \"" + an.getMatricula() + "\";");
		}
		assertFalse("Teste de Alteração.", resultado);
	}
	@Test (expected= ClienteException.class)
	public void testAlterarParaJaExistente() throws ClienteException, SQLException {
		boolean resultado = true;
		boolean resultado2 = false;
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "058801", "9999-9999", "aluno@email");
		Aluno an = new Aluno("Incluindo", "040.757.021-70", "058801", "9999-9999", "aluno@email");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getNome() + "\", " +
						"\"" + a.getCpf()+ "\", " +
						"\"" + a.getTelefone() + "\", " +
						"\"" + a.getEmail() + "\", " +
						"\"" + a.getMatricula() + "\"); ");
		
		try{
			AlunoDAO.getInstance().alterar(a, an);
		} finally {
			resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + an.getNome() + "\" and " +
				"aluno.cpf = \"" + an.getCpf() + "\" and " +
				"aluno.telefone = \"" + an.getTelefone() + "\" and " +
				"aluno.email = \"" + an.getEmail() + "\" and " +
				"aluno.matricula = \"" + an.getMatricula() + "\";");
			resultado2 =  this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + a.getNome() + "\" and " +
				"aluno.cpf = \"" + a.getCpf() + "\" and " +
				"aluno.telefone = \"" + a.getTelefone() + "\" and " +
				"aluno.email = \"" + a.getEmail() + "\" and " +
				"aluno.matricula = \"" + a.getMatricula() + "\";");
			if(resultado)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + an.getNome() + "\" and " +
					"aluno.cpf = \"" + an.getCpf() + "\" and " +
					"aluno.telefone = \"" + an.getTelefone() + "\" and " +
					"aluno.email = \"" + an.getEmail() + "\" and " +
					"aluno.matricula = \"" + an.getMatricula() + "\";");
			if(resultado2)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getNome() + "\" and " +
					"aluno.cpf = \"" + a.getCpf() + "\" and " +
					"aluno.telefone = \"" + a.getTelefone() + "\" and " +
					"aluno.email = \"" + a.getEmail() + "\" and " +
					"aluno.matricula = \"" + a.getMatricula() + "\";");
		}
		assertTrue("Teste de Alteração.", resultado == false && resultado2 == true);
	}
	@Test (expected= ClienteException.class)
	public void testAlterarParaCpfExistente() throws ClienteException, SQLException {
		boolean resultado = true;
		boolean resultado2 = false;
		boolean resultado3 = false;
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		Aluno an = new Aluno("Incluindo Segundo", "490.491.781-20", "1234", "4444-4444", "novoAluno@email");
		Aluno ann = new Aluno("Incluindo Segundo", "040.757.021-70", "1234", "4444-4444", "novoAluno@email");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getNome() + "\", " +
						"\"" + a.getCpf()+ "\", " +
						"\"" + a.getTelefone() + "\", " +
						"\"" + a.getEmail() + "\", " +
						"\"" + a.getMatricula() + "\"); ");
		this.executaNoBanco("INSERT INTO " +
				"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
				"\"" + an.getNome() + "\", " +
				"\"" + an.getCpf()+ "\", " +
				"\"" + an.getTelefone() + "\", " +
				"\"" + an.getEmail() + "\", " +
				"\"" + an.getMatricula() + "\"); ");
		
		try{
			AlunoDAO.getInstance().alterar(an, ann);
		} finally {
			resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + an.getNome() + "\" and " +
				"aluno.cpf = \"" + an.getCpf() + "\" and " +
				"aluno.telefone = \"" + an.getTelefone() + "\" and " +
				"aluno.email = \"" + an.getEmail() + "\" and " +
				"aluno.matricula = \"" + an.getMatricula() + "\";");
			resultado2 =  this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + a.getNome() + "\" and " +
				"aluno.cpf = \"" + a.getCpf() + "\" and " +
				"aluno.telefone = \"" + a.getTelefone() + "\" and " +
				"aluno.email = \"" + a.getEmail() + "\" and " +
				"aluno.matricula = \"" + a.getMatricula() + "\";");
			resultado3 =  this.estaNoBanco("SELECT * FROM aluno WHERE " +
					"aluno.nome = \"" + ann.getNome() + "\" and " +
					"aluno.cpf = \"" + ann.getCpf() + "\" and " +
					"aluno.telefone = \"" + ann.getTelefone() + "\" and " +
					"aluno.email = \"" + ann.getEmail() + "\" and " +
					"aluno.matricula = \"" + ann.getMatricula() + "\";");
			if(resultado)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + an.getNome() + "\" and " +
					"aluno.cpf = \"" + an.getCpf() + "\" and " +
					"aluno.telefone = \"" + an.getTelefone() + "\" and " +
					"aluno.email = \"" + an.getEmail() + "\" and " +
					"aluno.matricula = \"" + an.getMatricula() + "\";");
			if(resultado2)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getNome() + "\" and " +
					"aluno.cpf = \"" + a.getCpf() + "\" and " +
					"aluno.telefone = \"" + a.getTelefone() + "\" and " +
					"aluno.email = \"" + a.getEmail() + "\" and " +
					"aluno.matricula = \"" + a.getMatricula() + "\";");
			if(resultado3)
				this.executaNoBanco("DELETE FROM professor WHERE " +
					"aluno.nome = \"" + ann.getNome() + "\" and " +
					"aluno.cpf = \"" + ann.getCpf() + "\" and " +
					"aluno.telefone = \"" + ann.getTelefone() + "\" and " +
					"aluno.email = \"" + ann.getEmail() + "\" and " +
					"aluno.matricula = \"" + ann.getMatricula() + "\";");
		}
		assertTrue("Teste de Alteração.", resultado == true && resultado2 == true && resultado3 == false);
	}
	@Test (expected= ClienteException.class)
	public void testAlterarParaMatriculaExistente() throws ClienteException, SQLException {
		boolean resultado = true;
		boolean resultado2 = false;
		boolean resultado3 = false;
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "123456", "9999-99999", "aluno@email");
		Aluno an = new Aluno("Incluindo Segundo", "490.491.781-20", "0987", "5555-5555", "alunoNovo@email");
		Aluno ann = new Aluno("Incluindo Segundo", "490.491.781-20", "123456", "5555-5555", "alunoNovo@email");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getNome() + "\", " +
						"\"" + a.getCpf()+ "\", " +
						"\"" + a.getTelefone() + "\", " +
						"\"" + a.getEmail() + "\", " +
						"\"" + a.getMatricula() + "\"); ");
		this.executaNoBanco("INSERT INTO " +
				"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
				"\"" + an.getNome() + "\", " +
				"\"" + an.getCpf()+ "\", " +
				"\"" + an.getTelefone() + "\", " +
				"\"" + an.getEmail() + "\", " +
				"\"" + an.getMatricula() + "\"); ");
		
		try{
			AlunoDAO.getInstance().alterar(an, ann);
		} finally {
			resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + an.getNome() + "\" and " +
				"aluno.cpf = \"" + an.getCpf() + "\" and " +
				"aluno.telefone = \"" + an.getTelefone() + "\" and " +
				"aluno.email = \"" + an.getEmail() + "\" and " +
				"aluno.matricula = \"" + an.getMatricula() + "\";");
			resultado2 =  this.estaNoBanco("SELECT * FROM professor WHERE " +
				"aluno.nome = \"" + a.getNome() + "\" and " +
				"aluno.cpf = \"" + a.getCpf() + "\" and " +
				"aluno.telefone = \"" + a.getTelefone() + "\" and " +
				"aluno.email = \"" + a.getEmail() + "\" and " +
				"aluno.matricula = \"" + a.getMatricula() + "\";");
			resultado3 =  this.estaNoBanco("SELECT * FROM aluno WHERE " +
					"aluno.nome = \"" + ann.getNome() + "\" and " +
					"aluno.cpf = \"" + ann.getCpf() + "\" and " +
					"aluno.telefone = \"" + ann.getTelefone() + "\" and " +
					"aluno.email = \"" + ann.getEmail() + "\" and " +
					"aluno.matricula = \"" + ann.getMatricula() + "\";");
			if(resultado)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
						"aluno.nome = \"" + an.getNome() + "\" and " +
						"aluno.cpf = \"" + an.getCpf() + "\" and " +
						"aluno.telefone = \"" + an.getTelefone() + "\" and " +
						"aluno.email = \"" + an.getEmail() + "\" and " +
						"aluno.matricula = \"" + an.getMatricula() + "\";");
			if(resultado2)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getNome() + "\" and " +
					"aluno.cpf = \"" + a.getCpf() + "\" and " +
					"aluno.telefone = \"" + a.getTelefone() + "\" and " +
					"aluno.email = \"" + a.getEmail() + "\" and " +
					"aluno.matricula = \"" + a.getMatricula() + "\";");
			if(resultado3)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + ann.getNome() + "\" and " +
					"aluno.cpf = \"" + ann.getCpf() + "\" and " +
					"aluno.telefone = \"" + ann.getTelefone() + "\" and " +
					"aluno.email = \"" + ann.getEmail() + "\" and " +
					"aluno.matricula = \"" + ann.getMatricula() + "\";");
		}
		assertTrue("Teste de Alteração.", resultado == true && resultado2 == true && resultado3 == false);
	}
	@Ignore // (expected= ClienteException.class)
	public void testAlterarEnvolvidoEmReserva() throws ClienteException, SQLException {
		fail();
	}
	
	
	
	@Test
	public void testExcluir() throws ClienteException, SQLException {
		boolean resultado = true;
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "058801", "9999-9999", "aluno@email");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getNome() + "\", " +
						"\"" + a.getCpf()+ "\", " +
						"\"" + a.getTelefone() + "\", " +
						"\"" + a.getEmail() + "\", " +
						"\"" + a.getMatricula() + "\"); ");
		
		AlunoDAO.getInstance().excluir(a);
		

		resultado =  this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + a.getNome() + "\" and " +
				"aluno.cpf = \"" + a.getCpf() + "\" and " +
				"aluno.telefone = \"" + a.getTelefone() + "\" and " +
				"aluno.email = \"" + a.getEmail() + "\" and " +
				"aluno.matricula = \"" + a.getMatricula() + "\";");
		if(resultado)
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getNome() + "\" and " +
					"aluno.cpf = \"" + a.getCpf() + "\" and " +
					"aluno.telefone = \"" + a.getTelefone() + "\" and " +
					"aluno.email = \"" + a.getEmail() + "\" and " +
					"aluno.matricula = \"" + a.getMatricula() + "\";");
		
		assertFalse("Teste de Alteração.", resultado);
	}
	@Test (expected= ClienteException.class)
	public void testExcluirNulo() throws ClienteException, SQLException {
		AlunoDAO.getInstance().excluir(null);
	}
	@Ignore // (expected= ClienteException.class)
	public void testExcluirEnvolvidoEmReserva() throws ClienteException, SQLException {
		fail();
	}
	@Test (expected= ClienteException.class)
	public void testExcluirNaoExistente() throws ClienteException, SQLException {
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		AlunoDAO.getInstance().excluir(a);
	}
	
	
	
	@Test
	public void testBuscarNome() throws ClienteException, SQLException {
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getNome() + "\", " +
						"\"" + a.getCpf()+ "\", " +
						"\"" + a.getTelefone() + "\", " +
						"\"" + a.getEmail() + "\", " +
						"\"" + a.getMatricula() + "\"); ");
		
		Vector<Aluno> vet = AlunoDAO.getInstance().buscarNome("Incluindo");

		this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getNome() + "\" and " +
					"aluno.cpf = \"" + a.getCpf() + "\" and " +
					"aluno.telefone = \"" + a.getTelefone() + "\" and " +
					"aluno.email = \"" + a.getEmail() + "\" and " +
					"aluno.matricula = \"" + a.getMatricula() + "\";");
		
		assertTrue("Teste de Alteração.", vet.size() > 0);
	}
	@Test
	public void testBuscarCpf() throws ClienteException, SQLException {
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getNome() + "\", " +
						"\"" + a.getCpf()+ "\", " +
						"\"" + a.getTelefone() + "\", " +
						"\"" + a.getEmail() + "\", " +
						"\"" + a.getMatricula() + "\"); ");
		
		Vector<Aluno> vet = AlunoDAO.getInstance().buscarCpf("040.757.021-70");

		this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getNome() + "\" and " +
					"aluno.cpf = \"" + a.getCpf() + "\" and " +
					"aluno.telefone = \"" + a.getTelefone() + "\" and " +
					"aluno.email = \"" + a.getEmail() + "\" and " +
					"aluno.matricula = \"" + a.getMatricula() + "\";");
		
		assertTrue("Teste de Alteração.", vet.size() > 0);
	}
	@Test
	public void testBuscarMatricula() throws ClienteException, SQLException {
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getNome() + "\", " +
						"\"" + a.getCpf()+ "\", " +
						"\"" + a.getTelefone() + "\", " +
						"\"" + a.getEmail() + "\", " +
						"\"" + a.getMatricula() + "\"); ");
		
		Vector<Aluno> vet = AlunoDAO.getInstance().buscarMatricula("123456");

		this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getNome() + "\" and " +
					"aluno.cpf = \"" + a.getCpf() + "\" and " +
					"aluno.telefone = \"" + a.getTelefone() + "\" and " +
					"aluno.email = \"" + a.getEmail() + "\" and " +
					"aluno.matricula = \"" + a.getMatricula() + "\";");
		
		assertTrue("Teste de Alteração.", vet.size() > 0);
	}
	@Test
	public void testBuscarTelefone() throws ClienteException, SQLException {
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		this.executaNoBanco("INSERT INTO " +
				"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
				"\"" + a.getNome() + "\", " +
				"\"" + a.getCpf()+ "\", " +
				"\"" + a.getTelefone() + "\", " +
				"\"" + a.getEmail() + "\", " +
				"\"" + a.getMatricula() + "\"); ");
		
		Vector<Aluno> vet = AlunoDAO.getInstance().buscarTelefone("9999-9999");

		this.executaNoBanco("DELETE FROM aluno WHERE " +
				"aluno.nome = \"" + a.getNome() + "\" and " +
				"aluno.cpf = \"" + a.getCpf() + "\" and " +
				"aluno.telefone = \"" + a.getTelefone() + "\" and " +
				"aluno.email = \"" + a.getEmail() + "\" and " +
				"aluno.matricula = \"" + a.getMatricula() + "\";");
		
		assertTrue("Teste de Alteração.", vet.size() > 0);
	}
	@Test
	public void testBuscarEmail() throws ClienteException, SQLException {
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		this.executaNoBanco("INSERT INTO " +
				"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
				"\"" + a.getNome() + "\", " +
				"\"" + a.getCpf()+ "\", " +
				"\"" + a.getTelefone() + "\", " +
				"\"" + a.getEmail() + "\", " +
				"\"" + a.getMatricula() + "\"); ");
		
		Vector<Aluno> vet = AlunoDAO.getInstance().buscarEmail("aluno@email");

		this.executaNoBanco("DELETE FROM aluno WHERE " +
				"aluno.nome = \"" + a.getNome() + "\" and " +
				"aluno.cpf = \"" + a.getCpf() + "\" and " +
				"aluno.telefone = \"" + a.getTelefone() + "\" and " +
				"aluno.email = \"" + a.getEmail() + "\" and " +
				"aluno.matricula = \"" + a.getMatricula() + "\";");
		
		assertTrue("Teste de Alteração.", vet.size() > 0);
	}
	
	
	

	private void executaNoBanco(String msg) throws SQLException{
		Connection con =  FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(msg);
		pst.executeUpdate();
		pst.close();
		con.close();
	}
	private boolean estaNoBanco(String query) throws SQLException{
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
		if(!rs.next())
		{
			rs.close();
			pst.close();
			con.close();
			return false;
		}
		else {
			rs.close();
			pst.close();
			con.close();
			return true;
		}
	}
}

	