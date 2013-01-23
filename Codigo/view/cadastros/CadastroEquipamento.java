/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cadastros;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import exception.PatrimonioException;
//import javax.swing.JLabel;
import javax.swing.JOptionPane;
import control.ManterPatrimonio;
import model.Patrimonio;

/**
 *  @author Parley
 *  @editor Aulus
 */

public class CadastroEquipamento extends CadastroPatrimonio {
    public CadastroEquipamento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    @Override
    public void initComponents(){

        codigoLbl = new javax.swing.JLabel();
        descricaoLbl = new javax.swing.JLabel();
        descricaoTxtField = new javax.swing.JTextArea(); //JTextField();
        codigoTxtField = new javax.swing.JTextField();
        cadastroBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        //jTextPane1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro");
        setResizable(false);

        codigoLbl.setText("Codigo: ");
        descricaoLbl.setText("Descricao:");
        cadastroBtn.setText("Cadastrar");
        cancelBtn.setText("Cancelar");
        jScrollPane1.setViewportView(descricaoTxtField);
        
        cadastroBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastroAction();
            }
        });

        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }

			private void cancelBtnActionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 66, Short.MAX_VALUE)
                        .addComponent(cadastroBtn)
                        .addComponent(cancelBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(codigoLbl)
                            .addComponent(descricaoLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(codigoTxtField)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                	.addComponent(descricaoLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                	.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cadastroBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    @Override
	protected void cadastroAction() {
		// TODO Auto-generated method stub	
	//}
    //private void cadastroBtnActionPerformed(java.awt.event.ActionEvent evt) {
           try {																
        	   		Patrimonio equipamento = new Patrimonio(codigoTxtField.getText(), descricaoTxtField.getText());
        	   		try {
			            	if(cadastroBtn.getText().equals("Cadastrar"))
			            	{
			                // TODO add your handling code here:
				                ManterPatrimonio.getInstance().inserir(codigoTxtField.getText(), descricaoTxtField.getText());
				                this.setVisible(false);
				                
			            	} 
			            	else
			            	if (cadastroBtn.getText().equals("Alterar"))
			            	{
			            		ManterPatrimonio.getInstance().alterar(codigoTxtField.getText(), descricaoTxtField.getText(), 
			            				ManterPatrimonio.getInstance().getPatrimonio_vet().get(index));
			                    JOptionPane.showMessageDialog(this, "Cadastro alterado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
			                    this.setVisible(false);
			            	}
            } catch (PatrimonioException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
            }
            
            JOptionPane.showMessageDialog(this, "Equipamento Cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
          //this.setVisible(true);
        } catch (PatrimonioException ex) {
           JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
        
    }//*/
    
    
 // Variables declaration - do not modify//GEN-BEGIN:variables
    protected int index = 0;
    protected javax.swing.JButton cadastroBtn;
    protected javax.swing.JButton cancelBtn;
    protected javax.swing.JLabel codigoLbl;
    protected javax.swing.JLabel descricaoLbl;
    //protected javax.swing.JTextField descricaoTxtField;
    protected javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JTextField codigoTxtField;
    protected javax.swing.JTextArea descricaoTxtField;
 // End of variables declaration//GEN-END:variables
	
}
