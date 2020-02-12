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
public class phone_no_doctor {
    private String Phone_no_Doctor;
    private String Doctor_Doctor_medi_code;
    public phone_no_doctor(String Phone_no_Doctor, String Doctor_Doctor_medi_code){
        this.Phone_no_Doctor = Phone_no_Doctor;
        this.Doctor_Doctor_medi_code = Doctor_Doctor_medi_code;
    }
    public String getPhone_no_Doctor(){
        return Phone_no_Doctor;
    }
    public String getDoctor_Doctor_medi_code(){
        return Doctor_Doctor_medi_code;
    }
}
