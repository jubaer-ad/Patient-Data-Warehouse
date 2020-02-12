/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientdatawarehouse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author JuBaer
 */
public class PatientDataWarehouse {

    /**
     * @param args the command line arguments
     */
    
    public static void connection(){
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
            if(conn!= null)
            {
                System.out.println("Connected to database successfully");
            }
        }catch(Exception e){
            System.out.println("Not connected to database.");
        }
        
    }
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        HomePage home = new HomePage();
           // home.run();
        
         Connection conn = null;
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
            if(conn!= null)
            {
                System.out.println("Connected to database successfully");
            }
            
            
            
            
        }catch(Exception e){
            System.out.println("Not connected to database.");
        }
        
        
        
        
    }
    
}
