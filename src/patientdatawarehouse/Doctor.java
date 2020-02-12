/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientdatawarehouse;

import java.util.Date;

/**
 *
 * @author JuBaer
 */
public class Doctor {
    private int doctorId;
    private String doctorFirstName;
    private String doctorLastName;
    private String doctorGender;
    private Date doctorDOB;
    private String doctorSpeciality;
    private byte[] doctorImage;
    private String doctorMediCode;
    public Doctor(int doctorId, String doctorFirstName, String doctorLastName, String doctorGender, Date doctorDOB, String doctorSpeciality, byte[] doctorImage, String doctorMediCode){
        this.doctorId = doctorId;
        this.doctorFirstName = doctorFirstName;
        this.doctorLastName = doctorLastName;
        this.doctorGender = doctorGender;
        this.doctorDOB = doctorDOB;
        this.doctorSpeciality = doctorSpeciality;
        this.doctorImage = doctorImage;
        this.doctorMediCode = doctorMediCode;
        
    }
    public int getDoctorId(){
        return doctorId;
    }
    public String getDoctorFirstName(){
        return doctorFirstName;
    }
    public String getDoctorLastName(){
        return doctorLastName;
    }
    public String getDoctorGender(){
        return doctorGender;
    }
    public Date getDoctorDOB(){
        return doctorDOB;
    }
    public String getDoctorSpeciality(){
        return doctorSpeciality;
    }
    public byte[] getDoctorImage(){
        return doctorImage;
    }
    public String getDoctorMediCode(){
        return doctorMediCode;
    }
    
}
