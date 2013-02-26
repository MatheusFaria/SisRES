/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cadastros;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import control.ManterSala;
import exception.PatrimonioException;

/**
 * 
 * @author Parley
 */
public class CadastroSala extends CadastroPatrimonio {

    public CadastroSala(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setName("CadastroSala");
    }

    @Override protected void cadastroAction() {
        try {
            // JOptionPane.showMessageDialog(this, codigoTxtField.getText() +
            // descricaoTextArea.getText() + capacidadeTxtField.getText(),
            // "teste", JOptionPane.INFORMATION_MESSAGE, null);
            ManterSala.getInstance().inserir(codigoTxtField.getText(), descricaoTextArea.getText(), capacidadeTxtField.getText());

            JOptionPane.showMessageDialog(this, "Sala Cadastrada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
            this.setVisible(false);

        } catch (PatrimonioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getSQLState() + "\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }

    }
}
