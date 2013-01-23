/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.alteracoes;

import view.cadastros.CadastroPatrimonio;
import control.ManterPatrimonio;
import exception.PatrimonioException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.alteracoes;

import control.ManterPatrimonio;
import exception.PatrimonioException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.cadastros.CadastroEquipamento;

/**
 * @author Parley
 * @editor Aulus
 */
public class AlterarEquipamento extends CadastroEquipamento{
    
    private int index = 0;
    
    public AlterarEquipamento(java.awt.Frame parent, boolean modal, int index) {
        super(parent, modal);
        this.setTitle("Alterar");
        this.cadastroBtn.setText("Alterar");
        
        try {
            
            this.codigoTxtField.setText(ManterPatrimonio.getInstance().getPatrimonio_vet().get(index).getCodigo());
            this.descricaoTextArea.setText(ManterPatrimonio.getInstance().getPatrimonio_vet().get(index).getDescricao());
            this.index = index;
            
        } catch (PatrimonioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (NullPointerException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		}
        
    }

	protected void cadastroAction() {
    	try {
            ManterPatrimonio.getInstance().alterar(codigoTxtField.getText(), descricaoTextArea.getText(), 
            	ManterPatrimonio.getInstance().getPatrimonio_vet().get(index));
            
            JOptionPane.showMessageDialog(this, "Equipamento Alterado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
            this.setVisible(false);
            
        } catch (PatrimonioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }   
    }
}
