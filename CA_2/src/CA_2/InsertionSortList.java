/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

import java.util.ArrayList;

/**
 *
 * @author Mariana
 * @param <ElementType>
 */

//it extends Array list to get its methods and use a generic type, ElementType, to accept any type of object
public class InsertionSortList<ElementType> extends ArrayList<ElementType> {
    
    public  void insertionSort(){
        //this variable will go through each element of the list
        int i;
        //this variable will track the position of the element
        int position;
        //this variable is used to store the element to be sorted
        ElementType keyElement;
        
        //the loop starts ate 1 because this method considers that the first element is already sorted
        for(i = 1; i < size(); i++){
            //it stores the element of the current position
            keyElement = get(i);
            //it stores the current position
            position = i;
            
            //it checks if the position is greater then 0 to no go out of the boundaries of the list
            //this loop compares if the current element is greater than the previous one, if it is, it swaps them 
            while(position > 0 && ((Comparable) get(position - 1)).compareTo((Comparable) keyElement) > 0){
                //it gets the previous element
                ElementType elementPosMinusOne = get(position-1);
                //it sets the previous element in the current position
                set(position, elementPosMinusOne);
                //it moves the current position to the previous one and continues the loop until it finds the correct position for the element
                position = position -1;
            }
            
            //it sets the element to the right position
            set(position, keyElement);
        }
    }
}
