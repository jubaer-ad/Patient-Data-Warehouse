/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientdatawarehouse;

import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


/**
 *
 * @author JuBaer
 */
public class AdminOperations extends javax.swing.JFrame {

    /**
     * Creates new form AdminOperations
     */
    
    String gender;
    String filename = null;
    byte[] patientImage = null, doctorImage=null;
    
    java.util.Date date;
    java.sql.Date sqlDate;
    
    public AdminOperations() {
        initComponents();
        showDoctorList();
        show_treatment();
        doctorMediCombo();
        patientMediCombo();
        show_patient();
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
    
    public ArrayList<Doctor> doctorList(){
        Connection conn = null;
        ArrayList<Doctor> doctorList = new ArrayList<>();
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
            if(conn!= null)
            {
                System.out.println("Connected to database successfully");
            }
            String query1 = "SELECT * From Doctor";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Doctor doctor;
            while(rs.next()){
                doctor = new Doctor(rs.getInt("Doctor_id"), rs.getString("Doctor_first_name"), rs.getString("Doctor_last_name"), rs.getString("Doctor_gender"), rs.getDate("Doctor_DOB"), rs.getString("Doctor_speciality"), rs.getBytes("Doctor_picture"), rs.getString("Doctor_medi_code"));
                doctorList.add(doctor);
                
            }
            
        }catch(Exception e){
            System.out.println("Not connected to database.");
        }
        return doctorList;
        
    }
    
    public ArrayList<email_doctor> email_doctorList(){
        Connection conn = null;
        ArrayList<email_doctor> email_doctorList = new ArrayList<>();
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
            if(conn!= null)
            {
                System.out.println("Connected to database successfully");
            }
            String query1 = "SELECT * from email_doctor";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query1);
            email_doctor emailDoctor;
            while(rs.next()){
                emailDoctor = new email_doctor(rs.getString("Email_Doctor"), rs.getString("Doctor_Doctor_medi_code"));
                email_doctorList.add(emailDoctor);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return email_doctorList;
        
    }
    
    public ArrayList<phone_no_doctor> phone_no_doctorList(){
        Connection conn = null;
        ArrayList<phone_no_doctor> phone_no_doctorList = new ArrayList<>();
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
            if(conn!= null)
            {
                System.out.println("Connected to database successfully");
            }
            String query1 = "SELECT * from phone_no_doctor";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query1);
            phone_no_doctor phoneNoDoctor;
            while(rs.next()){
                phoneNoDoctor = new phone_no_doctor(rs.getString("Phone_no_Doctor"), rs.getString("Doctor_Doctor_medi_code"));
                phone_no_doctorList.add(phoneNoDoctor);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return phone_no_doctorList;
        
    }
    public void show_patient(){
        ArrayList<Patient> pList = patientList();
        DefaultTableModel model = (DefaultTableModel)jTableAddPatient.getModel();
        Object[] row = new Object[5];
        for(int i=0; i<pList.size(); i++){
            row[0] = pList.get(i).getpatientId();
            row[1] = pList.get(i).getpatientFirstName();
            row[2] = pList.get(i).getpatientLastName();
            row[3] = pList.get(i).getpatientNid();
            row[4] = pList.get(i).getPatientMediCode();
            model.addRow(row);
        }
        
        
    }
    
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
    
    public void show_treatment(){
        ArrayList<Treatment> treList = treatmentList();
        DefaultTableModel model = (DefaultTableModel)jTableTreatmentHistory.getModel();
        Object[] row = new Object[8];
        for(int i=0; i<treList.size(); i++){
            row[0] = treList.get(i).getTreatment_date();
            row[1] = treList.get(i).getDoctor_Doctor_medi_code();
            row[2] = treList.get(i).getPatient_Patient_medi_code();
            row[3] = treList.get(i).getTreatmentMedicines();
            row[4] = treList.get(i).getTreatmentTests();
            row[5] = treList.get(i).getTreatmentSurgeries();
            row[6] = treList.get(i).getTreatmentDiseaseSymptom();
            row[7] = treList.get(i).getTreatment_id();
            model.addRow(row);
            
        }
        
    }
    
    
    
    public void showDoctorList(){
        ArrayList<Doctor> listDoctor = doctorList();
        ArrayList<email_doctor> listEmailDoctor = email_doctorList();
        ArrayList<phone_no_doctor> listPhonenoDoctor = phone_no_doctorList();
        
        
        DefaultTableModel model = (DefaultTableModel)jTableDoctorList.getModel();
        Object[] row = new Object[6];
        for(int i=0; i<listDoctor.size(); i++){
            row[0] = listDoctor.get(i).getDoctorId();
            row[1] = listDoctor.get(i).getDoctorFirstName();
            row[2] = listDoctor.get(i).getDoctorLastName();
            row[3] = listDoctor.get(i).getDoctorSpeciality();
            row[4] = listEmailDoctor.get(i).getEmail_Doctor();
            row[5] = listPhonenoDoctor.get(i).getPhone_no_Doctor();
            model.addRow(row);
            
            
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        adminOpts = new javax.swing.JPanel();
        jButtonAdminOptAddPatient = new javax.swing.JButton();
        jButtonAdminOptAddDoctor = new javax.swing.JButton();
        jButtonAdminOptTreatmentHistory = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        parentPanel = new javax.swing.JPanel();
        jPanelPatientSignup = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButtonPatientImageChoose = new javax.swing.JButton();
        jLabelPatientImage = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldPatientFirstName = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldPatientLastName = new javax.swing.JTextField();
        jRadioButtonPatientMale = new javax.swing.JRadioButton();
        jRadioButtonPatientFemale = new javax.swing.JRadioButton();
        jTextFieldPatientAge = new javax.swing.JTextField();
        jDateChooserPatientDOB = new com.toedter.calendar.JDateChooser();
        jComboBoxPatientBloodGroup = new javax.swing.JComboBox<>();
        jTextFieldPatientNID = new javax.swing.JTextField();
        jTextFieldPatientMediCode = new javax.swing.JTextField();
        jPasswordFieldPatientPasswordSignup = new javax.swing.JPasswordField();
        jLabel14 = new javax.swing.JLabel();
        jButtonPatientSignupSignup = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldPatientPhoneNo1 = new javax.swing.JTextField();
        jTextFieldPatientPhoneNo2 = new javax.swing.JTextField();
        jTextFieldPatientPhoneNo3 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldPatientEmailAddress1 = new javax.swing.JTextField();
        jTextFieldPatientEmailAddress2 = new javax.swing.JTextField();
        jTextFieldPatientEmailAddress3 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldPatientStreet = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldPatientPostalCode = new javax.swing.JTextField();
        jTextFieldPatientCountry = new javax.swing.JTextField();
        jTextFieldPatientCity = new javax.swing.JTextField();
        jButtonAddPatientUpdate = new javax.swing.JButton();
        jButtonAddPatientDelete = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableAddPatient = new javax.swing.JTable();
        jTextFieldAddPatientID = new javax.swing.JTextField();
        jPanelUpdate = new javax.swing.JPanel();
        jPanelAddDoctor = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jTextFieldAddDoctorLastName = new javax.swing.JTextField();
        jTextFieldAddDoctorFirstName = new javax.swing.JTextField();
        jButtonDoctorPicChoose = new javax.swing.JButton();
        jTextFieldAddDoctorMediCode = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaAddDoctorSpeciality = new javax.swing.JTextArea();
        jButtonAddDoctorInsert = new javax.swing.JButton();
        jRadioButtonAddDoctorMale = new javax.swing.JRadioButton();
        jRadioButtonAddDoctorFemale = new javax.swing.JRadioButton();
        jDateChooserAddDoctorDOB = new com.toedter.calendar.JDateChooser();
        jLabelAddDoctorPicture = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jTextFieldAddDoctorPassword = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jTextFieldAddDoctorCity = new javax.swing.JTextField();
        jTextFieldAddDoctorStreet = new javax.swing.JTextField();
        jTextFieldAddDoctorCountry = new javax.swing.JTextField();
        jTextFieldAddDoctorPostalCode = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jTextFieldAddDoctorPhoneNo = new javax.swing.JTextField();
        jTextFieldAddDoctorEmail = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableDoctorList = new javax.swing.JTable();
        jTextFieldAddDoctorId = new javax.swing.JTextField();
        jPanelViewData = new javax.swing.JPanel();
        jPanelAddTreatment = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaSurgeries = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaMedicines = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaDiseaseSymptom = new javax.swing.JTextArea();
        jDateChooserTreatDate = new com.toedter.calendar.JDateChooser();
        jComboBoxDoctorMediCode = new javax.swing.JComboBox<>();
        jButtonTreatmentHistoryInsert = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaTests = new javax.swing.JTextArea();
        jLabel41 = new javax.swing.JLabel();
        jComboBoxPatientrMediCode = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableTreatmentHistory = new javax.swing.JTable();
        jButtonTreatmentHistoryUpdate = new javax.swing.JButton();
        jButtonTreatmentHistoryDelete = new javax.swing.JButton();
        jButtonTreatmentHistoryReset = new javax.swing.JButton();
        jTextFieldTreatmentHistoryID = new javax.swing.JTextField();
        jPanelDoctorList = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        adminOpts.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 0)));

        jButtonAdminOptAddPatient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/patientdatawarehouse/icon/addIcon.png"))); // NOI18N
        jButtonAdminOptAddPatient.setText("Add Patient");
        jButtonAdminOptAddPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdminOptAddPatientActionPerformed(evt);
            }
        });

        jButtonAdminOptAddDoctor.setText("Add Doctor");
        jButtonAdminOptAddDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdminOptAddDoctorActionPerformed(evt);
            }
        });

        jButtonAdminOptTreatmentHistory.setText("Treatment History");
        jButtonAdminOptTreatmentHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdminOptTreatmentHistoryActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("University Roman LET", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Admin Operations");

        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout adminOptsLayout = new javax.swing.GroupLayout(adminOpts);
        adminOpts.setLayout(adminOptsLayout);
        adminOptsLayout.setHorizontalGroup(
            adminOptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminOptsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAdminOptAddPatient)
                .addGroup(adminOptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(adminOptsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(460, 460, 460))
                    .addGroup(adminOptsLayout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(jButtonAdminOptAddDoctor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 323, Short.MAX_VALUE)
                        .addComponent(jButtonAdminOptTreatmentHistory)
                        .addGap(213, 213, 213)
                        .addComponent(jButton1)
                        .addGap(58, 58, 58))))
        );
        adminOptsLayout.setVerticalGroup(
            adminOptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adminOptsLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(adminOptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdminOptAddPatient)
                    .addComponent(jButtonAdminOptTreatmentHistory)
                    .addComponent(jButton1)
                    .addComponent(jButtonAdminOptAddDoctor)))
        );

        getContentPane().add(adminOpts, java.awt.BorderLayout.PAGE_START);

        parentPanel.setLayout(new java.awt.CardLayout());

        jLabel2.setText("First Name");

        jLabel3.setText("Last Name");

        jLabel4.setText("Gender");

        jLabel5.setText("Date of Birth");

        jLabel6.setText("Age");

        jLabel7.setText("Blood Group");

        jLabel8.setText("NID no.");

        jLabel9.setText("Picture");

        jButtonPatientImageChoose.setText("Choose");
        jButtonPatientImageChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPatientImageChooseActionPerformed(evt);
            }
        });

        jLabelPatientImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setText("Medi-Code");

        jLabel13.setText("Password");

        jTextFieldPatientLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPatientLastNameActionPerformed(evt);
            }
        });

        jRadioButtonPatientMale.setText("Male");

        jRadioButtonPatientFemale.setText("Female");

        jComboBoxPatientBloodGroup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "O+", "B+", "AB+", "A-", "O-", "B-", "AB-" }));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Add Patient");
        jLabel14.setBorder(new javax.swing.border.MatteBorder(null));

        jButtonPatientSignupSignup.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonPatientSignupSignup.setText("Signup");
        jButtonPatientSignupSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPatientSignupSignupActionPerformed(evt);
            }
        });

        jLabel10.setText("Phone No.");

        jLabel18.setText("Email Address.");

        jLabel15.setText("Country");

        jLabel16.setText("City");

        jLabel17.setText("Street");

        jLabel19.setText("Postal Code");

        jButtonAddPatientUpdate.setText("Update");
        jButtonAddPatientUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddPatientUpdateActionPerformed(evt);
            }
        });

        jButtonAddPatientDelete.setText("Delete");
        jButtonAddPatientDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddPatientDeleteActionPerformed(evt);
            }
        });

        jTableAddPatient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient ID", "First Name", "Last Name", "NID No.", "Medi Code"
            }
        ));
        jTableAddPatient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAddPatientMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTableAddPatient);

        javax.swing.GroupLayout jPanelPatientSignupLayout = new javax.swing.GroupLayout(jPanelPatientSignup);
        jPanelPatientSignup.setLayout(jPanelPatientSignupLayout);
        jPanelPatientSignupLayout.setHorizontalGroup(
            jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                .addGap(461, 461, 461)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPatientSignupLayout.createSequentialGroup()
                .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                        .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                                        .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2))
                                        .addGap(47, 47, 47)
                                        .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextFieldPatientLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldPatientFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jDateChooserPatientDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPatientSignupLayout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(36, 36, 36)
                                            .addComponent(jTextFieldPatientAge, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPatientSignupLayout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addGap(36, 36, 36)
                                            .addComponent(jComboBoxPatientBloodGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPatientSignupLayout.createSequentialGroup()
                                            .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel11)
                                                .addComponent(jLabel13))
                                            .addGap(31, 31, 31)
                                            .addComponent(jPasswordFieldPatientPasswordSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(4, 4, 4)))
                                    .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                                                .addGap(86, 86, 86)
                                                .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jTextFieldPatientPhoneNo3, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jTextFieldPatientPhoneNo2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addGap(36, 36, 36)
                                                .addComponent(jTextFieldPatientPhoneNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                                            .addGap(17, 17, 17)
                                            .addComponent(jLabel8)
                                            .addGap(36, 36, 36)
                                            .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jTextFieldPatientMediCode)
                                                .addComponent(jTextFieldPatientNID, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addComponent(jRadioButtonPatientMale)
                                .addGap(48, 48, 48)
                                .addComponent(jRadioButtonPatientFemale)
                                .addGap(102, 102, 102)))
                        .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel9)
                                .addGap(38, 38, 38)
                                .addComponent(jButtonPatientImageChoose)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelPatientImage, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                                        .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                                                .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel16)
                                                    .addComponent(jLabel15))
                                                .addGap(70, 70, 70)
                                                .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jTextFieldPatientCountry, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                                    .addComponent(jTextFieldPatientCity)))
                                            .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(49, 49, 49)
                                                .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jTextFieldPatientEmailAddress1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                                    .addComponent(jTextFieldPatientEmailAddress2)
                                                    .addComponent(jTextFieldPatientEmailAddress3))))
                                        .addGap(60, 60, 60)
                                        .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel19)))
                                    .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                                        .addComponent(jTextFieldAddPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButtonAddPatientDelete)
                                        .addGap(86, 86, 86)
                                        .addComponent(jButtonAddPatientUpdate)
                                        .addGap(79, 79, 79)
                                        .addComponent(jButtonPatientSignupSignup)))
                                .addGap(70, 70, 70)
                                .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldPatientStreet)
                                    .addComponent(jTextFieldPatientPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(72, 72, 72))
        );
        jPanelPatientSignupLayout.setVerticalGroup(
            jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldPatientFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldPatientLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jRadioButtonPatientMale)
                                .addComponent(jLabel4))
                            .addComponent(jRadioButtonPatientFemale))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserPatientDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(23, 23, 23)
                        .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldPatientAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel7))
                            .addComponent(jComboBoxPatientBloodGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jPasswordFieldPatientPasswordSignup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTextFieldPatientMediCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextFieldPatientNID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTextFieldPatientPhoneNo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addComponent(jTextFieldPatientPhoneNo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldPatientPhoneNo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                        .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(jButtonPatientImageChoose))
                                    .addComponent(jLabelPatientImage, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(jTextFieldPatientEmailAddress1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldPatientEmailAddress2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldPatientEmailAddress3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel16))
                                    .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                                        .addComponent(jTextFieldPatientCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldPatientCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                                .addGap(191, 191, 191)
                                .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addGroup(jPanelPatientSignupLayout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jTextFieldPatientStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(20, 20, 20)
                                .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldPatientPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelPatientSignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonPatientSignupSignup)
                            .addComponent(jButtonAddPatientUpdate)
                            .addComponent(jButtonAddPatientDelete)
                            .addComponent(jTextFieldAddPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(208, 208, 208))
        );

        parentPanel.add(jPanelPatientSignup, "card5");

        javax.swing.GroupLayout jPanelUpdateLayout = new javax.swing.GroupLayout(jPanelUpdate);
        jPanelUpdate.setLayout(jPanelUpdateLayout);
        jPanelUpdateLayout.setHorizontalGroup(
            jPanelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1271, Short.MAX_VALUE)
        );
        jPanelUpdateLayout.setVerticalGroup(
            jPanelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 869, Short.MAX_VALUE)
        );

        parentPanel.add(jPanelUpdate, "card6");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Add Doctor");

        jLabel27.setText("First Name");

        jLabel28.setText("Last Name");

        jLabel29.setText("Gender");

        jLabel30.setText("DOB");

        jLabel31.setText("Speciality");

        jLabel32.setText("Image");

        jLabel33.setText("Medi-Code");

        jButtonDoctorPicChoose.setText("Choose");
        jButtonDoctorPicChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDoctorPicChooseActionPerformed(evt);
            }
        });

        jTextAreaAddDoctorSpeciality.setColumns(20);
        jTextAreaAddDoctorSpeciality.setRows(5);
        jScrollPane5.setViewportView(jTextAreaAddDoctorSpeciality);

        jButtonAddDoctorInsert.setText("Insert");
        jButtonAddDoctorInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddDoctorInsertActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonAddDoctorMale);
        jRadioButtonAddDoctorMale.setText("Male");

        buttonGroup1.add(jRadioButtonAddDoctorFemale);
        jRadioButtonAddDoctorFemale.setText("Female");

        jLabelAddDoctorPicture.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelAddDoctorPicture.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));

        jLabel34.setText("Password");

        jLabel35.setText("City");

        jLabel36.setText("Street");

        jLabel37.setText("Country");

        jLabel38.setText("Postal Code");

        jLabel39.setText("Phone no.");

        jLabel40.setText("Email");

        jTableDoctorList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Doctor ID", "First Name", "Last Name", "Speciality", "Email", "Phone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDoctorList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDoctorListMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTableDoctorList);

        javax.swing.GroupLayout jPanelAddDoctorLayout = new javax.swing.GroupLayout(jPanelAddDoctor);
        jPanelAddDoctor.setLayout(jPanelAddDoctorLayout);
        jPanelAddDoctorLayout.setHorizontalGroup(
            jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel35)))
                        .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                            .addComponent(jLabel36)
                            .addGap(3, 3, 3))
                        .addComponent(jLabel28)
                        .addComponent(jLabel30))
                    .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel37)))
                .addGap(56, 56, 56)
                .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel38))
                    .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                        .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelAddDoctorLayout.createSequentialGroup()
                                    .addGap(323, 323, 323)
                                    .addComponent(jLabel29))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelAddDoctorLayout.createSequentialGroup()
                                    .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jTextFieldAddDoctorStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextFieldAddDoctorCity, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                            .addComponent(jTextFieldAddDoctorLastName, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldAddDoctorFirstName, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addComponent(jTextFieldAddDoctorCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                                                .addGap(134, 134, 134)
                                                .addComponent(jLabel34))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAddDoctorLayout.createSequentialGroup()
                                                .addGap(156, 156, 156)
                                                .addComponent(jLabel40)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAddDoctorLayout.createSequentialGroup()
                                            .addGap(131, 131, 131)
                                            .addComponent(jLabel39))))
                                .addComponent(jLabel33))
                            .addComponent(jDateChooserAddDoctorDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 65, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                        .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                                .addComponent(jTextFieldAddDoctorMediCode, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                                .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldAddDoctorPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                    .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextFieldAddDoctorEmail)
                                        .addComponent(jTextFieldAddDoctorPostalCode)
                                        .addComponent(jTextFieldAddDoctorPhoneNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(31, 31, 31)))
                        .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(jLabel31))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 48, Short.MAX_VALUE)
                        .addComponent(jButtonAddDoctorInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jTextFieldAddDoctorId, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))
                    .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                        .addComponent(jRadioButtonAddDoctorMale)
                        .addGap(29, 29, 29)
                        .addComponent(jRadioButtonAddDoctorFemale)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel32)
                        .addGap(35, 35, 35)
                        .addComponent(jLabelAddDoctorPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(jButtonDoctorPicChoose)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                .addGap(433, 433, 433)
                .addComponent(jLabel26)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1261, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanelAddDoctorLayout.setVerticalGroup(
            jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addGap(53, 53, 53)
                .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAddDoctorPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDoctorPicChoose)
                    .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                        .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jTextFieldAddDoctorFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldAddDoctorLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28)))
                    .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(jRadioButtonAddDoctorFemale)
                        .addComponent(jRadioButtonAddDoctorMale)
                        .addComponent(jLabel29)))
                .addGap(1, 1, 1)
                .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(3, 3, 3)
                        .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                                .addComponent(jTextFieldAddDoctorId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonAddDoctorInsert))
                            .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel31))))
                    .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                        .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel33)
                                .addComponent(jTextFieldAddDoctorMediCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDateChooserAddDoctorDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldAddDoctorCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel35))
                                .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextFieldAddDoctorPhoneNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel39)))
                                    .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextFieldAddDoctorStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel36))))
                                .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextFieldAddDoctorPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel38)))
                                    .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextFieldAddDoctorCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel37)))))
                            .addGroup(jPanelAddDoctorLayout.createSequentialGroup()
                                .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34)
                                    .addComponent(jTextFieldAddDoctorPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17)
                                .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel40)
                                    .addComponent(jTextFieldAddDoctorEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(499, Short.MAX_VALUE))
            .addGroup(jPanelAddDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAddDoctorLayout.createSequentialGroup()
                    .addGap(0, 621, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        parentPanel.add(jPanelAddDoctor, "card2");

        javax.swing.GroupLayout jPanelViewDataLayout = new javax.swing.GroupLayout(jPanelViewData);
        jPanelViewData.setLayout(jPanelViewDataLayout);
        jPanelViewDataLayout.setHorizontalGroup(
            jPanelViewDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1271, Short.MAX_VALUE)
        );
        jPanelViewDataLayout.setVerticalGroup(
            jPanelViewDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 869, Short.MAX_VALUE)
        );

        parentPanel.add(jPanelViewData, "card4");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Treatment History");

        jLabel20.setText("Treatment Date");

        jLabel21.setText("Doctor Medi-Code");

        jLabel22.setText("Medicines");

        jLabel23.setText("Tests");

        jLabel24.setText("Surgeries");

        jTextAreaSurgeries.setColumns(20);
        jTextAreaSurgeries.setRows(5);
        jScrollPane1.setViewportView(jTextAreaSurgeries);

        jTextAreaMedicines.setColumns(20);
        jTextAreaMedicines.setRows(5);
        jScrollPane2.setViewportView(jTextAreaMedicines);

        jTextAreaDiseaseSymptom.setColumns(20);
        jTextAreaDiseaseSymptom.setRows(5);
        jScrollPane3.setViewportView(jTextAreaDiseaseSymptom);

        jButtonTreatmentHistoryInsert.setText("Insert");
        jButtonTreatmentHistoryInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTreatmentHistoryInsertActionPerformed(evt);
            }
        });

        jLabel25.setText("Disease Symptom");

        jTextAreaTests.setColumns(20);
        jTextAreaTests.setRows(5);
        jScrollPane4.setViewportView(jTextAreaTests);

        jLabel41.setText("Patient Medi-Code");

        jTableTreatmentHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Treatment Date", "Doctor Medi-Code", "Patient Medi-Code", "Medicines", "Tests", "Surgeries", "Disease Symptom", "ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableTreatmentHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTreatmentHistoryMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTableTreatmentHistory);

        jButtonTreatmentHistoryUpdate.setText("Update");
        jButtonTreatmentHistoryUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTreatmentHistoryUpdateActionPerformed(evt);
            }
        });

        jButtonTreatmentHistoryDelete.setText("Delete");
        jButtonTreatmentHistoryDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTreatmentHistoryDeleteActionPerformed(evt);
            }
        });

        jButtonTreatmentHistoryReset.setText("Reset");
        jButtonTreatmentHistoryReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTreatmentHistoryResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAddTreatmentLayout = new javax.swing.GroupLayout(jPanelAddTreatment);
        jPanelAddTreatment.setLayout(jPanelAddTreatmentLayout);
        jPanelAddTreatmentLayout.setHorizontalGroup(
            jPanelAddTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAddTreatmentLayout.createSequentialGroup()
                .addGroup(jPanelAddTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelAddTreatmentLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanelAddTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelAddTreatmentLayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelAddTreatmentLayout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(108, 108, 108)
                        .addGroup(jPanelAddTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addGap(56, 56, 56)
                        .addGroup(jPanelAddTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelAddTreatmentLayout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanelAddTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButtonTreatmentHistoryReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonTreatmentHistoryUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonTreatmentHistoryInsert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonTreatmentHistoryDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(26, 26, 26)
                                .addComponent(jTextFieldTreatmentHistoryID, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 162, Short.MAX_VALUE))
                    .addGroup(jPanelAddTreatmentLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxDoctorMediCode, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel41)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxPatientrMediCode, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(146, 146, 146))
            .addGroup(jPanelAddTreatmentLayout.createSequentialGroup()
                .addGroup(jPanelAddTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAddTreatmentLayout.createSequentialGroup()
                        .addGap(398, 398, 398)
                        .addComponent(jLabel20)
                        .addGap(27, 27, 27)
                        .addComponent(jDateChooserTreatDate, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelAddTreatmentLayout.createSequentialGroup()
                        .addGap(489, 489, 489)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelAddTreatmentLayout.setVerticalGroup(
            jPanelAddTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAddTreatmentLayout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelAddTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelAddTreatmentLayout.createSequentialGroup()
                        .addGroup(jPanelAddTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20)
                            .addComponent(jDateChooserTreatDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(182, 182, 182)
                        .addGroup(jPanelAddTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonTreatmentHistoryReset)
                            .addComponent(jTextFieldTreatmentHistoryID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonTreatmentHistoryDelete)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonTreatmentHistoryUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonTreatmentHistoryInsert))
                    .addGroup(jPanelAddTreatmentLayout.createSequentialGroup()
                        .addGroup(jPanelAddTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jComboBoxDoctorMediCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41)
                            .addComponent(jComboBoxPatientrMediCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanelAddTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanelAddTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        parentPanel.add(jPanelAddTreatment, "card7");

        javax.swing.GroupLayout jPanelDoctorListLayout = new javax.swing.GroupLayout(jPanelDoctorList);
        jPanelDoctorList.setLayout(jPanelDoctorListLayout);
        jPanelDoctorListLayout.setHorizontalGroup(
            jPanelDoctorListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1271, Short.MAX_VALUE)
        );
        jPanelDoctorListLayout.setVerticalGroup(
            jPanelDoctorListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 869, Short.MAX_VALUE)
        );

        parentPanel.add(jPanelDoctorList, "card3");

        getContentPane().add(parentPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void connection(){
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
            if(conn!= null)
            {
                System.out.println("Connected to database successfully");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }  
    
    public void executeSQLQuery(String query, String message){
        
        Connection conn = null;
        Statement st;
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
            if(conn!= null)
            {
                System.out.println("Connected to database successfully");
            }
            st = conn.createStatement();
            if((st.executeUpdate(query)) == 1){
                JOptionPane.showMessageDialog(null, "Data"+message+"Succesfully");
            }
            else{
                JOptionPane.showMessageDialog(null, "Data not"+message);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        
    }
    
    private void jButtonPatientImageChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPatientImageChooseActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        filename = file.getAbsolutePath();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(jLabelPatientImage.getWidth(), jLabelPatientImage.getHeight(), Image.SCALE_SMOOTH));
        jLabelPatientImage.setIcon(imageIcon);
        try{
            File image =new File(filename);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] Byte = new byte[1024];
            for(int readNum;(readNum = fis.read(Byte)) != -1; ){
                baos.write(Byte, 0, readNum);

            }
            patientImage = baos.toByteArray();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonPatientImageChooseActionPerformed

    private void jTextFieldPatientLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPatientLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPatientLastNameActionPerformed

    private void jButtonPatientSignupSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPatientSignupSignupActionPerformed
        Connection conn = null;

        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");

            String query1 = "insert into patient(Patient_first_name, Patient_last_name, Patient_gender, Patient_DOB, Patient_age, Patient_blood_group, Patient_nid, Patient_picture, Patient_medi_code)values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst1 = conn.prepareStatement(query1);
            pst1.setString(1, jTextFieldPatientFirstName.getText());
            pst1.setString(2, jTextFieldPatientLastName.getText());
            if(jRadioButtonPatientMale.isSelected()){
                gender = "Male";
            }
            if(jRadioButtonPatientFemale.isSelected()){
                gender = "Female";
            }
            pst1.setString(3, gender);
            
            //code to convert jDate to my sqlDate
            date = jDateChooserPatientDOB.getDate();
            sqlDate = new java.sql.Date(date.getTime());
            //end code to convert jDate to my sqlDate
            
            pst1.setDate(4, sqlDate);
            pst1.setInt(5, Integer.parseInt(jTextFieldPatientAge.getText()));
            String bloodGroup;
            bloodGroup = jComboBoxPatientBloodGroup.getSelectedItem().toString();
            pst1.setString(6, bloodGroup);
            pst1.setString(7, jTextFieldPatientNID.getText());
            pst1.setBytes(8, patientImage);
            pst1.setString(9, jTextFieldPatientMediCode.getText());

            pst1.executeUpdate();

            String query2 = "insert into patient_address(Patient_address_city, Patient_address_street, Patient_address_country, Patient_address_zip_code, Patient_Patient_medi_code)values(?, ?, ?, ?, ?)";
            PreparedStatement pst2 = conn.prepareStatement(query2);
            pst2.setString(1, jTextFieldPatientCity.getText());
            pst2.setString(2, jTextFieldPatientStreet.getText());
            pst2.setString(3, jTextFieldPatientCountry.getText());
            pst2.setString(4, jTextFieldPatientPostalCode.getText());
            pst2.setString(5, jTextFieldPatientMediCode.getText());

            pst2.executeUpdate();

            String query3 = "insert into email_patient(Email_Patient, Patient_Patient_medi_code)values(?, ?)";
            PreparedStatement pst3 = conn.prepareStatement(query3);
            PreparedStatement pst31 = conn.prepareStatement(query3);
            PreparedStatement pst32 = conn.prepareStatement(query3);
            pst3.setString(1, jTextFieldPatientEmailAddress1.getText());
            pst3.setString(2, jTextFieldPatientMediCode.getText());
            if(jTextFieldPatientEmailAddress2.getText() != ""){
                pst31.setString(1, jTextFieldPatientEmailAddress2.getText());
                pst31.setString(2, jTextFieldPatientMediCode.getText());
            }
            if(jTextFieldPatientEmailAddress3.getText() != ""){
                pst32.setString(1, jTextFieldPatientEmailAddress3.getText());
                pst32.setString(2, jTextFieldPatientMediCode.getText());
            }

            pst3.executeUpdate();
            pst31.executeUpdate();
            pst32.executeUpdate();

            String query4 = "insert into phone_no_patient(Phone_no_Patient, Patient_Patient_medi_code)values(?, ?)";
            PreparedStatement pst4 = conn.prepareStatement(query4);
            PreparedStatement pst41 = conn.prepareStatement(query4);
            PreparedStatement pst42 = conn.prepareStatement(query4);
            pst4.setString(1, jTextFieldPatientPhoneNo1.getText());
            pst4.setString(2, jTextFieldPatientMediCode.getText());
            if(jTextFieldPatientPhoneNo2.getText() != null){

                pst41.setString(1, jTextFieldPatientPhoneNo2.getText());
                pst41.setString(2, jTextFieldPatientMediCode.getText());
            }
            if(jTextFieldPatientPhoneNo3.getText() != null){

                pst42.setString(1, jTextFieldPatientPhoneNo3.getText());
                pst42.setString(2, jTextFieldPatientMediCode.getText());
            }

            pst4.executeUpdate();
            pst41.executeUpdate();
            pst42.executeUpdate();

            String query5 = "insert into user(User_username, User_password)values(?, ?)";
            PreparedStatement pst5 = conn.prepareStatement(query5);
            pst5.setString(1, jTextFieldPatientMediCode.getText());
            pst5.setString(2, jPasswordFieldPatientPasswordSignup.getText());

            pst5.executeUpdate();
            
            //code to retrieve last Patient ID
            Statement st = conn.createStatement();
            String sql = ("SELECT * FROM patient ORDER BY Patient_id DESC LIMIT 1");
            ResultSet rs = st.executeQuery(sql);
            int pId = 0;
            if(rs.next()){
                pId =rs.getInt("Patient_id");
            }
            System.out.println(pId);
            //end code to retrieve last Patient ID
            

            if(conn!= null)
            {
                System.out.println("Connected to database successfully");
            }
            
            
            DefaultTableModel model = (DefaultTableModel)jTableAddPatient.getModel();
            model.setRowCount(0);
            show_patient();
            
            JOptionPane.showMessageDialog(null, "Updated");
            
            
            retrievePatientApply();
            
            parentPanel.removeAll();
            parentPanel.add(jPanelAddTreatment);
            parentPanel.repaint();
            parentPanel.revalidate();
            
            
             
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_jButtonPatientSignupSignupActionPerformed

    private void jButtonAdminOptAddPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdminOptAddPatientActionPerformed
        // TODO add your handling code here:
        parentPanel.removeAll();
        parentPanel.add(jPanelPatientSignup);
        parentPanel.repaint();
        parentPanel.revalidate();
    }//GEN-LAST:event_jButtonAdminOptAddPatientActionPerformed

    private void jButtonTreatmentHistoryInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTreatmentHistoryInsertActionPerformed
        // TODO add your handling code here:
        date = jDateChooserTreatDate.getDate();
        sqlDate = new java.sql.Date(date.getTime());
        
        String query1 = "insert into treatment(Treatment_date, Patient_Patient_medi_code, Doctor_Doctor_medi_code, TreatmentDiseaseSymptom, TreatmentTests, TreatmentSurgeries, TreatmentMedicines)values(?, ?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
            if(conn!= null)
            {
                System.out.println("Connected to database successfully");
            }
            
            Statement st = conn.createStatement();
            
            PreparedStatement pst = conn.prepareStatement(query1);
            
            String value1 = jComboBoxPatientrMediCode.getSelectedItem().toString();
            
            pst.setDate(1, sqlDate);
            pst.setString(2, value1);
            
            String value2 = jComboBoxDoctorMediCode.getSelectedItem().toString();
            
            pst.setString(3, value2);
            pst.setString(4, jTextAreaDiseaseSymptom.getText());
            pst.setString(5, jTextAreaTests.getText()); 
            pst.setString(6, jTextAreaSurgeries.getText());
            pst.setString(7, jTextAreaMedicines.getText());
            pst.executeUpdate();   
            DefaultTableModel model = (DefaultTableModel)jTableTreatmentHistory.getModel();
            model.setRowCount(0);
            show_treatment();
            
            JOptionPane.showMessageDialog(null, "Inserted");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        
        
        
    }//GEN-LAST:event_jButtonTreatmentHistoryInsertActionPerformed

    
    
    
    
    
    private void jButtonAddDoctorInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddDoctorInsertActionPerformed
        // TODO add your handling code here:
        
        Connection conn = null;
        
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
            if(conn!= null)
            {
                System.out.println("Connected to database successfully");
            }
            
            String query1 = "insert into doctor(Doctor_first_name, Doctor_last_name, Doctor_gender, Doctor_DOB, Doctor_speciality, Doctor_picture, Doctor_medi_code)values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst1 = conn.prepareStatement(query1);
            pst1.setString(1, jTextFieldAddDoctorFirstName.getText());
            pst1.setString(2, jTextFieldAddDoctorLastName.getText());
            if(jRadioButtonAddDoctorMale.isSelected()){
                gender = "Male";
            }
            if(jRadioButtonAddDoctorFemale.isSelected()){
                gender = "Female";
            }
            pst1.setString(3, gender);
            date = jDateChooserAddDoctorDOB.getDate();
            sqlDate = new java.sql.Date(date.getTime());
            pst1.setDate(4, sqlDate);
            pst1.setString(5, jTextAreaAddDoctorSpeciality.getText());
            pst1.setBytes(6, doctorImage);
            pst1.setString(7, jTextFieldAddDoctorMediCode.getText());
            pst1.executeUpdate();
            
            
            
            String query2 = "insert into user(User_username, User_password)values(?, ?)";
            PreparedStatement pst2 = conn.prepareStatement(query2);
            pst2.setString(1, jTextFieldAddDoctorMediCode.getText());
            pst2.setString(2, jTextFieldAddDoctorPassword.getText());
            pst2.executeUpdate();
            
            
            String query3 = "insert into doctor_address(Doctor_address_city, Doctor_address_street, Doctor_address_country, Doctor_address_zip_code, Doctor_Doctor_medi_code)values(?, ?, ?, ?, ?)";
            PreparedStatement pst3 = conn.prepareStatement(query3);
            pst3.setString(1, jTextFieldAddDoctorCity.getText());  
            pst3.setString(2, jTextFieldAddDoctorStreet.getText());
            //pst3.setString(1, .getText());
            pst3.setString(3, jTextFieldAddDoctorCountry.getText());
            pst3.setString(4, jTextFieldAddDoctorPostalCode.getText());
            pst3.setString(5, jTextFieldAddDoctorMediCode.getText());
            pst3.executeUpdate();
            
            String query4 = "insert into phone_no_doctor(Phone_no_Doctor, Doctor_Doctor_medi_code)values(?, ?)";
            PreparedStatement pst4 = conn.prepareStatement(query4);
            pst4.setString(1, jTextFieldAddDoctorPhoneNo.getText());
            pst4.setString(2, jTextFieldAddDoctorMediCode.getText());
            pst4.executeUpdate();
            
            
            
            String query5 = "insert into email_doctor(Email_Doctor, Doctor_Doctor_medi_code)values(?, ?)";
            PreparedStatement pst5 = conn.prepareStatement(query5);
            pst5.setString(1, jTextFieldAddDoctorEmail.getText());
            pst5.setString(2, jTextFieldAddDoctorMediCode.getText());
            pst5.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)jTableDoctorList.getModel();
            
            model.setRowCount(0);
            doctorList();
            phone_no_doctorList();
            email_doctorList();
            
            retrieveDoctorApply();
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        
    }//GEN-LAST:event_jButtonAddDoctorInsertActionPerformed

    private void jButtonDoctorPicChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDoctorPicChooseActionPerformed
        // TODO add your handling code here:
        
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        filename = file.getAbsolutePath();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(jLabelAddDoctorPicture.getWidth(), jLabelAddDoctorPicture.getHeight(), Image.SCALE_SMOOTH));
        jLabelAddDoctorPicture.setIcon(imageIcon);
        try{
            File image =new File(filename);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] Byte = new byte[1024];
            for(int readNum;(readNum = fis.read(Byte)) != -1; ){
                baos.write(Byte, 0, readNum);

            }
            doctorImage = baos.toByteArray();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_jButtonDoctorPicChooseActionPerformed

    private void jButtonAdminOptAddDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdminOptAddDoctorActionPerformed
        // TODO add your handling code here:
        
        parentPanel.removeAll();
        parentPanel.add(jPanelAddDoctor);
        parentPanel.repaint();
        parentPanel.revalidate();
        
    }//GEN-LAST:event_jButtonAdminOptAddDoctorActionPerformed

    private void jButtonAdminOptTreatmentHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdminOptTreatmentHistoryActionPerformed
        // TODO add your handling code here:
        
        parentPanel.removeAll();
        parentPanel.add(jPanelAddTreatment);
        parentPanel.repaint();
        parentPanel.revalidate();
        
    }//GEN-LAST:event_jButtonAdminOptTreatmentHistoryActionPerformed

    private void jTableTreatmentHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTreatmentHistoryMouseClicked
       
            // TODO add your handling code here:
            
            int i = jTableTreatmentHistory.getSelectedRow();
            TableModel model = jTableTreatmentHistory.getModel();
            jTextFieldTreatmentHistoryID.setText(model.getValueAt(i, 7).toString());
            
            
        
        
        
    }//GEN-LAST:event_jTableTreatmentHistoryMouseClicked

    private void jTableDoctorListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDoctorListMouseClicked
        // TODO add your handling code here:
        int i = jTableDoctorList.getSelectedRow();
        TableModel model = jTableDoctorList.getModel();
        jTextFieldAddDoctorId.setText(model.getValueAt(i, 0).toString());
        jTextFieldAddDoctorFirstName.setText(model.getValueAt(i, 1).toString());
        jTextFieldAddDoctorLastName.setText(model.getValueAt(i, 2).toString());
        jTextAreaAddDoctorSpeciality.setText(model.getValueAt(i, 3).toString());
        jTextFieldAddDoctorEmail.setText(model.getValueAt(i, 4).toString());
        jTextFieldAddDoctorPhoneNo.setText(model.getValueAt(i, 5).toString());
        
        
    }//GEN-LAST:event_jTableDoctorListMouseClicked

    private void jButtonTreatmentHistoryUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTreatmentHistoryUpdateActionPerformed
        // TODO add your handling code here:
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
            if(conn!= null)
            {
                System.out.println("Connected to database successfully");
            }
            int i = jTableTreatmentHistory.getSelectedRow();
            String value = jTextFieldTreatmentHistoryID.getText();
            String query = "UPDATE treatment SET Treatment_date=?, Patient_Patient_medi_code=?, Doctor_Doctor_medi_code=?, TreatmentDiseaseSymptom=?, TreatmentTests=?, TreatmentSurgeries=?, TreatmentMedicines=? WHERE Treatment_id="+value;
            PreparedStatement pst = conn.prepareStatement(query);
            
            String value1 = jComboBoxPatientrMediCode.getSelectedItem().toString();
            
            pst.setDate(1, sqlDate);
            pst.setString(2, value1);
            
            String value2 = jComboBoxDoctorMediCode.getSelectedItem().toString();
            
            pst.setString(3, value2);
            pst.setString(4, jTextAreaDiseaseSymptom.getText());
            pst.setString(5, jTextAreaTests.getText()); 
            pst.setString(6, jTextAreaSurgeries.getText());
            pst.setString(7, jTextAreaMedicines.getText());
            pst.executeUpdate();   
            DefaultTableModel model = (DefaultTableModel)jTableTreatmentHistory.getModel();
            model.setRowCount(0);
            show_treatment();
            JOptionPane.showMessageDialog(null, "Updated");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        
        
    }//GEN-LAST:event_jButtonTreatmentHistoryUpdateActionPerformed

    private void jButtonTreatmentHistoryDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTreatmentHistoryDeleteActionPerformed
        // TODO add your handling code here:
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
            if(conn!= null)
            {
                System.out.println("Connected to database successfully");
            }
            
            String value = jTextFieldTreatmentHistoryID.getText();
            String query = "DELETE FROM treatment WHERE Treatment_id = "+value;
        
            PreparedStatement pst = conn.prepareStatement(query);
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)jTableTreatmentHistory.getModel();
            model.setRowCount(0);
            show_treatment();
            JOptionPane.showMessageDialog(null, "Deleted");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_jButtonTreatmentHistoryDeleteActionPerformed

    private void jButtonAddPatientUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddPatientUpdateActionPerformed
        // TODO add your handling code here:
        
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
            if(conn!= null)
            {
                System.out.println("Connected to database successfully");
            }
            String value = jTextFieldAddPatientID.getText();
            String query = "UPDATE patient SET Patient_first_name=?, Patient_last_name=?, Patient_gender=?, Patient_DOB=?, Patient_age=?, Patient_blood_group=?, Patient_nid=?, Patient_picture=?, Patient_medi_code=? WHERE Patient_id="+value;
            PreparedStatement pst1 = conn.prepareStatement(query);
            pst1.setString(1, jTextFieldPatientFirstName.getText());
            pst1.setString(2, jTextFieldPatientLastName.getText());
            if(jRadioButtonPatientMale.isSelected()){
                gender = "Male";
            }
            if(jRadioButtonPatientFemale.isSelected()){
                gender = "Female";
            }
            pst1.setString(3, gender);
            
            //code to convert jDate to my sqlDate
            date = jDateChooserPatientDOB.getDate();
            sqlDate = new java.sql.Date(date.getTime());
            //end code to convert jDate to my sqlDate
            
            pst1.setDate(4, sqlDate);
            pst1.setInt(5, Integer.parseInt(jTextFieldPatientAge.getText()));
            String bloodGroup;
            bloodGroup = jComboBoxPatientBloodGroup.getSelectedItem().toString();
            pst1.setString(6, bloodGroup);
            pst1.setString(7, jTextFieldPatientNID.getText());
            pst1.setBytes(8, patientImage);
            pst1.setString(9, jTextFieldPatientMediCode.getText());

            pst1.executeUpdate();
            
            DefaultTableModel model = (DefaultTableModel)jTableAddPatient.getModel();
            model.setRowCount(0);
            show_patient();
            retrievePatientApply();
            JOptionPane.showMessageDialog(null, "Updated");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_jButtonAddPatientUpdateActionPerformed

    private void jTableAddPatientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAddPatientMouseClicked
        // TODO add your handling code here:
        
        int i = jTableAddPatient.getSelectedRow();
        TableModel model = jTableAddPatient.getModel();
        jTextFieldAddPatientID.setText(model.getValueAt(i, 0).toString());
        jTextFieldPatientMediCode.setText(model.getValueAt(i, 4).toString());
        
        
        
    }//GEN-LAST:event_jTableAddPatientMouseClicked

    private void jButtonAddPatientDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddPatientDeleteActionPerformed
        // TODO add your handling code here:
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
            if(conn!= null)
            {
                System.out.println("Connected to database successfully");
            }
            
            int row = jTableAddPatient.getSelectedRow();
            String value = (jTableAddPatient.getValueAt(row, 0).toString());
            String query = "DELETE FROM patient where Patient_id="+value;
            PreparedStatement pst = conn.prepareStatement(query);
            pst.executeUpdate();
            
            
            DefaultTableModel model = (DefaultTableModel)jTableAddPatient.getModel();
            model.setRowCount(0);
            show_patient();
            JOptionPane.showMessageDialog(null, "Deleted");
            retrievePatientApply();
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        
        
    }//GEN-LAST:event_jButtonAddPatientDeleteActionPerformed

    private void jButtonTreatmentHistoryResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTreatmentHistoryResetActionPerformed
        // TODO add your handling code here:
        
        jDateChooserTreatDate.setDate(null);
        
        jTextAreaMedicines.setText("");
        jTextAreaTests.setText("");
        jTextAreaSurgeries.setText("");
        jTextAreaDiseaseSymptom.setText("");
        jComboBoxDoctorMediCode.setSelectedIndex(0);
        jComboBoxPatientrMediCode.setSelectedIndex(0);
        
        
        
    }//GEN-LAST:event_jButtonTreatmentHistoryResetActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        
        HomePage homePage = new HomePage();
        homePage.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    public void patientMediCombo(){
        
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
            if(conn!= null)
            {
                System.out.println("Connected to database successfully");
            }
            String query = "select * from patient";
            PreparedStatement pst = conn.prepareStatement(query);
            Statement st = conn.createStatement();
            ResultSet rs = pst.executeQuery(query);
            
            while(rs.next()){
                String patiet_medi_code = rs.getString("Patient_medi_code");
                jComboBoxPatientrMediCode.addItem(patiet_medi_code);
                
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    
    public void doctorMediCombo(){
        
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
            if(conn!= null)
            {
                System.out.println("Connected to database successfully");
            }
            String query = "select * from doctor";
            PreparedStatement pst = conn.prepareStatement(query);
            Statement st = conn.createStatement();
            ResultSet rs = pst.executeQuery(query);
            
            while(rs.next()){
                String patiet_medi_code = rs.getString("Doctor_medi_code");
                jComboBoxDoctorMediCode.addItem(patiet_medi_code);
                
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    
    
    
    
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
            java.util.logging.Logger.getLogger(AdminOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminOperations().setVisible(true);
            }
        });
    }
    
    
    public DefaultComboBoxModel retrieveDoctor()
    {
        DefaultComboBoxModel dmD = new DefaultComboBoxModel();
        String sql = "SELECT Doctor_medi_code FROM doctor";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
            if(conn!= null)
            {
                System.out.println("Connected to database successfully");
            }
            Statement s = conn.prepareStatement(sql);
            ResultSet rs = s.executeQuery(sql);
            
            while(rs.next())
            {
                String name =rs.getString(1);
                dmD.addElement(name);
            }
            
            return dmD;
            
            
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    
    
    public DefaultComboBoxModel retrievePatient()
    {
        DefaultComboBoxModel dmP = new DefaultComboBoxModel();
        String sql = "SELECT Patient_medi_code FROM patient";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
            if(conn!= null)
            {
                System.out.println("Connected to database successfully");
            }
            Statement s = conn.prepareStatement(sql);
            ResultSet rs = s.executeQuery(sql);
            
            while(rs.next())
            {
                String name =rs.getString(1);
                dmP.addElement(name);
            }
            
            return dmP;
            
            
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    private void retrieveDoctorApply(){
        DefaultComboBoxModel dmP = retrievePatient();
        jComboBoxDoctorMediCode.setModel(dmP);
    }
    
    private void retrievePatientApply(){
        DefaultComboBoxModel dmD = retrieveDoctor();
        jComboBoxPatientrMediCode.setModel(dmD);
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel adminOpts;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAddDoctorInsert;
    private javax.swing.JButton jButtonAddPatientDelete;
    private javax.swing.JButton jButtonAddPatientUpdate;
    private javax.swing.JButton jButtonAdminOptAddDoctor;
    private javax.swing.JButton jButtonAdminOptAddPatient;
    private javax.swing.JButton jButtonAdminOptTreatmentHistory;
    private javax.swing.JButton jButtonDoctorPicChoose;
    private javax.swing.JButton jButtonPatientImageChoose;
    private javax.swing.JButton jButtonPatientSignupSignup;
    private javax.swing.JButton jButtonTreatmentHistoryDelete;
    private javax.swing.JButton jButtonTreatmentHistoryInsert;
    private javax.swing.JButton jButtonTreatmentHistoryReset;
    private javax.swing.JButton jButtonTreatmentHistoryUpdate;
    private javax.swing.JComboBox<String> jComboBoxDoctorMediCode;
    private javax.swing.JComboBox<String> jComboBoxPatientBloodGroup;
    private javax.swing.JComboBox<String> jComboBoxPatientrMediCode;
    private com.toedter.calendar.JDateChooser jDateChooserAddDoctorDOB;
    private com.toedter.calendar.JDateChooser jDateChooserPatientDOB;
    private com.toedter.calendar.JDateChooser jDateChooserTreatDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAddDoctorPicture;
    private javax.swing.JLabel jLabelPatientImage;
    private javax.swing.JPanel jPanelAddDoctor;
    private javax.swing.JPanel jPanelAddTreatment;
    private javax.swing.JPanel jPanelDoctorList;
    private javax.swing.JPanel jPanelPatientSignup;
    private javax.swing.JPanel jPanelUpdate;
    private javax.swing.JPanel jPanelViewData;
    private javax.swing.JPasswordField jPasswordFieldPatientPasswordSignup;
    private javax.swing.JRadioButton jRadioButtonAddDoctorFemale;
    private javax.swing.JRadioButton jRadioButtonAddDoctorMale;
    private javax.swing.JRadioButton jRadioButtonPatientFemale;
    private javax.swing.JRadioButton jRadioButtonPatientMale;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTableAddPatient;
    private javax.swing.JTable jTableDoctorList;
    private javax.swing.JTable jTableTreatmentHistory;
    private javax.swing.JTextArea jTextAreaAddDoctorSpeciality;
    private javax.swing.JTextArea jTextAreaDiseaseSymptom;
    private javax.swing.JTextArea jTextAreaMedicines;
    private javax.swing.JTextArea jTextAreaSurgeries;
    private javax.swing.JTextArea jTextAreaTests;
    private javax.swing.JTextField jTextFieldAddDoctorCity;
    private javax.swing.JTextField jTextFieldAddDoctorCountry;
    private javax.swing.JTextField jTextFieldAddDoctorEmail;
    private javax.swing.JTextField jTextFieldAddDoctorFirstName;
    private javax.swing.JTextField jTextFieldAddDoctorId;
    private javax.swing.JTextField jTextFieldAddDoctorLastName;
    private javax.swing.JTextField jTextFieldAddDoctorMediCode;
    private javax.swing.JTextField jTextFieldAddDoctorPassword;
    private javax.swing.JTextField jTextFieldAddDoctorPhoneNo;
    private javax.swing.JTextField jTextFieldAddDoctorPostalCode;
    private javax.swing.JTextField jTextFieldAddDoctorStreet;
    private javax.swing.JTextField jTextFieldAddPatientID;
    private javax.swing.JTextField jTextFieldPatientAge;
    private javax.swing.JTextField jTextFieldPatientCity;
    private javax.swing.JTextField jTextFieldPatientCountry;
    private javax.swing.JTextField jTextFieldPatientEmailAddress1;
    private javax.swing.JTextField jTextFieldPatientEmailAddress2;
    private javax.swing.JTextField jTextFieldPatientEmailAddress3;
    private javax.swing.JTextField jTextFieldPatientFirstName;
    private javax.swing.JTextField jTextFieldPatientLastName;
    private javax.swing.JTextField jTextFieldPatientMediCode;
    private javax.swing.JTextField jTextFieldPatientNID;
    private javax.swing.JTextField jTextFieldPatientPhoneNo1;
    private javax.swing.JTextField jTextFieldPatientPhoneNo2;
    private javax.swing.JTextField jTextFieldPatientPhoneNo3;
    private javax.swing.JTextField jTextFieldPatientPostalCode;
    private javax.swing.JTextField jTextFieldPatientStreet;
    private javax.swing.JTextField jTextFieldTreatmentHistoryID;
    private javax.swing.JPanel parentPanel;
    // End of variables declaration//GEN-END:variables
}
