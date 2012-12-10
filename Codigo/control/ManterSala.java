package control;

import java.util.Vector;

import persistence.SalaDAO;
import exception.PatrimonioException;
import model.Sala;

public class ManterSala {

	private Vector<Sala> salas_vet = SalaDAO.getInstance().buscarTodos();
	private static ManterSala instance;

	private ManterSala() {
	}

	public static ManterSala getInstance() {
		if(instance == null)
			instance = new ManterSala();
		return instance;
	}
	
	public Vector<Sala> getSalas_vet(){
		return this.salas_vet;
	}

	public void inserir(String codigo, String descricao, String capacidade) throws PatrimonioException {
		Sala sala = new Sala(codigo, descricao, capacidade);
		SalaDAO.getInstance().incluir(sala);
		this.salas_vet.add(sala);
	}

	public void alterar(String codigo, String descricao, String capacidade, Sala sala) throws PatrimonioException {
		sala.setCodigo(codigo);
		sala.setDescricao(descricao);
		sala.setCapacidade(capacidade);
		SalaDAO.getInstance().alterar(sala);
	}

	public void excluir(Sala sala) {
		SalaDAO.getInstance().excluir(sala);
		this.salas_vet.remove(sala);
	}

}
