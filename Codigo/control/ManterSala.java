package control;

import model.Sala;

public class ManterSala {

	private static ManterSala instance;

	private ManterSala() {
	}

	public static ManterSala getInstance() {
		if(instance == null)
			instance = new ManterSala();
		return instance;
	}

	public void inserir(int codigo, String descricao, int capacidade) {
		//TODO
	}

	public void alterar(int codigo, String descricao, int capacidade, Sala sala) {
		//TODO
	}

	public void excluir(Sala sala) {
		//TODO
	}

}
