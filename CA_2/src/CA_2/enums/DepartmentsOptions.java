/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2.enums;
/**
 *
 * @author Mariana
 */
public enum DepartmentsOptions {
    //it sets the enum values with a number and a description
    PEDIATRICS(1, "Pediatrics"),
    CARDIOLOGY(2, "Cardiology"),
    EMERGENCY(3, "Emergency"),
    GENERAL(4, "General"),
    PSYCHIATRY(5, "Psychiatry"),
    ORTHOPEDICS(6, "Orthopedics");
    
    //it stores the number and description for the enum
    private final int optionNumber;
    private final String optionDescription;

    //enum constructor that sets the values when created
    DepartmentsOptions(int optionNumber, String optionDescription) {
        this.optionNumber = optionNumber;
        this.optionDescription = optionDescription;
    }

    //it gets the optionNumber property
    public int getOptionNumber() {
        return optionNumber;
    }
    
    //it gets the optionDescription property
    public String getOptionDescription() {
        return optionDescription;
    }

    public static DepartmentsOptions fromCode(int optionNumber) {
        //it does a loop and returns the option selected
        for (DepartmentsOptions option : DepartmentsOptions.values()) {
            if (option.optionNumber == optionNumber) {
                return option;
            }
        }
        //if it does not find, return null
        return null;
    }

    //it overrides the toString method to return the formatted menu option
    @Override
    public String toString() {
        return optionNumber + ". " + optionDescription;
    }    
}
