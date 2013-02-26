/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.mainViews;

import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.JOptionPane;

import view.alteracoes.AlterarProfessor;
import view.cadastros.CadastroCliente;
import view.cadastros.CadastroProfessor;
import control.ManterProfessor;
import exception.ClienteException;

/**
 * 
 * @author Parley
 */
public class ProfessorView extends ClienteView {

    public ProfessorView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setName("ProfessorView");
    }

    public Iterator getIterator() {
        try {
            return ManterProfessor.getInstance().getProfessores_vet().iterator();

        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
        return null;
    }

    @Override public void cadastrarAction() {

        CadastroCliente cadastrar = new CadastroProfessor(new javax.swing.JFrame(), true);
        cadastrar.setResizable(false);
        cadastrar.setVisible(true);
        tabelaCliente.setModel(fillTable());

    }

    @Override public void alterarAction(int index) {

        AlterarProfessor alterar = new AlterarProfessor(new javax.swing.JFrame(), true, index);
        alterar.setResizable(false);
        alterar.setVisible(true);
        this.tabelaCliente.setModel(fillTable());
    }

    @Override public void excluirAction() {
        try {
            int index = this.tabelaCliente.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Selecione uma linha!", "Erro", JOptionPane.ERROR_MESSAGE, null);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Deseja mesmo excluir Professor: "
                    + ManterProfessor.getInstance().getProfessores_vet().get(index).getNome() + "?", "Excluir",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                ManterProfessor.getInstance().excluir(ManterProfessor.getInstance().getProfessores_vet().get(index));
                JOptionPane.showMessageDialog(this, "Professor excluido com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE,
                        null);
            }
            this.tabelaCliente.setModel(fillTable());

        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }
}