/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.reservas;

import control.ManterResEquipametoProfessor;
import java.awt.Frame;

/**
 *
 * @author Parley
 */
public class ReservaEquipamento extends ReservaPatrimonio{
	
	ManterResEquipametoProfessor instance;

	public ReservaEquipamento(Frame parent, boolean modal, int index) {
		super(parent, modal);
		instance = ManterResEquipametoProfessor.getInstance();
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
