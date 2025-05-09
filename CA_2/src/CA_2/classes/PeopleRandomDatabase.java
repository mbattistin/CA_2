/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2.classes;

import CA_2.enums.*;
import java.util.Random;

/**
 *
 * @author Mariana
 */

//Reference: https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
public class PeopleRandomDatabase {
    //it creates a new instance of Random library
    private static final Random random = new Random();
    
    //it sets a list of names
    private static final String[] FIRST_NAMES = {
        "James", "Carlos", "Lucas", "Ana", "Jack", "Fernanda",
        "Ann", "Mateus", "Sofia", "Pedro", "Camila", "Gabriel",
        "Larissa", "Rafael", "Julia", "Joan", "Isabela", "Daniel",
        "Bianca", "Felipe", "Marcos", "Renata", "Gustavo", "Paula",
        "Julius", "Greg", "Eduardo", "Tatiane", "Neil", "Aline",
        "Rodrigo", "Ken", "Bernadete", "Carolina", "Fernando", "Amanda"
    };  
    
    //it sets a list of surnames
    private static final String[] SURNAMES = {
        "Fitzsimons", "Santos", "Oliveira", "Souza", "Pereira", "Lima",
        "Carvalho", "Ribeiro", "Costa", "Almeida", "Oconnel", "Gomes",
        "Martins", "Rocha", "Dias", "Barbosa", "Freitas", "Correia",
        "Moura", "Teixeira", "Mendes", "Batista", "Ramos", "Castro",
        "Nunes", "Moreira", "Cardoso", "Pinto", "Vieira", "Onneal"
    };
   
    //it gets a random name using the random nextInt to return a random number with the max value of the list lenght
    public static String getRandomFirstName() {
        return FIRST_NAMES[random.nextInt(FIRST_NAMES.length - 1)];
    }
    
    //it gets a random surname using the random nextInt to return a random number with the max value of the list lenght
    public static String getRandomSurname() {
        return SURNAMES[random.nextInt(SURNAMES.length - 1)];
    }
    
    //it gets a random role in the enum using the random nextInt to return a random number with the max value of the enum list
    public static String getRandomRole() {
        int  enumLength = RolesOptions.values().length;
        
        return RolesOptions.fromCode(random.nextInt(1, enumLength)).getOptionDescription();
    }
    
    //it gets a random department in the enum using the random nextInt to return a random number with the max value of the enum list
    public static String getRandomDepartment() {
        int  enumLength = DepartmentsOptions.values().length;
        return DepartmentsOptions.fromCode(random.nextInt(1, enumLength)).getOptionDescription();
    }
    
    //it gets randon number for year, month and day
    public static String getRandomDate(int startYear, int endYear) {
        //it gets the size of the range doing the end - start date and then sum the add daye so the value starts with the min date
        int year = random.nextInt(endYear - startYear + 1) + startYear;
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;
        return String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + year;
    }
}
