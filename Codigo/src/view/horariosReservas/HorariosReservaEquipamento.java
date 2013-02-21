/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.horariosReservas;

import control.ManterResEquipametoProfessor;
import java.awt.Frame;

/**
 *
 * @author Parley
 */
public class HorariosReservaEquipamento extends HorariosReservaPatrimonio{
	
	ManterResEquipametoProfessor instance;

	public HorariosReservaEquipamento(Frame parent, boolean modal, int index) {
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
