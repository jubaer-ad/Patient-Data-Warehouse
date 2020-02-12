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
public class Treatment {
    private int Treatment_id;
    private Date Treatment_date;
    private String Patient_Patient_medi_code;
    private String Doctor_Doctor_medi_code;
    private String TreatmentDiseaseSymptom;
    private String TreatmentTests;
    private String TreatmentSurgeries;
    private String TreatmentMedicines;
    
    public Treatment(int Treatment_id, Date Treatment_date, String Patient_Patient_medi_code, String Doctor_Doctor_medi_code, String TreatmentDiseaseSymptom, String TreatmentTests, String TreatmentSurgeries, String TreatmentMedicines){
        this.Treatment_id = Treatment_id;
        this.Treatment_date = Treatment_date;
        this.Patient_Patient_medi_code = Patient_Patient_medi_code;
        this.Doctor_Doctor_medi_code = Doctor_Doctor_medi_code;
        this.TreatmentDiseaseSymptom = TreatmentDiseaseSymptom;
        this.TreatmentTests = TreatmentTests;
        this.TreatmentSurgeries = TreatmentSurgeries;
        this.TreatmentMedicines = TreatmentMedicines;
        
    }
    public int getTreatment_id(){
        return Treatment_id;
    }
    public Date getTreatment_date(){
        return Treatment_date;
    }
    public String getPatient_Patient_medi_code(){
        return Patient_Patient_medi_code;
    }
    public String getDoctor_Doctor_medi_code(){
        return Doctor_Doctor_medi_code;
    }
    public String getTreatmentDiseaseSymptom(){
        return TreatmentDiseaseSymptom;
    }
    public String getTreatmentTests(){
        return TreatmentTests;
    }
    public String getTreatmentSurgeries(){
        return TreatmentSurgeries;
    }
    public String getTreatmentMedicines(){
        return TreatmentMedicines;
    }
    
    
}
