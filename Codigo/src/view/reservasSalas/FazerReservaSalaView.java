/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.reservasSalas;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.Sala;
import control.ManterResSalaAluno;
import control.ManterResSalaProfessor;
import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;

/**
 * 
 * @author Parley
 */
public class FazerReservaSalaView extends ReservaSalaView {

    public FazerReservaSalaView(Frame parent, boolean modal, Sala sala, String data) throws SQLException, PatrimonioException,
            PatrimonioException, ClienteException, ReservaException {
        super(parent, modal);
        this.sala = sala;
        this.dataTextField.setText(data);
        this.salaTextArea.setText(sala.toString());
        this.qntCadeirasTxtField.setText(sala.getCapacidade());
        this.setName("FazerReservaSalaView");
        this.horaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horaAction(evt);
            }
        });
    }

    private void horaAction(ActionEvent evt) {
        try {
            this.qntCadeirasTxtField.setText(String.valueOf(instanceAluno.cadeirasDisponveis(sala, this.dataTextField.getText(),
                    this.horaTextField.getText())));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PatrimonioException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClienteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ReservaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override protected void reservarAluno() {
        try {
            instanceAluno.inserir(sala, aluno, this.dataTextField.getText(), this.horaTextField.getText(),
                    this.finalidadeTextField.getText(), this.qntCadeirasReservadasTextField.getText());

            instanceAluno.getResAlunoSala_vet();
            // System.out.println(v.toString());

            JOptionPane.showMessageDialog(this, "Reserva feita com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);

            this.setVisible(false);
        } catch (ReservaException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (PatrimonioException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ClienteException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    @Override protected void reservarProfessor() {
        try {

            instanceProf.inserir(sala, prof, this.dataTextField.getText(), this.horaTextField.getText(),
                    this.finalidadeTextField.getText());

            JOptionPane.showMessageDialog(this, "Reserva feita com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);

            this.setVisible(false);
        } catch (ReservaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    @Override protected void professorRadioButtonAction() {
        this.alunoLabel.setText(this.professorRadioButton.getText() + ": ");
        this.alunoTextArea.setText("");
        this.cpfTextField.setText("");
        this.qntCadeirasReservadasTextField.setEditable(false);
        this.qntCadeirasReservadasTextField.setBackground(new Color(200, 208, 254));
        this.qntCadeirasReservadasTextField.setText(this.qntCadeirasTxtField.getText());
        this.instanceProf = ManterResSalaProfessor.getInstance();
        this.instanceAluno = null;
    }

    @Override protected void alunoRadioButtonAction() {
        this.instanceAluno = ManterResSalaAluno.getInstance();
        this.alunoLabel.setText(this.alunoRadioButton.getText() + ": ");
        this.alunoTextArea.setText("");
        this.cpfTextField.setText("");
        this.qntCadeirasReservadasTextField.setEnabled(true);
        this.qntCadeirasReservadasTextField.setEditable(true);
        this.qntCadeirasReservadasTextField.setBackground(Color.white);
        this.instanceProf = null;
    }
}
