/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.horariosReservas;

import control.ManterResSalaAluno;
import control.ManterResSalaProfessor;
import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ReservaSalaAluno;
import model.ReservaSalaProfessor;
import model.Sala;
import view.reservasSalas.AlterarReservaSalaView;
import view.reservasSalas.FazerReservaSalaView;
import view.reservasSalas.ReservaSalaView;

/**
 *
 * @author Parley
 */
public class HorariosReservaSala extends HorariosReservaPatrimonio {

	ManterResSalaAluno instanceAluno;
	ManterResSalaProfessor instanceProf;
	Sala sala;

	public HorariosReservaSala(java.awt.Frame parent, boolean modal, String data, Sala sala) {
		super(parent, modal, data);
		this.sala = sala;
	}
	
	
	protected Vector<String> fillDataVector(Object o, int index) {
		Vector<String> nomesTabela = new Vector<String>();
		if (o instanceof ReservaSalaAluno) {
			ReservaSalaAluno r = (ReservaSalaAluno) o;
			if (!data.isEmpty() && r.getData().startsWith(data.substring(0, 2))) {
				nomesTabela.add(String.valueOf(index));
				nomesTabela.add(r.getHora());
				nomesTabela.add(r.getAluno().getNome());
				nomesTabela.add(r.getAluno().getMatricula());
				nomesTabela.add(r.getFinalidade());
				nomesTabela.add(r.getSala().getCodigo());
				nomesTabela.add(r.getSala().getDescricao());
				nomesTabela.add(r.getCadeiras_reservadas());
				nomesTabela.add(r.getSala().getCapacidade());
			}
		} else if (o instanceof ReservaSalaProfessor) {
			ReservaSalaProfessor r = (ReservaSalaProfessor) o;
			if (!data.isEmpty() && r.getData().startsWith(data.substring(0, 2))) {

				nomesTabela.add(String.valueOf(index));
				nomesTabela.add(r.getHora());
				nomesTabela.add(r.getProfessor().getNome());
				nomesTabela.add(r.getProfessor().getMatricula());
				nomesTabela.add(r.getFinalidade());
				nomesTabela.add(r.getSala().getCodigo());
				nomesTabela.add(r.getSala().getDescricao());
				nomesTabela.add(r.getSala().getCapacidade());
				nomesTabela.add(r.getSala().getCapacidade());
			}
		}

		return nomesTabela;

	}

	@Override
	protected DefaultTableModel fillTable() {
		DefaultTableModel table = new DefaultTableModel();
		instanceAluno = ManterResSalaAluno.getInstance();
		instanceProf = ManterResSalaProfessor.getInstance();
		try {
			table.addColumn("");
			table.addColumn("Hora:");
			table.addColumn("Nome");
			table.addColumn("Matricula");
			table.addColumn("Finalidade");
			table.addColumn("Codigo da Sala");
			table.addColumn("Descricao da Sala");
			table.addColumn("Reservadas");
			table.addColumn("Capacidade");

			this.mes = Integer.parseInt(this.data.substring(3, 5));

			Vector v = instanceProf.getReservasMes(mes);
			for (int i = 0; i < v.size(); i++) {
				table.addRow(fillDataVector(v.get(i), i));

			}
			v.clear();

			v = instanceAluno.getReservasMes(mes);
			for (int i = 0; i < v.size(); i++) {
				table.addRow(fillDataVector(v.get(i), i));

			}



		} catch (SQLException ex) {
			Logger.getLogger(HorariosReservaPatrimonio.class.getName()).log(Level.SEVERE, null, ex);
		} catch (PatrimonioException ex) {
			Logger.getLogger(HorariosReservaPatrimonio.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClienteException ex) {
			Logger.getLogger(HorariosReservaPatrimonio.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ReservaException ex) {
			Logger.getLogger(HorariosReservaPatrimonio.class.getName()).log(Level.SEVERE, null, ex);
		}
		return table;

	}

	@Override
	protected void cancelarReservaAction(int index) {
		try {
			int confirm = JOptionPane.showConfirmDialog(this, "Deseja mesmo excluir Reserva?\n"
				+ instanceAluno.getReservasMes(mes).get(index).toString(), "Excluir", JOptionPane.YES_NO_OPTION);

			if (confirm == JOptionPane.YES_OPTION) {
				this.instanceAluno.excluir(instanceAluno.getReservasMes(mes).get(index));
				JOptionPane.showMessageDialog(this, "Reserva excluida com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (PatrimonioException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (ClienteException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (ReservaException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		}
	}

	@Override
	protected void reservarAction() {
		try {
			ReservaSalaView reserva = new FazerReservaSalaView(new JFrame(), true, sala, this.data);
			reserva.setVisible(true);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (PatrimonioException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (ClienteException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (ReservaException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		}
	}

	@Override
	protected void alterarAction(int index) {
		try {

			index = Integer.parseInt((String) this.reservasTable.getModel().getValueAt(index, 0));
			ReservaSalaView reserva = new AlterarReservaSalaView(new JFrame(), true, index, this.mes);
			reserva.setVisible(true);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (PatrimonioException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (ClienteException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (ReservaException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		}
	}
}