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

	//os blocos try catch dos métodos seguintes são para evitar estourar um exceção para o último nível.
	//as tabelas que ele tenta consultar no banco não foram criadas.
	
	public void inserir(String codigo, String descricao, String capacidade) throws PatrimonioException, SQLException {
				try{
					Sala sala = new Sala(codigo, descricao, capacidade);
					SalaDAO.getInstance().incluir(sala);
					this.salas_vet.add(sala);
				}
				catch(PatrimonioException e)
				{
					//tratar as exceção aqui.
				}
			
	}

	public void alterar(String codigo, String descricao, String capacidade, Sala sala) throws PatrimonioException, SQLException {
		
			try {
				Sala old_sala = new Sala(sala.getCodigo(), sala.getDescricao(), sala.getCapacidade());
				sala.setCodigo(codigo);
				sala.setDescricao(descricao);
				sala.setCapacidade(capacidade);
				SalaDAO.getInstance().alterar(old_sala, sala);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//tabela faltando no banco.
			}
	
	}

	public void excluir(Sala sala) throws SQLException, PatrimonioException {
		
			try {
				SalaDAO.getInstance().excluir(sala);
				this.salas_vet.remove(sala);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//tabela faltando no banco.
			}
		
	}

}
