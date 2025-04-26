/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospitalapplication.inpututilities;

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
     
}
