/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.alteracoes;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import view.cadastros.CadastroCliente;
import control.ManterAluno;
import exception.ClienteException;

/**
 * 
 * @author Parley
 */
public class AlterarAluno extends CadastroCliente {

    int index2 = 0;

    public AlterarAluno(java.awt.Frame parent, boolean modal, int index) {
        super(parent, modal);
        this.setTitle("Alterar");
        this.setName("AlterarAluno");
        this.cadastroBtn.setText("Alterar");
        this.cadastroBtn.setName("Alterar");
        this.index2 = index;

        try {
            this.nomeTxtField.setText(ManterAluno.getInstance().getAluno_vet().get(index).getNome());
            this.emailTxtField.setText(ManterAluno.getInstance().getAluno_vet().get(index).getEmail());
            this.telefoneTxtField.setText(ManterAluno.getInstance().getAluno_vet().get(index).getTelefone());
            this.matriculaTxtField.setText(ManterAluno.getInstance().getAluno_vet().get(index).getMatricula());
            this.cpfTxtField.setText(ManterAluno.getInstance().getAluno_vet().get(index).getCpf());

        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    @Override public void cadastroAction() {
        try {
            ManterAluno.getInstance().alterar(nomeTxtField.getText(), cpfTxtField.getText(), matriculaTxtField.getText(),
                    telefoneTxtField.getText(), emailTxtField.getText(), ManterAluno.getInstance().getAluno_vet().get(index2));

            JOptionPane.showMessageDialog(this, "Aluno alterado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
            this.setVisible(false);
        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }
}
