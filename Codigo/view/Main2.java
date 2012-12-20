package view;


public class Main2 extends javax.swing.JFrame {

    public Main2() {
        initComponents();
    }

    private void initComponents() {

        fundoLbl = new javax.swing.JLabel();
        panelReserva1 = new javax.swing.JPanel();
        salaBtn = new javax.swing.JButton();
        equipamentoBtn = new javax.swing.JButton();
        panelReserva = new javax.swing.JPanel();
        professorBtn = new javax.swing.JButton();
        alunoBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SisRES");
        setResizable(false);

        fundoLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fundoLbl.setText("SisRES");

        panelReserva1.setBorder(javax.swing.BorderFactory.createTitledBorder("Reserva"));

        salaBtn.setText("Sala");
        salaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salaBtnActionPerformed(evt);
            }
        });

        equipamentoBtn.setText("Equipamento");
        equipamentoBtn.setEnabled(false);
        equipamentoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equipamentoBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelReserva1Layout = new javax.swing.GroupLayout(panelReserva1);
        panelReserva1.setLayout(panelReserva1Layout);
        panelReserva1Layout.setHorizontalGroup(
            panelReserva1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReserva1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(salaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(equipamentoBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelReserva1Layout.setVerticalGroup(
            panelReserva1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReserva1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelReserva1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equipamentoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelReserva.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro"));

        professorBtn.setText("Professor");
        professorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                professorBtnActionPerformed(evt);
            }
        });

        alunoBtn.setText("Aluno");
        alunoBtn.setEnabled(false);
        alunoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alunoBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelReservaLayout = new javax.swing.GroupLayout(panelReserva);
        panelReserva.setLayout(panelReservaLayout);
        panelReservaLayout.setHorizontalGroup(
            panelReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReservaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(professorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alunoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        panelReservaLayout.setVerticalGroup(
            panelReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReservaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(professorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alunoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelReserva1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelReserva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(fundoLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(185, 185, 185))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelReserva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelReserva1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(111, 111, 111)
                .addComponent(fundoLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(168, Short.MAX_VALUE))
        );

        pack();
    }

    private void salaBtnActionPerformed(java.awt.event.ActionEvent evt) {
        SalaView room = new SalaView(this,true);
        room.setResizable(false);
        room.setVisible(true);
        
    }

    private void equipamentoBtnActionPerformed(java.awt.event.ActionEvent evt) {
        EquipamentoView equipament = new EquipamentoView(this,true);
        equipament.setResizable(false);
        equipament.setVisible(true);
    }

    private void professorBtnActionPerformed(java.awt.event.ActionEvent evt) {
        ClienteView client = new ClienteView(this,true);
        client.setResizable(false);
        client.setVisible(true);
    }

    private void alunoBtnActionPerformed(java.awt.event.ActionEvent evt) {
       ClienteView client = new ClienteView(this,true);
       client.setResizable(false);
       client.setVisible(true);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main2().setVisible(true);
            }
        });
    }
    
    private javax.swing.JButton alunoBtn;
    private javax.swing.JButton equipamentoBtn;
    private javax.swing.JLabel fundoLbl;
    private javax.swing.JPanel panelReserva;
    private javax.swing.JPanel panelReserva1;
    private javax.swing.JButton professorBtn;
    private javax.swing.JButton salaBtn;
}
