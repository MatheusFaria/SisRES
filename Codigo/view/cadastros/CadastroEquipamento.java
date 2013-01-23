/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cadastros;

import exception.PatrimonioException;
import javax.swing.JOptionPane;
import model.Patrimonio;

/**
 *
 * @author Parley
 */
public class CadastroEquipamento extends CadastroPatrimonio {

	/**
	 * Creates new form CadastroCliente
	 */
	public CadastroEquipamento(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		super.capacidadeLbl.setVisible(false);
		super.capacidadeTxtField.setVisible(false);
	}

	@Override
	protected void cadastroAction() {
		/*try {
			ManterPatrimonio.getInstance().inserir(codigoTxtField.getText(), descricaoTextArea.getText());

			JOptionPane.showMessageDialog(this, "Sala Cadastrada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
			this.setVisible(false);
			JOptionPane.showMessageDialog(this, "Equipamento Cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
			this.setVisible(false);
		} catch (PatrimonioException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		}
		*/
	}


}
