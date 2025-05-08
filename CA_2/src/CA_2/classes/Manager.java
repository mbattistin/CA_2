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
    
    public Manager(Role role, String name, Date dateOfBirth, Date startDate, Department department) {
        super(role, name, dateOfBirth, startDate, department);
    }
    
    //it overrides the toString method to return the formatted string
    @Override
    public String toString() {
        return "Manager " + name + " of the department " + department.getDepartment() + ", date of birth: " + dateOfBirth + ", start working: " + startDate + ", role: " + role.getRoleName() + ".";
    }    
}
