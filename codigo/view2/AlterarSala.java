/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view2;

import control.ManterSala;
import exception.PatrimonioException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Sala;

/**
 *
 * @author Parley
 */
public class AlterarSala extends CadastroSala{
    
    private int index;
    
    public AlterarSala(java.awt.Frame parent, boolean modal, int index) {
        super(parent, modal);
        this.setTitle("Alterar");
        this.cadastroBtn.setText("Alterar");
        try {
            
            this.codigoTxtField.setText(ManterSala.getInstance().getSalas_vet().get(index).getCodigo());
            this.jTextPane1.setText(ManterSala.getInstance().getSalas_vet().get(index).getDescricao());
            this.jTextArea1.setText(ManterSala.getInstance().getSalas_vet().get(index).getCapacidade());
            this.index = index;
            
        } catch (PatrimonioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
        
    }
    
    private void cadastroBtnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            //Sala sala = new Sala(codigoTxtField.getText(), jTextArea1.getText(), capacidadeTxtField.getText());
            control.ManterSala.getInstance().alterar(codigoTxtField.getText(), jTextArea1.getText(), capacidadeTxtField.getText(),
                    ManterSala.getInstance().getSalas_vet().get(index));
            
            JOptionPane.showMessageDialog(this, "Sala Alterada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
            this.setVisible(false);
            
        } catch (PatrimonioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
        
    }
}
