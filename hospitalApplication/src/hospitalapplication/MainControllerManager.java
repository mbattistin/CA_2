/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalapplication;

import hospitalapplication.enums.MenuOptions;
import hospitalapplication.classes.Employee;
import hospitalapplication.classes.Manager;
import hospitalapplication.classes.Patient;
import hospitalapplication.classes.PeopleRandomDatabase;
import hospitalapplication.classes.Person;
import hospitalapplication.classes.Role;
import hospitalapplication.enums.AddRecordMenuOptions;
import static hospitalapplication.enums.MenuOptions.ADD_RECORD;
import static hospitalapplication.enums.MenuOptions.EXIT;
import static hospitalapplication.enums.MenuOptions.SEARCH;
import static hospitalapplication.enums.MenuOptions.SORT;
import hospitalapplication.inpututilities.InputUtilities;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Mariana
 */

//This class was made to use in the main class. So the functions don't need to be all static.
public class MainControllerManager {
    private InsertionSortList<Person> peopleList = new InsertionSortList<Person>();
    //type of date format where dd = days, MM = month, yyyy = year
    private final SimpleDateFormat simpleDateformatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);    
    //it creates a new instance for the InputUtilities library to verify user inputs
    private InputUtilities inputUtilities = new InputUtilities();
    
    //This method is static to be called in the Main
    public static void ExecuteMain(){
        //Doing an instance of the own class, it is possible to use this new object to call non-static methods
        MainControllerManager mainControllerManager = new MainControllerManager();
        
        System.out.println("Welcome to Hospital System!");
        //it call the method to read to ask and read the input file
        mainControllerManager.readInputFile();
        //it displays the main menu
        mainControllerManager.displayMainMenuOptions();
    }
    
    private void readInputFile(){
        //it creates a new instance of the Scanner to read the keybord input
        Scanner scanner = new Scanner(System.in);
        //variable to control if the file was read 
        boolean fileRed = true;
        //variable to control the current file line
        int lineNumber = 1;
        //this loop asks for the user input until it gets a valid file
        //I choose do/while because the code needs to execute at least one time
        do{
            System.out.println("Please, enter the file name with the data you want to work with:");
            //it reads the user input
            String filename = scanner.nextLine();

            //it creates a buffered reader that reads text line-by-line from the file
            //it is inside a try/catch to avoid the system to crash
            //Reference: https://www.geeksforgeeks.org/java-io-bufferedreader-class-java/
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                //this loop goes all the lines in the file 
                while ((line = reader.readLine()) != null) {
                    //it separates the line by ; and transform in an array
                    String[] lineSplitted = line.split(";");
                    //it is expected 4 properties in the line
                    if (lineSplitted.length == 4) {
                        String name = lineSplitted[0].trim();
                        String dateOfBirth = lineSplitted[1].trim();
                        String role = lineSplitted[2].trim();
                        String date = lineSplitted[3].trim();

                        insertObjectIntoList(name, dateOfBirth, role, date, lineNumber);
                    }
                    else{
                        System.out.println("The text line " + lineNumber + " does not contains the correct number of parameters.");
                    }
                    
                    lineNumber++;
                }
                fileRed = true;
                System.out.println("The text file was read successfully.");
            } catch (Exception ex) {
                System.out.println("An error happened while reading the file: " + ex.getMessage());
                fileRed = false;
            }            
        }
        while(!fileRed);        
    }
    
    
    private void insertObjectIntoList(String name, String dateOfBirth,  String role, String dateAdmission, int line){
        try {
            //it convert the string into date object
            Date dtOfBirth = simpleDateformatter.parse(dateOfBirth);
            Date dtAdmission = simpleDateformatter.parse(dateAdmission);
            
            Person newPerson = null;
            Role personRole = new Role(role);

            switch (role.toLowerCase()) {
                case "manager" -> newPerson = new Manager(personRole, name, dtOfBirth, dtAdmission);
                case "nurse" -> newPerson = new Employee(personRole, name, dtOfBirth, dtAdmission);
                case "doctor" -> newPerson = new Employee(personRole, name, dtOfBirth, dtAdmission);
                case "patient" -> newPerson = new Patient(dtOfBirth, name, dtAdmission);
                default -> System.out.println("Unknown role: " + role + " at text line "+ line + ". Person not included in the list.");
            }
            
            if(newPerson != null){
                peopleList.add(newPerson);
            }
        }
        catch(Exception ex){
            System.out.println("An error happen while reading the text in line " + line + ".");
            System.out.println("Error message: " + ex.getMessage());
        }
    }   
    
    
     public  void displayMainMenuOptions(){
        int selectedMenuOption = 0;
        
        //display menu options
        System.out.println("==========================================================================================");
        System.out.println("|                                     Hospital System Menu                               |");
        System.out.println("==========================================================================================");
        for (MenuOptions option : MenuOptions.values()) {
            System.out.println(option);
        }    
        System.out.println("==========================================================================================");
        //it calls the inputUtility library to ask the user input until is a valid input
        selectedMenuOption = inputUtilities.askUserForInt("Select one option from the menu", 1, 4);
        //it goes to call the specific action for the selected option in the menu
        mainMenuOptionActions(selectedMenuOption);        
    }
     
     private void mainMenuOptionActions(int selectedMenuOption){
            MenuOptions selectedOption = MenuOptions.fromCode(selectedMenuOption);
            //switch case to verify wich option was selected and call the specific actions
            switch (selectedOption) {
                case SORT -> {
                    peopleList.insertionSort();
                    displayPeopleList(20);
                    displayMainMenuOptions();
                }
                case SEARCH -> {

                }
                case ADD_RECORD -> {
                    displayAddRecordMenu();
                }
                case EXIT -> System.out.println("Thank you for visiting our system. See you next time!");
            }         
     }
     
     private void displayPeopleList(int displayNumberLimit){
           
           //it creates the iterator to go through any types of lists
            Iterator iterator=  peopleList.iterator();
            
            int countLines = 0;
           //it goes in each element of the queue and add to the string
            while (iterator.hasNext() && countLines <= displayNumberLimit){
                //it concats the string with the new text
                System.out.println(iterator.next().toString());
                countLines++;
           }
     }

     private void displayAddRecordMenu(){
        int selectedMenuOption = 0;
        for (AddRecordMenuOptions option : AddRecordMenuOptions.values()) {
            System.out.println(option);
        }
        //it calls the inputUtility library to ask the user input until is a valid input
        selectedMenuOption = inputUtilities.askUserForInt("Select one option from the menu", 1, 4);
        addRecordMenuOptionActions(selectedMenuOption);
     }
     
     private void addRecordMenuOptionActions(int selectedMenuOption){
            AddRecordMenuOptions selectedOption = AddRecordMenuOptions.fromCode(selectedMenuOption);
            //switch case to verify wich option was selected and call the specific actions
            switch (selectedOption) {
                case ADD_PERSON -> {

                }
                case RANDOM_PERSON -> {
                    //it calls the method to add a random record
                    addRandomRecord();
                    //it calls the display method to bring all the list
                    displayPeopleList(peopleList.size());
                    //it calls back add record menu
                    displayAddRecordMenu();
                }
                //it comes back to the main menu
                case EXIT -> displayMainMenuOptions();
            }         
     }
     
     private void addRandomRecord(){
         //it calls the static class to bring random information
         String name = PeopleRandomDatabase.getRandomFirstName() + " " + PeopleRandomDatabase.getRandomSurname();
         String dob = PeopleRandomDatabase.getRandomDate(1925, 2025);
         String dtAdmission = PeopleRandomDatabase.getRandomDate(2021, 2025);
         String role = PeopleRandomDatabase.getRandomRole();
         //it calls the method to insert object into the list
         insertObjectIntoList(name, dob, role, dtAdmission, 0);
         //it displays a message to the user
         System.out.println(role + " " + name + ", date of birth: " + dob + ", admission: " + dtAdmission +  " was added to the list.");
     }
}
