/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.mainViews;

import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.JOptionPane;

import view.alteracoes.AlterarAluno;
import view.cadastros.CadastroAluno;
import view.cadastros.CadastroCliente;
import control.ManterAluno;
import exception.ClienteException;

/**
 * 
 * @author Parley
 */
public class AlunoView extends ClienteView {

    public AlunoView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setName("AlunoView");
    }

    public Iterator getIterator() {
        try {
            return ManterAluno.getInstance().getAluno_vet().iterator();

        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
        return null;
    }

    @Override public void cadastrarAction() {

        CadastroCliente cadastrar = new CadastroAluno(new javax.swing.JFrame(), true);
        cadastrar.setResizable(false);
        cadastrar.setVisible(true);
        tabelaCliente.setModel(fillTable());

    }

    @Override public void alterarAction(int index) {

        AlterarAluno alterar = new AlterarAluno(new javax.swing.JFrame(), true, index);
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

            int confirm = JOptionPane.showConfirmDialog(this, "Deseja mesmo excluir Aluno: "
                    + ManterAluno.getInstance().getAluno_vet().get(index).getNome() + "?", "Excluir", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                ManterAluno.getInstance().excluir(ManterAluno.getInstance().getAluno_vet().get(index));
                JOptionPane.showMessageDialog(this, "Aluno excluido com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
            }
            this.tabelaCliente.setModel(fillTable());

        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

}
