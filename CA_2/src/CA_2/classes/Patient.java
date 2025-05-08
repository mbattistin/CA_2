/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2.classes;

import java.util.Date;

/**
 *
 * @author Mariana
 */
public class Patient extends Person{
    private Date admissionDate;
    private Department admittedTo;
    
    public Patient(Date admissionDate, String name, Date dateOfBirth, Department admittedTo) {
        super(name, dateOfBirth);
        this.admissionDate = admissionDate;
        this.admittedTo = admittedTo;
    }

    //it overrides the toString method to return the formatted string
    @Override
    public String toString() {
        return "Patient " + name + " hospitalized in " + admittedTo.getDepartment() + ", date of birth: " + dateOfBirth + ", admission: " + admissionDate +  ".";
    }      
}
