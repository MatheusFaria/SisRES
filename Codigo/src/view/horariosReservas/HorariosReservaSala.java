/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.horariosReservas;

import control.ManterResSalaProfessor;
import control.ManterSala;
import exception.PatrimonioException;
import java.awt.Frame;
import java.sql.SQLException;
import model.Sala;
import view.reservas.ReservaPatrimonio;

/**
 *
 * @author Parley
 */
public class HorariosReservaSala extends HorariosReservaPatrimonio{
	
	Sala sala;
	String horario;
	
	public HorariosReservaSala(Frame parent, boolean modal, int indexSala) throws SQLException, PatrimonioException {
		super(parent, modal);
		sala = ManterSala.getInstance().getSalas_vet().get(indexSala);
	}

	@Override
	protected void reservarAction() {
		
		ReservaPatrimonio reserva = new ReservaPatrimonio(null, true, sala);
		reserva.setVisible(true);
	}

	@Override
	protected void cancelarReservaAction() {
		this.setVisible(false);
	}
}
