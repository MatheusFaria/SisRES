/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.diasReservas;

import control.ManterSala;
import exception.PatrimonioException;
import java.awt.Frame;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Sala;
import view.horariosReservas.HorariosReservaEquipamento;
import view.horariosReservas.HorariosReservaPatrimonio;
import view.horariosReservas.HorariosReservaSala;
import view.reservasSalas.ReservaSalaView;

/**
 *
 * @author Parley
 */
public class DiaReservaSala extends DiaReservaPatrimonio{
	
	Sala sala;
	
	public DiaReservaSala(Frame parent, boolean modal, int indexSala) throws SQLException, PatrimonioException {
		super(parent, modal);
		sala = ManterSala.getInstance().getSalas_vet().get(indexSala);
		this.setName("DiaReservaSala");
	}

	@Override
	protected void visualizarAction(String data) {
		HorariosReservaSala reserva = new HorariosReservaSala(new JFrame(), true, data, sala);
		reserva.setVisible(true);
		reserva.setResizable(false);
	}
	
}
