/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.mainViews;

import view.cadastros.CadastroEquipamento;
import exception.PatrimonioException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Patrimonio;

/**
 *
 * @author Parley
 */
public class EquipamentoView extends PatrimonioView {// implements CadastroGeral{

	/**
	 * Creates new form ClienteView
	 */
	public EquipamentoView(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		this.setTitle("Equipamentos");
		pesquisarLbl.setText("Equipamento desejado: ");
	}

	private Vector<String> fillDataVector(Patrimonio equipamento) {

		if (equipamento == null) {
			return null;
		}

		Vector<String> nomesTabela = new Vector<String>();
		nomesTabela.add(equipamento.getCodigo());
		nomesTabela.add(equipamento.getDescricao());


		return nomesTabela;

	}

	protected DefaultTableModel fillTable() { 
		DefaultTableModel table = new DefaultTableModel();
		/*
		try {
			DefaultTableModel table = new DefaultTableModel();

			Iterator i = ManterPatrimonio.getInstance().getPatrimonios_vet().iterator();

			table.addColumn("Codigo");
			table.addColumn("Nome");
			while (i.hasNext()) {
				Patrimonio equipamento = (Patrimonio) i.next();
				table.addRow(fillDataVector(equipamento));
			}

			return table;
		} catch (PatrimonioException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		}
*/
		return table;

	}

	@Override
	protected void cadastrarAction() {
		CadastroEquipamento cadastro = new CadastroEquipamento(new javax.swing.JFrame(), true);
		cadastro.setResizable(false);
		cadastro.setVisible(true);
		this.tabelaPatrimonio.setModel(fillTable());
	}

	@Override
	protected void alterarAction(int index) {
		/*
		 * AlterarEquipamento alteracao = new AlterarEquipamento(new javax.swing.JFrame(), true, index);
		alteracao.setResizable(false);
		alteracao.setVisible(true);
		this.tabelaPatrimonio.setModel(fillTable());
		*/
	}

	@Override
	protected void excluirAction(int index) {
		/*
		try {
			int confirm = JOptionPane.showConfirmDialog(this, "Deseja mesmo excluir Sala: "
				+ ManterPatrimonio.getInstance().getSalas_vet().get(index).getDescricao() + "?", "Excluir", JOptionPane.YES_NO_OPTION);

			if (confirm == JOptionPane.YES_OPTION) {
				ManterPatrimonio.getInstance().excluir(ManterPatrimonio.getInstance().getSalas_vet().get(index));
				JOptionPane.showMessageDialog(this, "Sala excluida com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
			}
			this.tabelaPatrimonio.setModel(fillTable());

		} catch (PatrimonioException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		}
		*/
	}

}
