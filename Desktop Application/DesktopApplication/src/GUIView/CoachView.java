/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIView;

import datamodel.Coach;
import Controllers.CoachController; 
/**
 *
 * @author vcastellani
 */
public class CoachView extends javax.swing.JFrame {

    
//        int coachID;
//        String coachMake;
//        String coachModel;
//        String regPlate; 
//        boolean isActive;
        
        CoachController coachController = new CoachController();
        Coach coach = new Coach();
        
     /**
     * Creates new form CoachPage
     */
    public CoachView() {
        initComponents();
        displayCoachDetails();
    } 
    
    private void displayCoachDetails(){  
        
        coachController.getCoachData(coach);
        
//        coachMake = coach.getCoachMake();
//        coachModel = coach.getCoachModel();
//        regPlate = coach.getRegPlate();
//        isActive = coach.isIsActive();
        
        coachMakeLabel.setText(coach.getCoachMake());
        coachModelLabel.setText(coach.getCoachModel());
        regPlateLabel.setText(coach.getRegPlate());
        
        if(coach.isIsActive()){
            coachStatusLabel.setText("Active");
        }
        else{
            coachStatusLabel.setText("Inactive");
        }
        
                //TO DO
        /*Get the coach details via the coach id
        display the coach details
        when the breakdown button is pressed set isactive to false
        in db and on the labels
        by using the id to find what coach needs to be made in active
        */
              
    }
    
    public void updateCoachStatus(){
        //update the database by posting the inactive coach status back
        coachController.postCoachData(coach);
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ShiftsPgBtn = new javax.swing.JButton();
        PassengerPgBtn = new javax.swing.JButton();
        CurrentJourneyPgBtn = new javax.swing.JButton();
        CoachesPgBtn = new javax.swing.JButton();
        SignOutBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        breakdownAlertBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        coachModelLabel = new javax.swing.JLabel();
        regPlateLabel = new javax.swing.JLabel();
        coachMakeLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        coachStatusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ShiftsPgBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ShiftsPgBtn.setText("Shifts");
        ShiftsPgBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShiftsPgBtnActionPerformed(evt);
            }
        });

        PassengerPgBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        PassengerPgBtn.setText("Passengers");
        PassengerPgBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PassengerPgBtnActionPerformed(evt);
            }
        });

        CurrentJourneyPgBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        CurrentJourneyPgBtn.setText("Current Journey");
        CurrentJourneyPgBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CurrentJourneyPgBtnActionPerformed(evt);
            }
        });

        CoachesPgBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        CoachesPgBtn.setText("Coaches");

        SignOutBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        SignOutBtn.setText("Sign Out");
        SignOutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignOutBtnActionPerformed(evt);
            }
        });

        breakdownAlertBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        breakdownAlertBtn.setText("Coach Brokendown");
        breakdownAlertBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                breakdownAlertBtnActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Alert Breakdown");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Coach Details");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Model:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Make:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Reg Plate:");

        coachModelLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        coachModelLabel.setText("N/A");

        regPlateLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        regPlateLabel.setText("N/A");

        coachMakeLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        coachMakeLabel.setText("N/A");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Status:");

        coachStatusLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        coachStatusLabel.setText("N/A");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CurrentJourneyPgBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ShiftsPgBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(PassengerPgBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CoachesPgBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 220, Short.MAX_VALUE)
                        .addComponent(SignOutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel10))
                                .addGap(64, 64, 64)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(coachStatusLabel)
                                    .addComponent(coachModelLabel)
                                    .addComponent(coachMakeLabel)
                                    .addComponent(regPlateLabel))))
                        .addGap(246, 246, 246)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(breakdownAlertBtn))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PassengerPgBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ShiftsPgBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CurrentJourneyPgBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CoachesPgBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SignOutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(breakdownAlertBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(coachStatusLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(coachMakeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(coachModelLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(regPlateLabel)))))
                .addContainerGap(327, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SignOutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignOutBtnActionPerformed
        //Signout button goes back to the login page and signs to user out      
        LoginScreen loginPg = new LoginScreen();
        loginPg.setVisible(true);
        this.setVisible(false);   
    }//GEN-LAST:event_SignOutBtnActionPerformed

    private void PassengerPgBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PassengerPgBtnActionPerformed
        //Passenger button changes to passenger page
    }//GEN-LAST:event_PassengerPgBtnActionPerformed

    private void ShiftsPgBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShiftsPgBtnActionPerformed
        //Shifts button changes to shifts page
    }//GEN-LAST:event_ShiftsPgBtnActionPerformed

    private void CurrentJourneyPgBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CurrentJourneyPgBtnActionPerformed
        //Current journey button changes to journey page
    }//GEN-LAST:event_CurrentJourneyPgBtnActionPerformed

    private void breakdownAlertBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_breakdownAlertBtnActionPerformed
        //Get the coach reg plate
        System.out.println("Coach breakdown alert sent ");
        updateCoachStatus();
    }//GEN-LAST:event_breakdownAlertBtnActionPerformed


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
            java.util.logging.Logger.getLogger(CoachView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CoachView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CoachView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CoachView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CoachView().setVisible(true);
          
            }
        });
        
    }
   
   


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CoachesPgBtn;
    private javax.swing.JButton CurrentJourneyPgBtn;
    private javax.swing.JButton PassengerPgBtn;
    private javax.swing.JButton ShiftsPgBtn;
    private javax.swing.JButton SignOutBtn;
    private javax.swing.JButton breakdownAlertBtn;
    private javax.swing.JLabel coachMakeLabel;
    private javax.swing.JLabel coachModelLabel;
    private javax.swing.JLabel coachStatusLabel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel regPlateLabel;
    // End of variables declaration//GEN-END:variables
}
