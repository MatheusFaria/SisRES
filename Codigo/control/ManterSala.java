package control;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;
import persistence.SalaDAO;
import exception.PatrimonioException;
import model.Sala;

public class ManterSala {

	private Vector<Sala> salas_vet = new Vector<Sala>();
	
	//Singleton
		private static ManterSala instance;
		private ManterSala() {
			
		}
		public static ManterSala getInstance() {
		if(instance == null)
			instance = new ManterSala();
		return instance;
	}
	//
		
	public Vector<Sala> getSalas_vet() throws SQLException, PatrimonioException{
		this.salas_vet = SalaDAO.getInstance().buscarTodos();
		return this.salas_vet;
	}
	
	public Sala procurarNoVetor(Sala teste) throws PatrimonioException, SQLException {
		Vector<Sala> todos = this.getSalas_vet();
		Iterator<Sala> i = todos.iterator();
		while(i.hasNext()){
			Sala e = i.next();
			if(e.equals(teste))
				return e;			
		}
		return null;
	}

	public void inserir(String codigo, String descricao, String capacidade) throws PatrimonioException, SQLException {
				Sala sala = new Sala(codigo, descricao, capacidade);
				SalaDAO.getInstance().incluir(sala);
				this.salas_vet.add(sala);	
	}

	public void alterar(String codigo, String descricao, String capacidade, Sala sala) throws PatrimonioException, SQLException {
				Sala old_sala = new Sala(sala.getCodigo(), sala.getDescricao(), sala.getCapacidade());
				sala.setCodigo(codigo);
				sala.setDescricao(descricao);
				sala.setCapacidade(capacidade);
				SalaDAO.getInstance().alterar(old_sala, sala);
	}

	public void excluir(Sala sala) throws SQLException, PatrimonioException {
				SalaDAO.getInstance().excluir(sala);
				this.salas_vet.remove(sala);
	}

}
