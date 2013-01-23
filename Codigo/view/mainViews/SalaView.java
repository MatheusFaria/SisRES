/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.mainViews;

import control.ManterSala;
import exception.PatrimonioException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Sala;
import view.alteracoes.AlterarEquipamento;
import view.cadastros.CadastroPatrimonio;
import view.cadastros.CadastroSala;

/**
 *
 * @author Parley
 */
public class SalaView extends PatrimonioView {

	public SalaView(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		pesquisarLbl.setText("Digite a sala desejada: ");
	}

	protected Vector<String> fillDataVector(Sala sala) {

		if (sala == null) {
			return null;
		}

		Vector<String> nomesTabela = new Vector<String>();
		
		nomesTabela.add(sala.getCodigo());
		nomesTabela.add(sala.getDescricao());
		nomesTabela.add(sala.getCapacidade());

		return nomesTabela;

	}

	
	@Override
	protected DefaultTableModel fillTable() {
		try {
			DefaultTableModel table = new DefaultTableModel();

			Iterator<Sala> i = ManterSala.getInstance().getSalas_vet().iterator();

			table.addColumn("Codigo");
			table.addColumn("Nome");
			table.addColumn("Capacidade");
			while (i.hasNext()) {
				Sala sala = i.next();
				table.addRow(fillDataVector(sala));
			}

			return table;
			
		} catch (PatrimonioException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		}

		return null;
	}

	@Override
	protected void cadastrarAction() {
		CadastroPatrimonio cadastro = new CadastroSala(new javax.swing.JFrame(), true);
		cadastro.setResizable(false);
		cadastro.setVisible(true);
		this.tabelaPatrimonio.setModel(fillTable());
	}

	@Override
	protected void alterarAction(int index) {
		
		AlterarEquipamento alteracao = new AlterarEquipamento(new javax.swing.JFrame(), true, index);
		alteracao.setResizable(false);
		alteracao.setVisible(true);
		this.tabelaPatrimonio.setModel(fillTable());
	}

	@Override
	protected void excluirAction(int index) {
		try {
			int confirm = JOptionPane.showConfirmDialog(this, "Deseja mesmo excluir Sala: "
				+ ManterSala.getInstance().getSalas_vet().get(index).getDescricao() + "?", "Excluir", JOptionPane.YES_NO_OPTION);

			if (confirm == JOptionPane.YES_OPTION) {
				ManterSala.getInstance().excluir(ManterSala.getInstance().getSalas_vet().get(index));
				JOptionPane.showMessageDialog(this, "Sala excluida com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
			}
			this.tabelaPatrimonio.setModel(fillTable());

		} catch (PatrimonioException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		}
	}
}
