package view.mainViews;

import model.Patrimonio;
import view.mainViews.EquipamentoView;
import view.cadastros.CadastroEquipamento;
import view.alteracoes.AlterarEquipamento;
import exception.PatrimonioException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author Parley
 * @editor Aulus
 */
public class EquipamentoView extends PatrimonioView {
	/**
     * Creates new form EquipamentoView
     */
	public EquipamentoView(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		this.setTitle("Equipamentos");
	}

	private Vector <String> fillDataVector(Patrimonio equipamento) {

		if(equipamento == null)
			return null;
		
		Vector <String> nomesTabela = new Vector<String> ();
		nomesTabela.add(equipamento.getCodigo());
		nomesTabela.add(equipamento.getDescricao());

		return nomesTabela;
	}

	protected DefaultTableModel fillTable() { 
        try {
            DefaultTableModel table = new DefaultTableModel();
            
            Iterator<Patrimonio>  i = control.ManterPatrimonio.getInstance().getPatrimonio_vet().iterator();
                        
            table.addColumn("Codigo");     
            table.addColumn("Descricao");
            while(i.hasNext()) {
                Patrimonio equipamento =  (Patrimonio) i.next();
                table.addRow(fillDataVector(equipamento));
            	}
            return table;
	        } catch (PatrimonioException ex) {
	            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
	        	} catch (SQLException ex) {
	            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
	        		}
        return null;
		}

	protected void cadastrarAction() {
		CadastroEquipamento cadastro = new CadastroEquipamento(new javax.swing.JFrame(), true);
		cadastro.setResizable(false);
		cadastro.setVisible(true);
		this.tabelaPatrimonio.setModel(fillTable());
		}	
	
	protected void alterarAction(int index) {
        if(index < 0){
            JOptionPane.showMessageDialog(this, "Selecione uma linha!", "Erro", JOptionPane.ERROR_MESSAGE, null);
            return;
        }

        AlterarEquipamento alteracao = new AlterarEquipamento(new javax.swing.JFrame(), true, index);
        alteracao.setResizable(false);
        alteracao.setVisible(true);  
	}

	@Override
	protected void excluirAction(int index) {
		try {
			int confirm = JOptionPane.showConfirmDialog(this, "Deseja mesmo excluir Equipamento: "
				+ control.ManterPatrimonio.getInstance().getPatrimonio_vet().get(index).getDescricao() + "?", "Excluir", JOptionPane.YES_NO_OPTION);

			if (confirm == JOptionPane.YES_OPTION) {
				control.ManterPatrimonio.getInstance().excluir(control.ManterPatrimonio.getInstance().getPatrimonio_vet().get(index));
				JOptionPane.showMessageDialog(this, "Equipamento excluida com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
			}
			this.tabelaPatrimonio.setModel(fillTable());
			} catch (PatrimonioException ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
			}
		}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton alterar;
    protected javax.swing.JButton cadastrar;
    protected javax.swing.JButton excluir;
    protected javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JPanel panelBotoes;
    protected javax.swing.JPanel panelLista;
    protected javax.swing.JButton pesquisarBtn;
    protected javax.swing.JLabel pesquisarLbl;
    protected javax.swing.JTextField pesquisarTextField;
    protected javax.swing.JTable tabelaPatrimonio;
    protected javax.swing.JButton visualizarBtn;
    // End of variables declaration//GEN-END:variables
}
