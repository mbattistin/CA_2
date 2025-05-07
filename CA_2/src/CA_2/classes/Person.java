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

//the class implements the Comparable interface to enable its objects to be comparable
//reference: https://java-programming.mooc.fi/part-10/2-interface-comparable
public class Person implements Comparable<Person>{
    protected String name;
    protected Date dateOfBirth;   
    
    public Person(String name, Date dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    //it overites the method of comparison of the interface
    @Override
    public int compareTo(Person comparablePerson) {
        //it compares the person by name, a common property from all the class' children
        return this.name.compareTo(comparablePerson.name);
    }

}
