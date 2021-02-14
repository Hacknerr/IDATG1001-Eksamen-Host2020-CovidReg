package me.ntnu.candidate.exam;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is used to process input from user.
 * @Author: 10009
 */
public class InputHandler {

    private Scanner sc;

    /**
     * Initializes Scanner
     */
    public InputHandler(){
        sc = new Scanner(System.in);
    }

    /**
     * Takes int from user and makes sure its within the number-range of the desired parameters
     * @param min
     * @param max
     * @return input from user.
     */
    public int getIntInput(int min, int max){

        boolean validInput = false;
        int inputFromUser = 0;

        while(!validInput){
            try{
                inputFromUser = sc.nextInt();
                if(checkValidInteger(inputFromUser, min, max)){
                    validInput = true;
                }
            }catch(InputMismatchException e){
                System.out.println("Please enter an integer!");
                sc.next();
            }
        }
        return inputFromUser;
    }

    /**
     * Checks if int-parameter is within the number-range of the desired min & max parameters
     * @param toBeChecked
     * @param minimumValue
     * @param maximumValue
     * @return boolean
     */
    public boolean checkValidInteger(int toBeChecked, int minimumValue, int maximumValue){
        boolean validInteger = false;
        if(toBeChecked <= maximumValue && toBeChecked >= minimumValue){
            validInteger = true;
        }else{
            System.out.println("Invalid input. Please enter an integer between " + minimumValue + " and " + maximumValue);
        }
        return validInteger;
    }

    /**
     * Checks if given string has a valid value, will continue input until it is valid.
     * @param string String to be checked
     * @return the string
     */
    public String checkString(String string){

        boolean validName = false;

        while(!validName){
            if(validStringInput(string)){
                validName = true;
            }else{
                System.out.println("Please enter a string!");
                Scanner scanner = new Scanner(System.in);
                string = scanner.nextLine();
            }
        }
        return string;
    }

    /**
     * Checks if string is a string and contains more than 0 letters in length.
     * @param string from user.
     * @return boolean
     */
    public boolean validStringInput(String string){

        if (string.length() == 0){
            return false;
        }

        int len = string.length();

        for (int i = 0; i < len; i++) {
            if ((Character.isDigit(string.charAt(i)) == true)) {
                return false;
            }
        }
        return true;
    }
}
