/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2.classes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Mariana
 */
public class Employee extends Person{
    protected Role role;
    protected Date startDate;
    protected Department department;
    
    private final SimpleDateFormat simpleDateformatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);    
 
    public Employee(Role role, String name, Date dateOfBirth, Date startDate, Department department) {
        super(name, dateOfBirth);
        this.role = role;
        this.startDate = startDate;
        this.department = department;
    }
    
    //it overrides the toString method to return the formatted string
    @Override
    public String toString() {
        return "Employee " + name + " of the department " + department.getDepartment() + ", date of birth: " + simpleDateformatter.format(dateOfBirth) + 
                ", start working: " + simpleDateformatter.format(startDate) + ", role: " + role.getRoleName() + ".";
    }   
}
