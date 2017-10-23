
/**
 * CS 2003 - Lab 1
 * Code to find the maximum integer in a given file
 * NOTE:
 * there are bugs including those causing compilation errors and
 * resulting in runtime logical problems
 *
 * @author Zimo Chai (Jerry)
 */

import java.util.Scanner;
import java.util.Vector;
import java.io.File;
import java.io.IOException;
import java.util.Random;


public class Lab1a {
    
    /**stores the data retrived from the file */
	/*
	 In this lab we use vector instead of an arrayList
	 The differences  between them can be seen at:
	 http://www.javaworld.com/article/2077425/java-se/vector-or-arraylist-which-is-better.html
	 */
    Vector <Integer> intVector; 
    /** variable used to compute the run-time */
    long startTime, endTime, totalTime;
    /** Constructor: computes the running time and call readFile
     * method
     */
    public Lab1a() {
	startTime = System.currentTimeMillis();
	readFile();
	endTime = System.currentTimeMillis();    
	totalTime = endTime - startTime;
	System.out.println("total time taken: " + totalTime + " milliseconds");
    }

    /** Reads the integers from the file lab1a.dat and stores them in
     * a vector */
    public void readFile() {
	try {
	    File inputFile = new File("Lab1a.dat");
	    Scanner input = new Scanner(inputFile);
	    intVector = new Vector<Integer>();
	    int max, elt,count=0;
	    // store all elements in a vector  
	    /*elt = input.nextInt();
	    Integer eltWrapped = new Integer(elt);
	    while (eltWrapped != null) {//What is it？
		count++;
		intVector.addElement(elt);
	    }
	    Integer eltWrapped；*/
	    while ( input.hasNext() == true) {// Q: Are there any way to implement this by using null?
		count++;
		intVector.addElement(elt = input.nextInt());
	    }
	    
	    // print on the terminal each elements of intVector
	    System.out.printf("There are %d integers in the input file:\n",
			       count);
	    for (int value: intVector)// Enhanced Loop just like an array, Q: Can we do this in an arrayList?
		System.out.printf("%d ",value);
	    System.out.println();
	    
	    // find the max (use a random index to start the search)
	    Random generator = new Random();
	    int vectorSize = intVector.size();
	    int index = generator.nextInt(vectorSize);
	    max = intVector.get(index);      
	    for(int i=1;i<vectorSize;i++){
		int value = intVector.get((index+i)%vectorSize);
		if(max < value)
		    max = value;
	    }
	    //output results
	    System.out.printf("The maximum integer in the input file is %d\n",max);
	    input.close();
	} 
	catch (IOException e) {
	    System.err.println("IOException in reading input file!!!");
	}	
    } //end readFile()
    
	
    /** main : creates an Object Lab1a */
    public static void main(String args[]) {
    Lab1a lab = new Lab1a();
    } //end main

    
} //end class Lab1a
