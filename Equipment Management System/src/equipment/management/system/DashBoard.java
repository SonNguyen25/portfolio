/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipment.management.system;


/**
 *
 * @author Bao Son
 */
public class DashBoard extends javax.swing.JFrame {

    /**
     * Creates new form DashBoard
     */
    public DashBoard() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        ImageTitle = new javax.swing.JLabel();
        TitleDashboard = new javax.swing.JLabel();
        DashboardPanel = new javax.swing.JPanel();
        EquipmentListLabel = new javax.swing.JLabel();
        ReturnEquipmentLabel = new javax.swing.JLabel();
        BorrowEquipmentLabel = new javax.swing.JLabel();
        ReturnEquipmentButton = new javax.swing.JButton();
        EquipmentListButton = new javax.swing.JButton();
        BorrowEquipmentButton = new javax.swing.JButton();
        NewEquipmentButton = new javax.swing.JButton();
        NewEquipmentLabel = new javax.swing.JLabel();
        HistoryButton = new javax.swing.JButton();
        HistoryLabel = new javax.swing.JLabel();
        DashboardMenuBar = new javax.swing.JMenuBar();
        DashboardMenu = new javax.swing.JMenu();
        ExitMenuItem = new javax.swing.JMenuItem();
        LogoutMenuItem = new javax.swing.JMenuItem();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ImageTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Graphics-Painting-icon.png"))); // NOI18N

        TitleDashboard.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        TitleDashboard.setForeground(new java.awt.Color(0, 153, 204));
        TitleDashboard.setText("Equipment Management System");
        TitleDashboard.setAlignmentX(0.5F);

        DashboardPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)), "Action", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(0, 153, 204))); // NOI18N

        EquipmentListLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        EquipmentListLabel.setText("Equipment List");

        ReturnEquipmentLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ReturnEquipmentLabel.setText("Return Equipment");

        BorrowEquipmentLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        BorrowEquipmentLabel.setText("Borrow Equipment");

        ReturnEquipmentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Return-Icon.png"))); // NOI18N
        ReturnEquipmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnEquipmentButtonActionPerformed(evt);
            }
        });

        EquipmentListButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/list_1.png"))); // NOI18N
        EquipmentListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EquipmentListButtonActionPerformed(evt);
            }
        });

        BorrowEquipmentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Borrow.png"))); // NOI18N
        BorrowEquipmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrowEquipmentButtonActionPerformed(evt);
            }
        });

        NewEquipmentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addequipment.png"))); // NOI18N
        NewEquipmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewEquipmentButtonActionPerformed(evt);
            }
        });

        NewEquipmentLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        NewEquipmentLabel.setText("New Equipment");

        HistoryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/history.png"))); // NOI18N
        HistoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HistoryButtonActionPerformed(evt);
            }
        });

        HistoryLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        HistoryLabel.setText("History");

        javax.swing.GroupLayout DashboardPanelLayout = new javax.swing.GroupLayout(DashboardPanel);
        DashboardPanel.setLayout(DashboardPanelLayout);
        DashboardPanelLayout.setHorizontalGroup(
            DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BorrowEquipmentLabel)
                    .addComponent(BorrowEquipmentButton))
                .addGap(184, 184, 184)
                .addGroup(DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ReturnEquipmentButton)
                    .addComponent(ReturnEquipmentLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
                .addGroup(DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EquipmentListButton)
                    .addGroup(DashboardPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(EquipmentListLabel)))
                .addContainerGap())
            .addGroup(DashboardPanelLayout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addGroup(DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DashboardPanelLayout.createSequentialGroup()
                        .addComponent(NewEquipmentButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(HistoryButton)
                        .addGap(195, 195, 195))
                    .addGroup(DashboardPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(NewEquipmentLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(HistoryLabel)
                        .addGap(237, 237, 237))))
        );
        DashboardPanelLayout.setVerticalGroup(
            DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ReturnEquipmentButton)
                        .addComponent(EquipmentListButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BorrowEquipmentButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BorrowEquipmentLabel)
                    .addComponent(EquipmentListLabel)
                    .addComponent(ReturnEquipmentLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DashboardPanelLayout.createSequentialGroup()
                        .addComponent(NewEquipmentButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NewEquipmentLabel))
                    .addGroup(DashboardPanelLayout.createSequentialGroup()
                        .addComponent(HistoryButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(HistoryLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        DashboardMenu.setText("File");

        ExitMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close-icon.png"))); // NOI18N
        ExitMenuItem.setText("Exit");
        ExitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitMenuItemActionPerformed(evt);
            }
        });
        DashboardMenu.add(ExitMenuItem);

        LogoutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Login-icon.png"))); // NOI18N
        LogoutMenuItem.setText("Logout");
        LogoutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutMenuItemActionPerformed(evt);
            }
        });
        DashboardMenu.add(LogoutMenuItem);

        DashboardMenuBar.add(DashboardMenu);

        setJMenuBar(DashboardMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ImageTitle)
                        .addGap(44, 44, 44)
                        .addComponent(TitleDashboard)
                        .addGap(344, 344, 344))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(DashboardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ImageTitle)
                    .addComponent(TitleDashboard))
                .addGap(18, 18, 18)
                .addComponent(DashboardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ExitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitMenuItemActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_ExitMenuItemActionPerformed

    private void LogoutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutMenuItemActionPerformed
        setVisible(false);
        Login lg = new Login();
        lg.setVisible(true);
    }//GEN-LAST:event_LogoutMenuItemActionPerformed

    private void HistoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HistoryButtonActionPerformed
        setVisible(false);
        History history = new History();
        history.setVisible(true);
        
    }//GEN-LAST:event_HistoryButtonActionPerformed

    private void NewEquipmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewEquipmentButtonActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        NewEquipment newEquipment = new NewEquipment();
        newEquipment.setVisible(true);
    }//GEN-LAST:event_NewEquipmentButtonActionPerformed

    private void BorrowEquipmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrowEquipmentButtonActionPerformed
            // TODO add your handling code here:
        setVisible(false);
        Borrow borrow = new Borrow();
        borrow.setVisible(true);
    }//GEN-LAST:event_BorrowEquipmentButtonActionPerformed

    private void ReturnEquipmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnEquipmentButtonActionPerformed
        setVisible(false);
        ReturnEquipment returnEquipment = new ReturnEquipment();
        returnEquipment.setVisible(true);
    }//GEN-LAST:event_ReturnEquipmentButtonActionPerformed

    private void EquipmentListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EquipmentListButtonActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        EquipmentList equipmentList = new EquipmentList();
        equipmentList.setVisible(true);
    }//GEN-LAST:event_EquipmentListButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashBoard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BorrowEquipmentButton;
    private javax.swing.JLabel BorrowEquipmentLabel;
    private javax.swing.JMenu DashboardMenu;
    private javax.swing.JMenuBar DashboardMenuBar;
    private javax.swing.JPanel DashboardPanel;
    private javax.swing.JButton EquipmentListButton;
    private javax.swing.JLabel EquipmentListLabel;
    private javax.swing.JMenuItem ExitMenuItem;
    private javax.swing.JButton HistoryButton;
    private javax.swing.JLabel HistoryLabel;
    private javax.swing.JLabel ImageTitle;
    private javax.swing.JMenuItem LogoutMenuItem;
    private javax.swing.JButton NewEquipmentButton;
    private javax.swing.JLabel NewEquipmentLabel;
    private javax.swing.JButton ReturnEquipmentButton;
    private javax.swing.JLabel ReturnEquipmentLabel;
    private javax.swing.JLabel TitleDashboard;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    // End of variables declaration//GEN-END:variables
}