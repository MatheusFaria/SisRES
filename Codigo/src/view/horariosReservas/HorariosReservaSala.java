/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.horariosReservas;

import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.ReservaSalaAluno;
import model.Sala;
import view.reservas.AlterarReservaSalaView;
import view.reservas.FazerReservaSalaView;
import view.reservas.ReservaSalaView;

/**
 *
 * @author Parley
 */
public class HorariosReservaSala extends HorariosReservaPatrimonio {

	Sala sala;

	public HorariosReservaSala(java.awt.Frame parent, boolean modal, String data, Sala sala) {
		super(parent, modal, data);
		this.sala = sala;
	}

	@Override
	protected void cancelarReservaAction(int index) {
		try {
			int confirm = JOptionPane.showConfirmDialog(this, "Deseja mesmo excluir Reserva?\n"
				+ instanceAluno.getReservasMes(mes).get(index).toString() , "Excluir", JOptionPane.YES_NO_OPTION);

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