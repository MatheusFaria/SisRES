/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.horariosReservas;

import model.Equipamento;
import model.Sala;

/**
 *
 * @author Parley
 */
public class HorariosReservaEquipamento extends HorariosReservaPatrimonio{

	Equipamento eq;
	
	public HorariosReservaEquipamento(java.awt.Frame parent, boolean modal, String data, Equipamento eq) {
		super(parent, modal, data);
		this.eq = eq;
	}
	
	@Override
	protected void cancelarReservaAction(int index) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void reservarAction() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void alterarAction(int index) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
