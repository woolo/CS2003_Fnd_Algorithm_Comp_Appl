/**
* @author Zimo Chai (Jerry)
* Student ID: 1495687
* Lab Section: 1
* Date: 1/31/2017
* Description: This program will find the max and min value and their indexs in a given file.
*/

import java.util.Scanner;
import java.util.Vector;
import java.io.File;
import java.io.IOException;

public class findMMP {
	
	public findMMP(){
		Vector <Double> doubleVector = readFile();
		MinMaxObject obj = findMinMaxPos(doubleVector, 1, doubleVector.size(), 
				new MinMaxObject (doubleVector.get(1),doubleVector.get(1), 1, 1));
    	System.out.println(obj.toString());
	}
	
    public MinMaxObject findMinMaxPos(Vector <Double> doubleVector, int low, int high, MinMaxObject obj){
    	if (low >= high)//Though works, it is still suspected.
    		return obj;
    	/*else{
    		if (doubleVector.get(low) > obj.getMax())
    			return findMinMaxPos(doubleVector, low+1, high, 
    					new MinMaxObject (obj.getMin(),doubleVector.get(low), obj.getMinPos(), low));
    		else if (doubleVector.get(low) < obj.getMin())
    			return findMinMaxPos(doubleVector, low+1, high, 
    					new MinMaxObject (doubleVector.get(low),obj.getMax(), low, obj.getMaxPos()));
    		
    	}
    	return findMinMaxPos(doubleVector, low+1, high, 
					new MinMaxObject (obj.getMin(),obj.getMax(), obj.getMinPos(), obj.getMaxPos()));*/
    	
    	//Notice that the three situations above can be concluded into one, beautiful, isn't it?
    	return findMinMaxPos
    			(doubleVector, low+1, high, new MinMaxObject 
    					(
						(doubleVector.get(low) < obj.getMin()? doubleVector.get(low): obj.getMin()),
						(doubleVector.get(low) > obj.getMax()? doubleVector.get(low): obj.getMax()),
						(doubleVector.get(low) < obj.getMin()? low : obj.getMinPos()),
						(doubleVector.get(low) > obj.getMax()? low : obj.getMaxPos())
						)
    			);
    }
	
    /** Reads the doubles from the file lab1a.dat and stores them in a vector
     */
    public Vector<Double> readFile() {
		   boolean tryAgain;
		   Scanner input = null;
		   Vector <Double> doubleVector = null; 
		   int count =0;
		   do
		   {	
		   	   Scanner console = new Scanner( System.in );         
			   System.out.print( "Please enter a file name to read: " );         
			   String inFile = console.next();  
			   tryAgain = false;
			   
			   try
			   {
			       File file = new File( inFile );// connect to the file         
			       input = new Scanner( file ); 
			       doubleVector = new Vector<Double>();
				   double elt;
				   while ( input.hasNext() == true){
					   doubleVector.addElement(elt = input.nextDouble());
					   count++;
				   }
		       }
			   catch (IOException e) {
				   System.out.printf( "Error Opening file %s, %s\n"
						   + "How about trying another file?\n\n", inFile, e );
				    tryAgain = true;
				}
			   finally
			   {
				   if (input != null) input.close();// 4. close the file
			   }
		   } while (tryAgain);
		   
		   // For debugging purpose, print on the terminal each elements of intVector
		   System.out.printf("There are %d doubles in the input file:\n", count);
		   for (double value: doubleVector)// Enhanced Loop just like an array.
		    	System.out.printf("%.2f ",value);
		   System.out.println();
		   
		   return doubleVector;
    }// end readFile()  

//*************************************************
    /**@author Zimo Chai (Jerry)
    * Student ID: 1495687
    * Lab Section: 1
    * Date: 1/31/2017
    * Description: MinMaxObject serves as a "container" which stores four values.
    */
    
    class MinMaxObject {
    	private double max;
    	private double min;
    	private int maxPos;
    	private int minPos;
    	
    	public MinMaxObject(double min, double max, int minPos, int maxPos ){
    		this.max = max;
    		this.min = min;
    		this.maxPos = maxPos;
    		this.minPos = minPos;
    	}
    	
    	public double getMax(){
    		return this.max;
    	}
    	
    	public double getMin(){
    		return this.min;
    	}
    	
    	public int getMaxPos(){
    		return this.maxPos;
    	}
    	
    	public int getMinPos(){
    		return this.minPos;
    	}
    	
    	public String toString(){
    		return String.format("The max value is %f. Its index is %d;\n"
    				+ "The min value is %f. Its index is %d.\n", max, maxPos+1, min, minPos+1);
    	}
    }
    
//*************************************************
}
