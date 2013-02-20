/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.reservas;

import control.ManterResSalaProfessor;
import java.awt.Frame;

/**
 *
 * @author Parley
 */
public class ReservaSala extends ReservaPatrimonio{
	
	ManterResSalaProfessor instance;

	public ReservaSala(Frame parent, boolean modal, int index) {
		super(parent, modal);
		instance = ManterResSalaProfessor.getInstance();
	}

	@Override
	protected void reservarAction() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void cancelarReservaAction() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
