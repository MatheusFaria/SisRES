package control;

import java.sql.SQLException;
import java.util.Vector;
import persistence.EquipamentoDAO;
import exception.PatrimonioException;
import model.Equipamento;

public class ManterEquipamento {

  private Vector<Equipamento> Equipamento_vet = new Vector<Equipamento>();
	private static ManterEquipamento instance;

	private ManterEquipamento() {
	}

	public static ManterEquipamento getInstance() {
		if(instance == null)
			instance = new ManterEquipamento();
		return instance;
	}
	
	public Vector<Equipamento> getEquipamento_vet() throws SQLException, PatrimonioException{
		this.Equipamento_vet = EquipamentoDAO.getInstance().buscarTodos();
		return this.Equipamento_vet;
	}

	public void inserir(String codigo, String descricao) throws PatrimonioException, SQLException {
		Equipamento equipamento = new Equipamento(codigo, descricao);
		EquipamentoDAO.getInstance().incluir(equipamento);
		this.Equipamento_vet.add(equipamento);
	}

	public void alterar(String codigo, String descricao, Equipamento equipamento) throws PatrimonioException, SQLException {
		Equipamento old_equipamento = new Equipamento(equipamento.getCodigo(), equipamento.getDescricao());
		equipamento.setCodigo(codigo);
		equipamento.setDescricao(descricao);
		EquipamentoDAO.getInstance().alterar(old_equipamento, equipamento);
	}

	public void excluir(Equipamento equipamento) throws SQLException {
		EquipamentoDAO.getInstance().excluir(equipamento);
		this.Equipamento_vet.remove(equipamento);
	}

}
