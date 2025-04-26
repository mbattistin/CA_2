/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospitalapplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author Mariana
 */
public class HospitalApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        readInputFile();
    }
    
    private static void readInputFile(){
        //it creates a new instance of the Scanner to read the keybord input
        Scanner scanner = new Scanner(System.in);
        //variable to control if the file was read 
        boolean fileRed = true;
        
        //this loop asks for the user input until it gets a valid file
        //I choose do/while because the code needs to execute at least one time
        do{
            System.out.println("Please, enter the file name with the data");
            //it reads the user input
            String filename = scanner.nextLine();

            //it creates a buffered reader that reads text line-by-line from the file
            //it is inside a try/catch to avoid the system to crash
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

                        insertObjectIntoList(name, dateOfBirth, role, date);
                    }
                }
                fileRed = true;
            } catch (Exception ex) {
                System.out.println("An error happened while reading the file: " + ex.getMessage());
                fileRed = false;
            }            
        }
        while(fileRed);        
    }
    
    
    private static void insertObjectIntoList(String name, String dateOfBirth,  String role, String dateAdmission){
        
    }
    
}
