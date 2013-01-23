package control;

import java.sql.SQLException;
import java.util.Vector;
import persistence.PatrimonioDAO;
import exception.PatrimonioException;
import model.Patrimonio;

public class ManterPatrimonio {

  private Vector<Patrimonio> patrimonio_vet = new Vector<Patrimonio>();
	private static ManterPatrimonio instance;

	private ManterPatrimonio() {
	}

	public static ManterPatrimonio getInstance() {
		if(instance == null)
			instance = new ManterPatrimonio();
		return instance;
	}
	
	public Vector<Patrimonio> getPatrimonio_vet() throws SQLException, PatrimonioException{
		this.patrimonio_vet = PatrimonioDAO.getInstance().buscarTodos();
		return this.patrimonio_vet;
	}

	public void inserir(String codigo, String descricao) throws PatrimonioException, SQLException {
		Patrimonio equipamento = new Patrimonio(codigo, descricao);
		PatrimonioDAO.getInstance().incluir(equipamento);
		this.patrimonio_vet.add(equipamento);
	}

	public void alterar(String codigo, String descricao, Patrimonio equipamento) throws PatrimonioException, SQLException {
		Patrimonio old_equipamento = new Patrimonio(equipamento.getCodigo(), equipamento.getDescricao());
		equipamento.setCodigo(codigo);
		equipamento.setDescricao(descricao);
		PatrimonioDAO.getInstance().alterar(old_equipamento, equipamento);
	}

	public void excluir(Patrimonio equipamento) throws SQLException {
		PatrimonioDAO.getInstance().excluir(equipamento);
		this.patrimonio_vet.remove(equipamento);
	}

}
