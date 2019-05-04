/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIView;

import GUIView.Shifts.ShiftsPanel;

/**
 *
 * @author adbellas
 */
public class LoginPanel extends javax.swing.JPanel {

    private ApplicationFrame parent;
    
    /**
     * Creates new form LoginPanel
     */
    public LoginPanel() {
        initComponents();
    }

    public void setParent(ApplicationFrame applicationFrame) {
        this.parent = applicationFrame;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layoutPanel = new javax.swing.JPanel();
        loginLabel = new javax.swing.JLabel();
        employeeIDField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        employeeIDLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        loginBtn = new javax.swing.JButton();

        loginLabel.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        loginLabel.setText("Please Login Below:");

        employeeIDField.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        employeeIDField.setText("Employee ID...");

        passwordField.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        employeeIDLabel.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        employeeIDLabel.setText("Employee ID:");

        passwordLabel.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        passwordLabel.setText("Password:");

        loginBtn.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layoutPanelLayout = new javax.swing.GroupLayout(layoutPanel);
        layoutPanel.setLayout(layoutPanelLayout);
        layoutPanelLayout.setHorizontalGroup(
            layoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layoutPanelLayout.createSequentialGroup()
                .addGroup(layoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layoutPanelLayout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(loginLabel))
                    .addGroup(layoutPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(employeeIDLabel)
                            .addComponent(passwordLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passwordField)
                            .addComponent(employeeIDField, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addComponent(loginBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        layoutPanelLayout.setVerticalGroup(
            layoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layoutPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loginLabel)
                .addGap(51, 51, 51)
                .addGroup(layoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employeeIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeIDLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel))
                .addGap(18, 18, 18)
                .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layoutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layoutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        // TODO add your handling code here:
        ShiftsPanel shiftsPanel = new ShiftsPanel();
        shiftsPanel.setParent(this.parent);
        this.parent.applicationPanels.removeAll();
        this.parent.applicationPanels.add(shiftsPanel);
        this.parent.applicationPanels.repaint();
        this.parent.applicationPanels.revalidate();
    }//GEN-LAST:event_loginBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField employeeIDField;
    private javax.swing.JLabel employeeIDLabel;
    private javax.swing.JPanel layoutPanel;
    private javax.swing.JButton loginBtn;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    // End of variables declaration//GEN-END:variables
}
