/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.reservas;

import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;
import java.awt.Color;
import java.awt.Frame;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.ReservaSalaAluno;
import model.Sala;

/**
 *
 * @author Parley
 */
public class AlterarReservaSalaView extends ReservaSalaView{

	int index;
	ReservaSalaAluno reserva;
	
	private void resetComponents(){
		this.reservarButton.setText("Alterar");
		this.reservarButton.setName("AlterarButton");
		this.alunoRadioButton.setSelected(true);
		this.cpfLabel.setEnabled(false);
		alunoRadioButtonAction();
	}
	
	public AlterarReservaSalaView(Frame parent, boolean modal, int index, int mes) throws SQLException, PatrimonioException, PatrimonioException, ClienteException, ReservaException {
		super(parent, modal);
		this.setName("AlterarReservaSalaView");
		reserva = instanceAluno.getReservasMes(mes).get(index);
		resetComponents();
		
	}
	@Override
	protected void reservarAluno() {
		try {
			instanceAluno.alterar(this.finalidadeTextField.getText(),
				this.qntCadeirasReservadasTextField.getText(), reserva);
			
			JOptionPane.showMessageDialog(this, "Reserva alterada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);

			this.setVisible(false);
		} catch (ReservaException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (PatrimonioException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (ClienteException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (NullPointerException ex) {
			JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		}
	}

	@Override
	protected void reservarProfessor() {
		//TODO
	}

	@Override
	protected void professorRadioButtonAction() {
	}

	@Override
	protected void alunoRadioButtonAction() {
		this.professorRadioButton.setEnabled(false);
		this.cpfTextField.setBackground(new Color(200,208,254));
		this.cpfTextField.setEditable(false);
		this.qntCadeirasReservadasTextField.setEditable(true);
		this.qntCadeirasReservadasTextField.setBackground(Color.white);
		this.horaTextField.setBackground(new Color(200,208,254));
		this.horaTextField.setEditable(false);
		this.horaTextField.setText(reserva.getHora());
		this.alunoTextArea.setText(reserva.getAluno().toString());
		this.salaTextArea.setText(reserva.getSala().toString());
		this.dataTextField.setText(reserva.getData());
		this.qntCadeirasTxtField.setText(reserva.getSala().getCapacidade());
		this.qntCadeirasReservadasTextField.setText(reserva.getCadeiras_reservadas());
		this.finalidadeTextField.setText(reserva.getFinalidade());
	}
}
