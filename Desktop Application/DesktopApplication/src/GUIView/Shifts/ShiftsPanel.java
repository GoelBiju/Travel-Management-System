/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIView.Shifts;

import GUIView.ApplicationFrame;
import GUIView.HomePanel;
import GUIView.LoginPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

/**
 *
 * @author Goel
 */
public class ShiftsPanel extends javax.swing.JPanel {

    private ApplicationFrame parent;
    /**
     * Creates new form ShiftsPanel
     */
    public ShiftsPanel() {
        initComponents();
    }
    
    public void setParent(ApplicationFrame applicationFrame) {
        this.parent = applicationFrame;
    }
    
    public void setShiftsListModel(DefaultListModel<String> model) {
        this.shiftsList.setModel(model);
    }
    
    public int getSelectedShiftIndex() {
        return this.shiftsList.getSelectedIndex();
    }
    
    public JButton getUpButton() {
        return this.upButton;
    }
    
    public JButton getDownButton() {
        return this.downButton;
    }
    
    public JButton getSignOutButton() {
        return this.signoutButton;
    }
    
    public JButton getStartShiftButton() {
        return this.startShiftButton;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        signoutButton = new javax.swing.JButton();
        shiftsLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        shiftsList = new javax.swing.JList<>();
        upButton = new javax.swing.JButton();
        downButton = new javax.swing.JButton();
        startShiftButton = new javax.swing.JButton();

        signoutButton.setText("Sign Out");
        signoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signoutButtonActionPerformed(evt);
            }
        });

        shiftsLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        shiftsLabel.setText("Your Shifts");

        jScrollPane1.setViewportView(shiftsList);

        upButton.setText("UP");
        upButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upButtonActionPerformed(evt);
            }
        });

        downButton.setText("DOWN");
        downButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downButtonActionPerformed(evt);
            }
        });

        startShiftButton.setText("Start Shift");
        startShiftButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startShiftButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(upButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(downButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(startShiftButton, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(signoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(175, 175, 175)
                        .addComponent(shiftsLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(signoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(shiftsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(upButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(downButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(startShiftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void startShiftButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startShiftButtonActionPerformed
        // TODO add your handling code here:
        
        //Logic
        
        //Switch to shift panel on the applicaiton frame
//        HomePanel homePanel = new HomePanel(); 
//        homePanel.setParent(this.parent);
//       
//        this.parent.applicationPanels.removeAll();
//        this.parent.applicationPanels.add(homePanel);
//        this.parent.applicationPanels.repaint();
//        this.parent.applicationPanels.revalidate();
        
    }//GEN-LAST:event_startShiftButtonActionPerformed

    private void signoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signoutButtonActionPerformed
        // TODO add your handling code here:
//        LoginPanel login = new LoginPanel();
//        this.parent.applicationPanels.removeAll();
//        login.setParent(this.parent);
//        this.parent.applicationPanels.add(login);
//        this.parent.applicationPanels.repaint();
//        this.parent.applicationPanels.revalidate();
    }//GEN-LAST:event_signoutButtonActionPerformed

    private void upButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upButtonActionPerformed
        // TODO add your handling code here:
        if (this.shiftsList.getSelectedIndex() != 0)
            this.shiftsList.setSelectedIndex(this.shiftsList.getSelectedIndex() - 1);
    }//GEN-LAST:event_upButtonActionPerformed

    private void downButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downButtonActionPerformed
        // TODO add your handling code here:
        if (this.shiftsList.getSelectedIndex() != (this.shiftsList.getModel().getSize() - 1))
            this.shiftsList.setSelectedIndex(this.shiftsList.getSelectedIndex() + 1);
    }//GEN-LAST:event_downButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton downButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel shiftsLabel;
    private javax.swing.JList<String> shiftsList;
    private javax.swing.JButton signoutButton;
    private javax.swing.JButton startShiftButton;
    private javax.swing.JButton upButton;
    // End of variables declaration//GEN-END:variables
}
