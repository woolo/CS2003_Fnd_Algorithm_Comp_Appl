/**
* @author Zimo Chai (Jerry)
* Student ID: 1495687
* Lab Section: 1
* Date: 2/15/2017
* Description: This program will use a recursive method for recognizing a palindrome.
*/

import java.util.Scanner;

public class Lab6{


    public static void main(String[] args){
	/******************************************************** Palindrome */
	if (!isaPalindrome("satanoscillatemymetallicsonatas"))
	    System.out.println("You have a bug in your code!");
	else
	    System.out.println("check for palidrome ok");
			    
	Scanner console = new Scanner(System.in);
	System.out.print("Input a string\n>");
	String line = console.nextLine();
	while (!line.equals("")){
	    if (isaPalindrome(line))
	    	System.out.println(line+ " is a palindrome");
	    else
	    	System.out.println(line+ " is *not* a palindrome");
	    System.out.print("Input a string\n >");
	    line = console.nextLine();
	}
    }

    /** method to determine whether the specified string is a palindrome
     * @param expr string 
     * @return true if the specified string is a palindrome, otherwise false.
     */
    public static boolean isaPalindrome(String expr){
    	if (expr.length() <= 1)
    		return true;
    	//Notice that the reason why we use == below, is because char is a primitive type in Java
    	if (expr.charAt(0)==expr.charAt(expr.length()-1))
    		return isaPalindrome(expr.substring(1, expr.length()-1));
    	else return false;
    }

}
