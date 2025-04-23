/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalapplication.classes;

import java.util.Date;

/**
 *
 * @author Mariana
 */
public class Patient extends Person{
    private Date AdmissionDate;

    public Patient(Date AdmissionDate, String name, Date dateOfBirth) {
        super(name, dateOfBirth);
        this.AdmissionDate = AdmissionDate;
    }    
}
