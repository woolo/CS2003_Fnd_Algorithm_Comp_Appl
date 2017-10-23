
/**
 * CS 2003 - Lab 1. Code to compute the sum of doubles from an input
 * file.  NOTE: add data members and methods at your convenience. Use
 * the most accurate data types. You can reuse bits of code from
 * the class Lab1a.
 *
 * @author Zimo Chai (Jerry)
 */

import java.util.Scanner;
import java.util.Vector;
import java.io.File;
import java.io.IOException;

public class Lab1b {
    
    // data members
	double sum = 0;
    Vector <Double> intVector; 
    
    /** Constructor: calls method readFile that reads the doubles
     * contained in the file "<code>filename</code>" and computes the
     * sum of the elements.
     @param filename name of the file containing doubles.
    */
    public Lab1b(String filename) {
	readFile(filename);
    }

    /** Reads double from a file named <code>filename</code> and
     * computes the sum of the elements contained in the file 
     * @param filename name of the file containing the doubles 
     */
    public void readFile(String filename) {
	//  -- TO COMPLETE --
    	try{
    		File inputFile = new File(filename);
		    Scanner input = new Scanner(inputFile);
		    intVector = new Vector<Double>();
		    // store all elements in a vector  
		    while ( input.hasNext() == true) {// Q: Are there any way to implement this by using null?
		    	intVector.addElement(input.nextDouble());
		    }
    	}
		catch (IOException e) {
		    System.err.println("IOException in reading input file!!!");
		}	
	    
    	//Compute the sum by using enhanced loop
		for (double value: intVector)
			sum += value;
    	
    	//display the sum the elements
    	System.out.printf("The sum of the elements is %f", sum);
    	
    	
    } //end readFile()
    
    

    /** main : creates a Lab1b Object with the filename passed in
     * argument*/
    public static void main(String args[]) {
	if (args.length == 0)
	    System.err.println("enter the data file name!");
	else
	    new Lab1b(args[0]);
    } //end main
    
} //end class Lab1b
