package view.cadastros;

import java.sql.SQLException;
import exception.PatrimonioException;
import javax.swing.JOptionPane;
import control.ManterPatrimonio;
/**
 * @author Parley
 * @editor Aulus
 */
public class CadastroEquipamento extends CadastroPatrimonio {
    public CadastroEquipamento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.capacidadeLbl.setVisible(false);
	this.capacidadeTxtField.setVisible(false);
	//A classe pai não pode conter elementos que a filha não contenha.
	//Na classe de sala é que deve ser criado o componente extra.
    }
    
    @Override
	protected void cadastroAction() {	        
   	try {
            ManterPatrimonio.getInstance().inserir(codigoTxtField.getText(), descricaoTextArea.getText());
            JOptionPane.showMessageDialog(this, "Equipamento Cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
            this.setVisible(false);
        } catch (PatrimonioException ex) {
		JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
        	JOptionPane.showMessageDialog(this, ex.getSQLState() + "\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }
}
