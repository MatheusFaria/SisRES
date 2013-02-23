/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.reservas;

import control.ManterResSalaAluno;
import control.ManterResSalaProfessor;
import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;
import java.awt.Color;
import java.awt.Frame;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Sala;

/**
 *
 * @author Parley
 */
public class FazerReservaSalaView extends ReservaSalaView{

	public FazerReservaSalaView(Frame parent, boolean modal, Sala sala, String data) throws SQLException, PatrimonioException, PatrimonioException, ClienteException, ReservaException {
		super(parent, modal);
		this.sala = sala;
		this.dataTextField.setText(data);
		this.salaTextArea.setText(sala.toString());
		this.qntCadeirasTxtField.setText(String.valueOf(instanceAluno.cadeirasDisponveis(sala)));
	}
	
	
	
	@Override
	protected void reservarAluno() {
		try {
			instanceAluno.inserir(sala, aluno,
				this.dataTextField.getText(),
				this.horaTextField.getText(),
				this.finalidadeTextField.getText(), this.qntCadeirasReservadasTextField.getText());
			
			JOptionPane.showMessageDialog(this, "Reserva feita com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);

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
		this.alunoLabel.setText(this.professorRadioButton.getText() + ": ");
		this.qntCadeirasReservadasTextField.setEditable(false);
		this.qntCadeirasReservadasTextField.setBackground(new Color(200,208,254));
		this.qntCadeirasReservadasTextField.setText(this.qntCadeirasTxtField.getText());
		this.instanceProf = ManterResSalaProfessor.getInstance();
		this.instanceAluno = null;
	}

	@Override
	protected void alunoRadioButtonAction() {
		this.alunoLabel.setText(this.alunoRadioButton.getText() + ": ");
		this.qntCadeirasReservadasTextField.setEnabled(true);
		this.qntCadeirasReservadasTextField.setEditable(true);
		this.qntCadeirasReservadasTextField.setBackground(Color.white);
		this.instanceAluno = ManterResSalaAluno.getInstance();
		this.instanceProf = null;
	}
}
