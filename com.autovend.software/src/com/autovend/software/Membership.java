package com.autovend.software;

import java.util.Scanner;


public class Membership {
    
    public static void main(String[] args) {
    	String membershipNumber;

        Scanner input = new Scanner(System.in);

        System.out.println("Do you wish to provide your Membership Number? (Y/N) ");
        String response = input.nextLine();

        if (response.equalsIgnoreCase("Y")){
        	 // Display virtual keyboard
            System.out.println("Please enter your membership number: ");
                
            // Get membership number input from the customer
            membershipNumber = input.nextLine();
            try {
            	isValid(membershipNumber);
            	
            }
            //throws an exception if an invalid membership number is input by the customer
            catch (Exception e) {
            	System.out.println("Invalid membership number. " + e.getMessage());
            	return;
            }
            
        }
        //prompts the customer to continue with the self check out if they do not wish to provide Membership number
        else {
        	System.out.println("Continue with the Self-CheckOut. ");
        }
    }
    //Membership number needs to be a string of length 10 and should only have digits from 0 to 9
    //this is the criteria for membership number to be considered valid
    public static void isValid(String membershipNumber) throws Exception {
    	if (membershipNumber.length() != 10) {
    		throw new Exception("Membership Number should be of 10 digits long");
    	}
        for (char c : membershipNumber.toCharArray()) {
            if(c < '0' || c > '9') {
                throw new Exception("Membership number should only contain digits from 0 to 9.");
            }
        }

    }
    
  }
    
