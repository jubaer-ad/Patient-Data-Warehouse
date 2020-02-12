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
public class Patient {
    private int patientId;
    private String patientFirstName;
    private String patientLastName;
    private String patientGender;
    private Date patientDOB;
    private int patientAge;
    private String patientBloodGroup;
    private String patientNid;
    private byte[] patientImage;
    private String patientMediCode;
    
    public Patient(int patientId, String patientFirstName, String patientLastName, String patientGender, Date patientDOB, int patientAge, String patientBloodGroup, String patientNid, byte[] patientImage, String patientMediCode){
        this.patientId = patientId;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientGender = patientGender;
        this.patientDOB = patientDOB;
        this.patientAge = patientAge;
        this.patientBloodGroup = patientBloodGroup;
        this.patientNid = patientNid;
        this.patientImage = patientImage;
        this.patientMediCode = patientMediCode;
    }
    public int getpatientId(){
        return patientId;
    }
    public String getpatientFirstName(){
        return patientFirstName;
    }
    public String getpatientLastName(){
        return patientLastName;
    }
    public String getpatientGender(){
        return patientGender;
    }
    public Date getpatientDOB(){
        return patientDOB;
    }
    public int getpatientAge(){
        return patientAge;
    }
    public String getpatientBloodGroup(){
        return patientBloodGroup;
    }
    public String getpatientNid(){
        return patientNid;
    }
    public byte[] getpatientImage(){
        return patientImage;
    }
    public String getPatientMediCode(){
        return patientMediCode;
    }
    
    
    
}
