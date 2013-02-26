package view.alteracoes;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import view.cadastros.CadastroPatrimonio;
import control.ManterEquipamento;
import exception.PatrimonioException;

/**
 * 
 * @author Parley
 */
public class AlterarEquipamento extends CadastroPatrimonio {

    private int index2 = 0;

    public AlterarEquipamento(java.awt.Frame parent, boolean modal, int index) {
        super(parent, modal);
        this.setTitle("Alterar");
        this.setName("AlterarEquipamento");
        this.cadastroBtn.setText("Alterar");
        this.cadastroBtn.setName("Alterar");
        this.capacidadeLbl.setVisible(false);
        this.capacidadeTxtField.setVisible(false);
        index2 = index;

        try {

            this.codigoTxtField.setText(ManterEquipamento.getInstance().getEquipamento_vet().get(index).getCodigo());
            this.descricaoTextArea.setText(ManterEquipamento.getInstance().getEquipamento_vet().get(index).getDescricao());
            this.index2 = index;

        } catch (PatrimonioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }

    }

    @Override protected void cadastroAction() {
        try {

            ManterEquipamento.getInstance().alterar(codigoTxtField.getText(), descricaoTextArea.getText(),
                    ManterEquipamento.getInstance().getEquipamento_vet().get(index2));

            JOptionPane.showMessageDialog(this, "Equipamento alterado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE,
                    null);
            this.setVisible(false);

        } catch (PatrimonioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }
}
