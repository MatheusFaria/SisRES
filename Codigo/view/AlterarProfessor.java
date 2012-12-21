/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.ManterProfessor;
import exception.ClienteException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Parley
 */
public class AlterarProfessor extends CadastroCliente{
    
    int index;
    
    public AlterarProfessor(java.awt.Frame parent, boolean modal, int index) {
        super(parent, modal);
        this.setTitle("Alterar");
        this.cadastroBtn.setText("Alterar");
        try {
            this.nomeTxtField.setText(ManterProfessor.getInstance().getProfessores_vet().get(index).getNome());
            this.emailTxtField.setText(ManterProfessor.getInstance().getProfessores_vet().get(index).getEmail());
            this.telefoneTxtField.setText(ManterProfessor.getInstance().getProfessores_vet().get(index).getTelefone());
            this.matriculaTxtField.setText(ManterProfessor.getInstance().getProfessores_vet().get(index).getMatricula());
            this.cpfTxtField.setText(ManterProfessor.getInstance().getProfessores_vet().get(index).getCpf());
            
            this.index = index;
            
        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }
    
    private void cadastroBtnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            //Sala sala = new Sala(codigoTxtField.getText(), jTextArea1.getText(), capacidadeTxtField.getText());
            control.ManterProfessor.getInstance().alterar(nomeTxtField.getText(), cpfTxtField.getText(), matriculaTxtField.getText(), 
                    telefoneTxtField.getText(), emailTxtField.getText(),
                    ManterProfessor.getInstance().getProfessores_vet().get(index));
            
            
            JOptionPane.showMessageDialog(this, "Sala Alterada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
            this.setVisible(false);
            
        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
        
    }
}
