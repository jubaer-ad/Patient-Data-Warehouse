/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientdatawarehouse;

import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author JuBaer
 */
public class UserForm extends javax.swing.JFrame {

    /**
     * Creates new form UserForm
     */
    public UserForm() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldPatientMediCodeForShowData = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButtonUserFormGO = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaUserForm = new javax.swing.JTextArea();
        jLabelUserFormImageShow = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaUserForm2 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButtonPrintUserForm = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Enter Patient Medi Code");

        jTextFieldPatientMediCodeForShowData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPatientMediCodeForShowDataActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText("User Form");

        jButtonUserFormGO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/patientdatawarehouse/icons8-more-than-16.png"))); // NOI18N
        jButtonUserFormGO.setText("GO");
        jButtonUserFormGO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUserFormGOActionPerformed(evt);
            }
        });

        jTextAreaUserForm.setColumns(20);
        jTextAreaUserForm.setRows(5);
        jScrollPane1.setViewportView(jTextAreaUserForm);

        jLabelUserFormImageShow.setBorder(new javax.swing.border.MatteBorder(null));

        jTextAreaUserForm2.setColumns(20);
        jTextAreaUserForm2.setRows(5);
        jScrollPane2.setViewportView(jTextAreaUserForm2);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel3.setText("(Plese enter your Medi code to see all value)");

        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(21, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(54, 54, 54)
                                .addComponent(jTextFieldPatientMediCodeForShowData, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jButtonUserFormGO))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabelUserFormImageShow, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addGap(27, 27, 27)))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPatientMediCodeForShowData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButtonUserFormGO)
                    .addComponent(jButton1))
                .addGap(3, 3, 3)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addComponent(jLabelUserFormImageShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 550));

        jButtonPrintUserForm.setIcon(new javax.swing.ImageIcon("F:\\patientDataWarehouse\\patientDataWarehouse\\patientDataWarehouse\\src\\patientdatawarehouse\\icon\\icons8-print-16.png")); // NOI18N
        jButtonPrintUserForm.setText("Print");
        jButtonPrintUserForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintUserFormActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonPrintUserForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 550, 133, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldPatientMediCodeForShowDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPatientMediCodeForShowDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPatientMediCodeForShowDataActionPerformed

    
    public ArrayList<Treatment> treatmentList(){
        
        Connection conn = null;
        ArrayList<Treatment> treatmentList = new ArrayList<>();
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
            if(conn!= null)
            {
                System.out.println("Connected to database successfully");
            }
            String query1 = "SELECT * from treatment";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Treatment treatment;
            while(rs.next()){
                treatment = new Treatment(rs.getInt("Treatment_id"), rs.getDate("Treatment_date"), rs.getString("Patient_Patient_medi_code"), rs.getString("Doctor_Doctor_medi_code"), rs.getString("TreatmentDiseaseSymptom"), rs.getString("TreatmentTests"), rs.getString("TreatmentSurgeries"), rs.getString("TreatmentMedicines"));
                treatmentList.add(treatment);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        return treatmentList;
    }
    
    
    
    public ArrayList<Patient> patientList(){
        
        Connection conn = null;
        ArrayList<Patient> patientlist = new ArrayList<>();
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
            if(conn!= null)
            {
                System.out.println("Connected to database successfully");
            }
            
            String query1 = "SELECT * From patient";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Patient patient;
            while(rs.next()){
                patient = new Patient(rs.getInt("Patient_id"), rs.getString("Patient_first_name"), rs.getString("Patient_last_name"), rs.getString("Patient_gender"), rs.getDate("Patient_DOB"), rs.getInt("Patient_age"), rs.getString("Patient_blood_group"), rs.getString("Patient_nid"), rs.getBytes("Patient_picture"), rs.getString("Patient_medi_code"));
                patientlist.add(patient);
                
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return patientlist;
    }
    
    
    
    
    
    
    
    
    
    
    
    private void jButtonUserFormGOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUserFormGOActionPerformed
        // TODO add your handling code here:
        
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
            if(conn!= null)
            {
                System.out.println("Connected to database successfully");
            }
            
            String query1 = "Select * from patient where Patient_medi_code=?";
            PreparedStatement pst1 = conn.prepareStatement(query1);
            pst1.setString(1, jTextFieldPatientMediCodeForShowData.getText());
            ResultSet rs1 = pst1.executeQuery();
            
            String query2 = "Select * from treatment where Patient_Patient_medi_code = ?";
            
            PreparedStatement pst2 = conn.prepareStatement(query2);
            pst2.setString(1, jTextFieldPatientMediCodeForShowData.getText());
            ResultSet rs2 = pst2.executeQuery();
            
            
            String fname;
            String lname;
            String surgeries;
            String tests;
            String medicines;
            
            if(rs1.next() ){
                byte[] img = rs1.getBytes("Patient_picture");
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(jLabelUserFormImageShow.getWidth(), jLabelUserFormImageShow.getHeight(), Image.SCALE_SMOOTH));
                jLabelUserFormImageShow.setIcon(imageIcon);
                
                fname = rs1.getString("Patient_first_name");
                lname = rs1.getString("Patient_last_name");
                
                
                jTextAreaUserForm.setText("*********************************\n");
                jTextAreaUserForm.setText(jTextAreaUserForm.getText()+"          Patient treatment History                 ");
            
                Date obj = new Date();
                String date = obj.toString();
            
                jTextAreaUserForm.setText(jTextAreaUserForm.getText()+date+"\n\n");
                jTextAreaUserForm.setText(jTextAreaUserForm.getText()+"First name:  "+fname+"\n");
                jTextAreaUserForm.setText(jTextAreaUserForm.getText()+"First name:  "+lname+"\n");
            
            }
            
            if(rs2.next()){
                surgeries = rs2.getString("TreatmentSurgeries");
                tests = rs2.getString("TreatmentTests");
                medicines = rs2.getString("TreatmentMedicines");
                
                Date obj = new Date();
                obj = rs2.getDate("Treatment_date");
                String date = obj.toString();
                
                
                jTextAreaUserForm2.setText("*********************************\n");
                jTextAreaUserForm2.setText(jTextAreaUserForm2.getText()+date+"\n\n");
                jTextAreaUserForm2.setText(jTextAreaUserForm2.getText()+"Surgeries:  "+surgeries+"\n");
                jTextAreaUserForm2.setText(jTextAreaUserForm2.getText()+"Tests:  "+tests+"\n");
                jTextAreaUserForm2.setText(jTextAreaUserForm2.getText()+"Medicines:  "+medicines+"\n");
                
                
                
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        
    }//GEN-LAST:event_jButtonUserFormGOActionPerformed

    private void jButtonPrintUserFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintUserFormActionPerformed
        // TODO add your handling code here:
        
        try{
            jTextAreaUserForm.print();
        }catch(Exception e){
            
        }
        
        
    }//GEN-LAST:event_jButtonPrintUserFormActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        HomePage homePage = new HomePage();
        homePage.setVisible(true);
        setVisible(false);
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(UserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonPrintUserForm;
    private javax.swing.JButton jButtonUserFormGO;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelUserFormImageShow;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaUserForm;
    private javax.swing.JTextArea jTextAreaUserForm2;
    private javax.swing.JTextField jTextFieldPatientMediCodeForShowData;
    // End of variables declaration//GEN-END:variables
}
