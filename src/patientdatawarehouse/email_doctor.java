/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientdatawarehouse;

/**
 *
 * @author JuBaer
 */
public class email_doctor {
    private String Email_Doctor;
    private String Doctor_Doctor_medi_code;
    public email_doctor(String Email_Doctor, String Doctor_Doctor_medi_code){
        this.Email_Doctor = Email_Doctor;
        this.Doctor_Doctor_medi_code = Doctor_Doctor_medi_code;
    }
    public String getEmail_Doctor(){
        return Email_Doctor;
    }
    public String getDoctor_Doctor_medi_code(){
        return Doctor_Doctor_medi_code;
    }
    
}
