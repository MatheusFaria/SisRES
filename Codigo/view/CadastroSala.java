package view;

import exception.PatrimonioException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CadastroSala extends javax.swing.JDialog implements CadastroGeral{

    public CadastroSala(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public void initComponents() {

        codigoLbl = new javax.swing.JLabel();
        capacidadeLbl = new javax.swing.JLabel();
        descricaoLbl = new javax.swing.JLabel();
        codigoTxtField = new javax.swing.JTextField();
        capacidadeTxtField = new javax.swing.JTextField();
        cadastroBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro");
        setResizable(false);

        codigoLbl.setText("Codigo: ");

        capacidadeLbl.setText("Capacidade: ");

        descricaoLbl.setText("Descricao:");

        capacidadeTxtField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        cadastroBtn.setText("Cadastrar");
        cadastroBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastroBtnActionPerformed(evt);
            }
        });

        cancelBtn.setText("Cancelar");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(descricaoLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(capacidadeLbl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(codigoLbl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(capacidadeTxtField)
                            .addComponent(codigoTxtField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cadastroBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(capacidadeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(capacidadeTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descricaoLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cadastroBtn)
                    .addComponent(cancelBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
    
    private void cadastroBtnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            control.ManterSala.getInstance().inserir(codigoTxtField.getText(), jTextArea1.getText(), capacidadeTxtField.getText());
            
            JOptionPane.showMessageDialog(this, "Sala Cadastrada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
            this.setVisible(false);
            
        } catch (PatrimonioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getSQLState() + "\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
        
    }
    
    protected void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
    }
    
    
    protected javax.swing.JButton cadastroBtn;
    protected javax.swing.JButton cancelBtn;
    protected javax.swing.JLabel codigoLbl;
    protected javax.swing.JTextField codigoTxtField;
    protected javax.swing.JLabel descricaoLbl;
    protected javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JTextPane jTextPane1;
    protected javax.swing.JLabel capacidadeLbl;
    protected javax.swing.JTextField capacidadeTxtField;
    protected javax.swing.JTextArea jTextArea1;
}
