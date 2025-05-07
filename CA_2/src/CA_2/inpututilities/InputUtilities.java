/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CA_2.inpututilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Mariana
 */

//this class was created to validate the types of user inputs
public class InputUtilities {

   //it asks for the user a valid number between the ranges
    public int askUserForInt(String prompt, int minValue, int maxValue){
        
        Scanner myKB = new Scanner(System.in);
        int userInput= minValue - 1;
        //it does the loop until the user type a valid number
        do{
            //it displays the message on the console
            System.out.println(prompt);
            try{
                //it tries to read the user input
                userInput = myKB.nextInt();            

            }
            catch(Exception ex){
                //in case of error displays the error message
                System.out.println("You must enter integer between " + minValue + " and " + maxValue + ".");
                myKB.next();
            }

        } 
        //it validates if the input is between the values
        while (userInput < minValue || userInput > maxValue);
        
        //it returns the valid user input
        return (userInput);       
    }
    
    public String askUserForText(String prompt){
        
        Scanner myKB = new Scanner(System.in);
        String userInput ;
        
        do{
            System.out.println(prompt);
            System.out.println("You must enter text only.");
            
            userInput = myKB.nextLine();            
            
        }while (!userInput.matches("[a-zA-Z .!@?\"]+"));
        //userinput must be valid text
        
        return (userInput);
    }
    
    public String askUserForDate(String prompt){
            Scanner myKB = new Scanner(System.in);
            String userInput;
            boolean isDatevalid = true;
            //format the string to the given date format
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date inputDate = new Date();
            do{            
                System.out.println(prompt);
                System.out.println("You must enter a date (Eg. 20/01/2025)");
                
                userInput = myKB.nextLine();    
                try {
                    //try to parse the String to Date
                    inputDate = dateFormat.parse(userInput);
                    isDatevalid = true;
                } catch (ParseException e) {
                    isDatevalid =  false; 
                    myKB.next();
                }    
            }while (!isDatevalid);
            //userinput must be valid date
            return inputDate.toString();
    }
     
}
