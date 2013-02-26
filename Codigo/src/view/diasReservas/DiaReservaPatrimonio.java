package view.diasReservas;

/**
 * 
 * @author Parley
 */
public abstract class DiaReservaPatrimonio extends javax.swing.JDialog {
    public DiaReservaPatrimonio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    protected abstract void visualizarAction(String data);

    @SuppressWarnings("unchecked")// <editor-fold defaultstate="collapsed"
                                  // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        visualizarButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jCalendar1 = new com.toedter.calendar.JCalendar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        visualizarButton.setText("Visualizar Reservas do Dia");
        visualizarButton.setName("VisualizarButton"); // NOI18N
        visualizarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualizarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                jPanel1Layout.createSequentialGroup().addContainerGap()
                        .addComponent(jCalendar1, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE).addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                jPanel1Layout.createSequentialGroup().addContainerGap()
                        .addComponent(jCalendar1, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE).addContainerGap()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(
                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                layout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(visualizarButton, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(visualizarButton).addContainerGap()));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void visualizarButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_visualizarButtonActionPerformed
        String data;
        int mes = 1 + this.jCalendar1.getMonthChooser().getMonth();
        if (mes < 10) {
            data = this.jCalendar1.getDayChooser().getDay() + "/0" + mes + "/" + this.jCalendar1.getYearChooser().getYear();
        } else {
            data = this.jCalendar1.getDayChooser().getDay() + "/" + mes + "/" + this.jCalendar1.getYearChooser().getYear();
        }
        visualizarAction(data);
    }// GEN-LAST:event_visualizarButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JPanel jPanel1;
    protected javax.swing.JButton visualizarButton;
    // End of variables declaration//GEN-END:variables
}
