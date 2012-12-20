package view;

import exception.PatrimonioException;
import javax.swing.JOptionPane;
import model.Patrimonio;

public class CadastroEquipamento extends CadastroSala {

    public CadastroEquipamento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setComponents();
    }

    public void setComponents(){
        
    }
    
    private void cadastroBtnActionPerformed(java.awt.event.ActionEvent evt) {
          try {
            Patrimonio equipamento = new Patrimonio(codigoTxtField.getText(), jTextPane1.getText());
            
            JOptionPane.showMessageDialog(this, "Equipamento Cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
            this.setVisible(false);
        } catch (PatrimonioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
        
    }
    
}
