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
public class Employee extends Person{
    private Role role;
    private Date startDate;
    
    public Employee(Role role, String name, Date dateOfBirth, Date startDate) {
        super(name, dateOfBirth);
        this.role = role;
        this.startDate = startDate;
    }
}
