/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2.classes;

/**
 *
 * @author Mariana
 */
public class Department {
    private String department;
    private String description;
    
    public Department(String department, String description) {
        this.department = department;
        this.description = description;
    }
    
    public String getDepartment() {
        return department;
    }

    public String getDescription() {
        return description;
    }
}
