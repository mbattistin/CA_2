/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

import CA_2.classes.*;
import CA_2.enums.*;
import CA_2.inpututilities.InputUtilities;
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
    private ApplicationList<Person> peopleList = new ApplicationList<Person>();
    //type of date format where dd = days, MM = month, yyyy = year
    private final SimpleDateFormat simpleDateformatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);      

    //it creates a new instance for the InputUtilities library to verify user inputs
    private InputUtilities inputUtilities = new InputUtilities();

    //This method is static to be called in the Main
    public static void ExecuteMain(){
        //Doing an instance of the own class, it is possible to use this new object to call non-static methods
        MainControllerManager mainControllerManager = new MainControllerManager();
        System.out.println("==========================================================================================================================================================");
        System.out.println("|                                                                  Welcome to Hospital System                                                            |");
        System.out.println("==========================================================================================================================================================");        
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
                //it reads the first line to skip it
                String line =  reader.readLine();
                
                //this loop goes all the lines in the file 
                while ((line = reader.readLine()) != null) {
                    //it separates the line by ; and transform in an array
                    String[] lineSplitted = line.split(";");
                    //it is expected 4 properties in the line
                    if (lineSplitted.length == 5) {
                        String role = lineSplitted[0].trim();
                        String name = lineSplitted[1].trim();
                        String dateOfBirth = lineSplitted[2].trim();
                        String date = lineSplitted[3].trim();
                        String department = lineSplitted[4].trim();
                        insertObjectIntoList(name, dateOfBirth, role, date,department, lineNumber);
                    }
                    else{
                        System.out.println("The text line " + lineNumber + " does not contains the correct number of parameters.");
                    }
                    
                    lineNumber++;
                }
                
                if(peopleList.isEmpty()){
                    fileRed = false;
                    System.out.println("File with no valid records.");
                }
                else{
                    fileRed = true;
                    System.out.println("The text file was read successfully.");                  
                }

            } catch (Exception ex) {
                System.out.println("An error happened while reading the file: " + ex.getMessage());
                fileRed = false;
            }            
        }
        while(!fileRed);        
    }
    
    
    private void insertObjectIntoList(String name, String dateOfBirth,  String role, String dateAdmission,String department,  int line){
        try {
            //it convert the string into date object
            Date dtOfBirth = simpleDateformatter.parse(dateOfBirth);
            Date dtAdmission = simpleDateformatter.parse(dateAdmission);
            
            Person newPerson = null;
            Role personRole = new Role(role);
            Department personDepartment = new Department(department, "");
            switch (role.toLowerCase()) {
                case "manager" -> newPerson = new Manager(personRole, name, dtOfBirth, dtAdmission, personDepartment);
                case "nurse" -> newPerson = new Employee(personRole, name, dtOfBirth, dtAdmission, personDepartment);
                case "doctor" -> newPerson = new Employee(personRole, name, dtOfBirth, dtAdmission, personDepartment);
                case "patient" -> newPerson = new Patient(dtOfBirth, name, dtAdmission, personDepartment);
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
        System.out.println("==========================================================================================================================================================");
        System.out.println("|                                                                  Hospital System Menu                                                                  |");
        System.out.println("==========================================================================================================================================================");
        for (MenuOptions option : MenuOptions.values()) {
            System.out.println(option);
        }    
        System.out.println("==========================================================================================================================================================");
        //it calls the inputUtility library to ask the user input until is a valid input
        selectedMenuOption = inputUtilities.askUserForInt("Select one option from the menu:", 1, 4);
        //it goes to call the specific action for the selected option in the menu
        mainMenuOptionActions(selectedMenuOption);        
    }
     
     private void mainMenuOptionActions(int selectedMenuOption){
        MenuOptions selectedOption = MenuOptions.fromCode(selectedMenuOption);
        //switch case to verify wich option was selected and call the specific actions
        switch (selectedOption) {
            case SORT -> {
                peopleList.insertionSort();
                //it sets the list to sorted
                peopleList.setIsListSorted(true);
                displayPeopleList(20);
                displayMainMenuOptions();
            }
            case SEARCH -> {
                searchPeople();
                displayMainMenuOptions();
            }
            case ADD_RECORD -> {
                displayAddRecordMenu();
            }
            case EXIT -> System.out.println("Thank you for visiting our system. See you next time!");
        }         
     }
      

     private void displayPeopleList(int displayNumberLimit){
        System.out.println("==========================================================================================================================================================");
        System.out.println("|                                                                   Hospital Users List                                                                  |");
        System.out.println("==========================================================================================================================================================");           
       //it creates the iterator to go through any types of lists
        Iterator iterator=  peopleList.iterator();

        int countLines = 1;
       //it goes in each element of the queue and add to the string
        while (iterator.hasNext() && countLines <= displayNumberLimit){
            //it concats the string with the new text
            System.out.println(iterator.next().toString());
            countLines++;
       }
     }

     private void displayAddRecordMenu(){
        int selectedMenuOption = 0;        
        System.out.println("==========================================================================================================================================================");
        System.out.println("|                                                                      New User Menu                                                                     |");
        System.out.println("=========================================================================================================================================================="); 
        for (AddRecordMenuOptions option : AddRecordMenuOptions.values()) {
            System.out.println(option);
        }
        System.out.println("==========================================================================================================================================================");
        //it calls the inputUtility library to ask the user input until is a valid input
        selectedMenuOption = inputUtilities.askUserForInt("Select one option from the menu:", 1, 4);
        addRecordMenuOptionActions(selectedMenuOption);
     }
     
     private void addRecordMenuOptionActions(int selectedMenuOption){
            AddRecordMenuOptions selectedOption = AddRecordMenuOptions.fromCode(selectedMenuOption);
            //switch case to verify wich option was selected and call the specific actions
            switch (selectedOption) {
                case ADD_PERSON -> {
                    //it calls the method to add manually the person
                    addManualRecord();
                    //it calls the display method to bring all the list
                    displayPeopleList(peopleList.size());
                    //it calls back add record menu
                    displayAddRecordMenu();
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
        System.out.println("==========================================================================================================================================================");
        System.out.println("|                                                                     New Random User                                                                    |");
        System.out.println("=========================================================================================================================================================="); 
        //it calls the static class to bring random information
        String name = PeopleRandomDatabase.getRandomFirstName() + " " + PeopleRandomDatabase.getRandomSurname();
        String dob = PeopleRandomDatabase.getRandomDate(1925, 2025);
        String dtAdmission = PeopleRandomDatabase.getRandomDate(2021, 2025);
        String role = PeopleRandomDatabase.getRandomRole();
        String department = PeopleRandomDatabase.getRandomDepartment();
        //it calls the method to insert object into the list
        insertObjectIntoList(name, dob, role, dtAdmission,department, 0);
        //it sets the list to unsorted again
        peopleList.setIsListSorted(false);
        //it displays a message to the user
        System.out.println(role + " " + name + ", date of birth: " + dob + ", was addmited " +  dtAdmission + " at " + department +  " was added to the list.");
     }
     
     private void addManualRecord(){
        System.out.println("==========================================================================================================================================================");
        System.out.println("|                                                                        New User                                                                        |");
        System.out.println("=========================================================================================================================================================="); 
         //it asks for the user to inser the inputs
        String name = inputUtilities.askUserForText("Insert the user name:");
        String dob = inputUtilities.askUserForDate("Insert date of birth:");
        String dtAdmission = inputUtilities.askUserForDate("Insert the date of admission");
        
        //it displays the roles option
        for (RolesOptions option : RolesOptions.values()) {
            System.out.println(option);
        }
        //it asks user input to select the option
        int selectedMenuOption = inputUtilities.askUserForInt("Select the user role:", 1, 4);
        //it retrieves the role's description
        String role = RolesOptions.fromCode(selectedMenuOption).getOptionDescription();
        
        //it displays the department option
        for (DepartmentsOptions option : DepartmentsOptions.values()) {
            System.out.println(option);
        }
        //it asks user input to select the option
        selectedMenuOption = inputUtilities.askUserForInt("Select the user department:", 1, 6);
        //it retrieves the department's description
        String department = DepartmentsOptions.fromCode(selectedMenuOption).getOptionDescription();
        
        //it calls the method to insert object into the list
        insertObjectIntoList(name, dob, role, dtAdmission, department, 0);
        //it sets the list to unsorted again
        peopleList.setIsListSorted(false);
        //it displays a message to the user
        System.out.println(role + " " + name + ", date of birth: " + dob + ", was addmited " +  dtAdmission + " at " + department +  " was added to the list.");
     }
     
     private void searchPeople(){
        System.out.println("==========================================================================================================================================================");
        System.out.println("|                                                                       Search User                                                                      |");
        System.out.println("==========================================================================================================================================================");
         //it asks for the user to insert the input for the search
        String name = inputUtilities.askUserForText("Insert the person name for the search:");
        //it creates a new instance of person with the given name tobe used in the search
        Person person = new Person(name, new Date());
        int peoplePosition;
        //if the list is sorted, it calls the binary search
        if(peopleList.isIsListSorted()){
            peoplePosition = peopleList.orderedBinarySearch(person, 0, peopleList.size() - 1);
        }else{
            //if the list is unsorted, it calls the linear search
            peoplePosition = peopleList.unorderedLinearSearch(person);
        }
        //if the position is greater than -1, it means it found a record
        if(peoplePosition > -1){
            person = peopleList.get(peoplePosition);
            System.out.println("The user was found: " + person.toString());
        }
        else{
            //otherwise, it displays no person was found.
            System.out.println("No user found!");
        }
        
     }
}
