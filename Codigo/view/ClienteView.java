package view;

import control.ManterProfessor;
import exception.ClienteException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Professor;
import model.Sala;

public class ClienteView extends javax.swing.JDialog {

    public ClienteView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    private Vector <String> fillDataVector(int index) {
        try {
            if(index < 0)
                return null;
            
            Vector <String> nomesTabela = new Vector<String> ();
            nomesTabela.add(ManterProfessor.getInstance().getProfessores_vet().get(index).getMatricula());
            nomesTabela.add(ManterProfessor.getInstance().getProfessores_vet().get(index).getNome());
            nomesTabela.add(ManterProfessor.getInstance().getProfessores_vet().get(index).getTelefone());
            nomesTabela.add(ManterProfessor.getInstance().getProfessores_vet().get(index).getCpf());
            nomesTabela.add(ManterProfessor.getInstance().getProfessores_vet().get(index).getEmail());
            
            return nomesTabela;
        } catch (SQLException ex) {
            Logger.getLogger(SalaView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClienteException ex) {
            Logger.getLogger(SalaView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private DefaultTableModel fillTable(){
        try {
            DefaultTableModel table = new DefaultTableModel();
            
            Iterator <Professor> i = ManterProfessor.getInstance().getProfessores_vet().iterator();
               
            table.addColumn("Matricula");   
            table.addColumn("Nome");
            table.addColumn("Telefone");
            table.addColumn("CPF");
            table.addColumn("E-mail");
            
            while(i.hasNext()){
                Professor prof =  i.next();
                int index = ManterProfessor.getInstance().getProfessores_vet().indexOf(prof);
                table.addRow(fillDataVector(index));
                
            }
           
            return table;
        } catch (SQLException ex) {
            Logger.getLogger(SalaView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClienteException ex) {
            Logger.getLogger(SalaView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private void initComponents() {

        panelBotoes = new javax.swing.JPanel();
        cadastrarBtn = new javax.swing.JButton();
        alterarBtn = new javax.swing.JButton();
        excluirBtn = new javax.swing.JButton();
        panelLista = new javax.swing.JPanel();
        pesquisarLbl = new javax.swing.JLabel();
        pesquisarBtn = new javax.swing.JButton();
        pesquisarTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCliente = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cliente");

        panelBotoes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cadastrarBtn.setText("Cadastrar");
        cadastrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarBtnActionPerformed(evt);
            }
        });

        alterarBtn.setText("Alterar");
        alterarBtn.setEnabled(false);
        alterarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarBtnActionPerformed(evt);
            }
        });

        excluirBtn.setText("Excluir");
        excluirBtn.setEnabled(false);
        excluirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotoesLayout = new javax.swing.GroupLayout(panelBotoes);
        panelBotoes.setLayout(panelBotoesLayout);
        panelBotoesLayout.setHorizontalGroup(
            panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(excluirBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(alterarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cadastrarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBotoesLayout.setVerticalGroup(
            panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoesLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(cadastrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(alterarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(excluirBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        pesquisarLbl.setText("Digite a matricula desejada: ");

        pesquisarBtn.setText("Pesquisar");
        pesquisarBtn.setEnabled(false);
        pesquisarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisarBtnActionPerformed(evt);
            }
        });

        pesquisarTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisarTextFieldActionPerformed(evt);
            }
        });
        pesquisarTextField.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                pesquisarTextFieldInputMethodTextChanged(evt);
            }
        });

        javax.swing.GroupLayout panelListaLayout = new javax.swing.GroupLayout(panelLista);
        panelLista.setLayout(panelListaLayout);
        panelListaLayout.setHorizontalGroup(
            panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pesquisarLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pesquisarTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pesquisarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelListaLayout.setVerticalGroup(
            panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pesquisarLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pesquisarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pesquisarTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabelaCliente.setModel(fillTable());
        tabelaCliente.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tabelaClientePropertyChange1(evt);
                tabelaClientePropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCliente);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void pesquisarBtnActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void pesquisarTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void pesquisarTextFieldInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
        pesquisarBtn.setEnabled(true);
    }

    private void cadastrarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        CadastroCliente cadastrar = new CadastroCliente(new javax.swing.JFrame(), true);
        cadastrar.setResizable(false);
        cadastrar.setVisible(true);
        tabelaCliente.setModel(fillTable());
    }

    private void alterarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        int index = this.tabelaCliente.getSelectedRow();
        if(index < 0){
            JOptionPane.showMessageDialog(this, "Selecione uma linha!", "Erro", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        
        AlterarProfessor alterar = new AlterarProfessor(new javax.swing.JFrame(), true, index);
        alterar.setResizable(false);
        alterar.setVisible(true);
    }

    private void excluirBtnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int index = this.tabelaCliente.getSelectedRow();
            if(index < 0){
                JOptionPane.showMessageDialog(this, "Selecione uma linha!", "Erro", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            
            JOptionPane.showMessageDialog(this, "Professor excluido com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
            this.tabelaCliente.setModel(fillTable());
            this.setVisible(false);
            
            ManterProfessor.getInstance().excluir(ManterProfessor.getInstance().getProfessores_vet().get(index));
        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } 
        
    }

    private void tabelaClientePropertyChange1(java.beans.PropertyChangeEvent evt) {
        excluirBtn.setEnabled(true);
    }

    private void tabelaClientePropertyChange(java.beans.PropertyChangeEvent evt) {
        alterarBtn.setEnabled(true);
    }

    private javax.swing.JButton alterarBtn;
    private javax.swing.JButton cadastrarBtn;
    private javax.swing.JButton excluirBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelBotoes;
    private javax.swing.JPanel panelLista;
    private javax.swing.JButton pesquisarBtn;
    private javax.swing.JLabel pesquisarLbl;
    private javax.swing.JTextField pesquisarTextField;
    protected javax.swing.JTable tabelaCliente;
}
