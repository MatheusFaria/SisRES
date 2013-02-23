/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.diasReservas;

import control.ManterResEquipametoProfessor;
import java.awt.Frame;
import javax.swing.JFrame;
import view.horariosReservas.HorariosReservaEquipamento;
import view.horariosReservas.HorariosReservaPatrimonio;

/**
 *
 * @author Parley
 */
public class DiaReservaEquipamento extends DiaReservaPatrimonio{
	
	ManterResEquipametoProfessor instance;

	public DiaReservaEquipamento(Frame parent, boolean modal) {
		super(parent, modal);
		instance = ManterResEquipametoProfessor.getInstance();
	}

	@Override
	protected void visualizarAction(String data) {
		HorariosReservaPatrimonio reserva = new HorariosReservaEquipamento(new JFrame(), true, data, null);
		reserva.setVisible(true);
		reserva.setResizable(false);
	}
}
