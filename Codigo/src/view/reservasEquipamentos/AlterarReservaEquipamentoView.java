/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.reservasEquipamentos;

import control.ManterResEquipamentoProfessor;
import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;
import java.awt.Color;
import java.awt.Frame;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Equipamento;
import model.ReservaEquipamentoProfessor;

/**
 *
 * @author Parley
 */
public class AlterarReservaEquipamentoView  extends ReservaEquipamentoView{

	int index;
	ReservaEquipamentoProfessor reserva;
	
	private void resetComponents(){
		this.reservarButton.setText("Alterar");
		this.reservarButton.setName("AlterarButton");
		this.cpfLabel.setEnabled(false);
		this.cpfTextField.setBackground(new Color(200,208,254));
		this.cpfTextField.setEditable(false);
		this.horaTextField.setText(reserva.getHora());
		this.dataTextField.setText(reserva.getData());
		this.professorTextArea.setText(reserva.getProfessor().toString());
	}
	
	public AlterarReservaEquipamentoView(Frame parent, boolean modal, int index, int mes) throws SQLException, PatrimonioException, PatrimonioException, ClienteException, ReservaException {
		super(parent, modal);
		this.index = index;
		reserva = this.instanceProf.getReservasMes(mes).get(index);
		resetComponents();
	}

	@Override
	protected void reservarProfessor() {
		try {
			
			instanceProf.alterar(reserva);
			JOptionPane.showMessageDialog(this, "Reserva alterarada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
			this.setVisible(false);
		} catch (ReservaException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} 
	}
	
}
