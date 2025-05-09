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
public class ApplicationList<ElementType> extends ArrayList<ElementType> {
    //it stores the information if the list is sorted
    private boolean isListSorted = false;

    public boolean isIsListSorted() {
        return isListSorted;
    }

    public void setIsListSorted(boolean isListSorted) {
        this.isListSorted = isListSorted;
    }
    
    
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
    
    //this method implements the linear search for linear search
    public int unorderedLinearSearch(ElementType keyElement) {
        int i;
        //simple for to run the list searching for the key element
        for (i = 0; i < size(); i++) {
            //it compares the current element with the key element
            if (((Comparable) get(i)).compareTo((Comparable) keyElement) == 0) {
                //it return the position of the element if its found
                return i;
            }
        }
        //it return -1 in case not found
        return -1;
    }
    
    //this method implements the binary search using recursiveness
    public int orderedBinarySearch(ElementType keyElement, int start, int end) {
        //it checks if the start is equals to end
        if (start == end) {
            //it checks if the element is not in the first/last position in the list
            if((((Comparable) get(start)).compareTo(keyElement)) == 0){
                return start;
            }
            else{
                //no element found
                return -1;
            }
        }

        //it calculates the middle position of the list
        int middle = (start + end) / 2;
        //it compares the middle element with the key element
        int comparison = ((Comparable) get(middle)).compareTo(keyElement);

        //if the key element is found, return the position
        if (comparison == 0) {
            return middle;
        //if the key element is before the middle element on the list, it gets the information before the middle    
        } else if (comparison > 0) {
            return orderedBinarySearch(keyElement, start, middle - 1);
        //if the key element is after the middle element on the list, it gets the information after the middle    
        } else {
            return orderedBinarySearch(keyElement, middle + 1, end);
        }        
    }
}


