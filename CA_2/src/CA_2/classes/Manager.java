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
public class Manager extends Employee{
    
    public Manager(Role role, String name, Date dateOfBirth, Date startDate) {
        super(role, name, dateOfBirth, startDate);
    }
    
    //it overrides the toString method to return the formatted string
    @Override
    public String toString() {
        return "Manager " + name + ", date of birth: " + dateOfBirth + ", start working: " + startDate + ", role: " + role.getRoleName() + ".";
    }    
}
