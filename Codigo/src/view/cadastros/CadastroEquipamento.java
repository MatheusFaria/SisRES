/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cadastros;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import control.ManterEquipamento;
import exception.PatrimonioException;

/**
 * @author Parley
 * @editor Aulus
 */
public class CadastroEquipamento extends CadastroPatrimonio {

    public CadastroEquipamento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setName("CadastroEquipamento");
        this.capacidadeLbl.setVisible(false);
        this.capacidadeTxtField.setVisible(false);
    }

    @Override protected void cadastroAction() {

        try {
            ManterEquipamento.getInstance().inserir(codigoTxtField.getText(), descricaoTextArea.getText());
            JOptionPane.showMessageDialog(this, "Equipamento Cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE,
                    null);
            this.setVisible(false);

        } catch (PatrimonioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }

    }
}
