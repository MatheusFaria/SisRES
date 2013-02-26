/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.mainViews;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author Parley
 */
public abstract class PatrimonioView extends JDialog {

    /**
     * Creates new form ClienteView
     */
    public PatrimonioView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    protected abstract DefaultTableModel fillTable();

    protected abstract void visualizarAction(int index);

    protected abstract void cadastrarAction();

    protected abstract void alterarAction(int index);

    protected abstract void excluirAction(int index);

    private void initComponents() {

        panelBotoes = new javax.swing.JPanel();
        cadastrar = new javax.swing.JButton();
        alterar = new javax.swing.JButton();
        excluir = new javax.swing.JButton();
        visualizarBtn = new javax.swing.JButton();
        panelLista = new javax.swing.JPanel();
        pesquisarLbl = new javax.swing.JLabel();
        pesquisarTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaPatrimonio = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Salas");

        panelBotoes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cadastrar.setText("Cadastrar");
        cadastrar.setName("Cadastrar");
        cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarActionPerformed(evt);
            }
        });

        alterar.setText("Alterar");
        alterar.setName("Alterar");
        alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarActionPerformed(evt);
            }
        });

        excluir.setText("Excluir");
        excluir.setName("Excluir");
        excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirActionPerformed(evt);
            }
        });

        visualizarBtn.setText("Visualizar Horarios");
        visualizarBtn.setName("Visualizar Horarios");
        visualizarBtn.setEnabled(true);
        visualizarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotoesLayout = new javax.swing.GroupLayout(panelBotoes);
        panelBotoes.setLayout(panelBotoesLayout);
        panelBotoesLayout.setHorizontalGroup(panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                        javax.swing.GroupLayout.Alignment.TRAILING,
                        panelBotoesLayout
                                .createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        panelBotoesLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(visualizarBtn, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(excluir, javax.swing.GroupLayout.Alignment.LEADING,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE)
                                                .addComponent(alterar, javax.swing.GroupLayout.Alignment.LEADING,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE)
                                                .addComponent(cadastrar, javax.swing.GroupLayout.Alignment.LEADING,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                                .addContainerGap()));
        panelBotoesLayout.setVerticalGroup(panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                        panelBotoesLayout
                                .createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 82,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(visualizarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81,
                                        javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(31, Short.MAX_VALUE)));

        pesquisarTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisarTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelListaLayout = new javax.swing.GroupLayout(panelLista);
        panelLista.setLayout(panelListaLayout);
        panelListaLayout.setHorizontalGroup(panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                        panelListaLayout
                                .createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pesquisarLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pesquisarTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 304,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addContainerGap()));
        panelListaLayout.setVerticalGroup(panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                panelListaLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                panelListaLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(pesquisarLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pesquisarTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        tabelaPatrimonio.setModel(fillTable());
        tabelaPatrimonio.setName("tabelaPatrimonio");
        tabelaPatrimonio.setRowSelectionAllowed(true);
        // tabelaPatrimonio.setSelectionMode();

        jScrollPane1.setViewportView(tabelaPatrimonio);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panelLista, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(
                                                layout.createSequentialGroup()
                                                        .addComponent(panelLista, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(panelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void visualizarActionPerformed(java.awt.event.ActionEvent evt) {
        int index = this.tabelaPatrimonio.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha!", "Erro", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        visualizarAction(index);
    }

    protected void pesquisarTextFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_pesquisarTextFieldActionPerformed
        String nome = this.pesquisarTextField.getText();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum texto digitado", "Erro", JOptionPane.ERROR_MESSAGE, null);
        } else {
            JOptionPane.showMessageDialog(this, "Funciona", "Teste", JOptionPane.WARNING_MESSAGE, null);
        }
    }

    private void cadastrarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cadastrarActionPerformed

        cadastrarAction();
    }

    private void alterarActionPerformed(java.awt.event.ActionEvent evt) {
        int index = this.tabelaPatrimonio.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha!", "Erro", JOptionPane.ERROR_MESSAGE, null);
            return;
        }

        alterarAction(index);
    }

    private void excluirActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_excluirActionPerformed

        int index = this.tabelaPatrimonio.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha!", "Erro", JOptionPane.ERROR_MESSAGE, null);
            return;
        }

        excluirAction(index);

    }// GEN-LAST:event_excluirActionPerformed

    protected javax.swing.JButton alterar;
    protected javax.swing.JButton cadastrar;
    protected javax.swing.JButton excluir;
    protected javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JPanel panelBotoes;
    protected javax.swing.JPanel panelLista;
    protected javax.swing.JLabel pesquisarLbl;
    protected javax.swing.JTextField pesquisarTextField;
    protected javax.swing.JTable tabelaPatrimonio;
    protected javax.swing.JButton visualizarBtn;
}
