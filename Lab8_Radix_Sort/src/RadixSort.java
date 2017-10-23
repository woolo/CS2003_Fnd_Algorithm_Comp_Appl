/**
* @author Zimo Chai (Jerry)
* Student ID: 1495687
* Lab Section: 1
* Date: 2/24/2017
* Description: This program will implement Radix Sort with multiple queues
*/

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class RadixSort{
        
    public static void main(String[] argv){
	QueueReferenceBased<Integer> Q=null;
		try{
		    Q = new QueueReferenceBased<Integer>();
		    //Read file radix.dat into a queue Q
		    Scanner scan = new Scanner(new File("radix.dat"));	    
		    //Calling RadSort with queue Q and the pass number
		    while (scan.hasNextInt())
		    	Q.enqueue(scan.nextInt());
		    scan.close();
		}
		catch(IOException e){
		    System.err.println("error obtaining the data");
		    System.exit(9);
		}
		radixSort(Q);
    }
    
    
    public static void radixSort(QueueReferenceBased<Integer> Q){
    	radix(Q, 1);
    } 
    
    public static void radix(QueueReferenceBased<Integer> Q, int k){
		final int NUMDIGITS = 5; // maximum number of digits in data
		final int NUMBASE = 10; // decimal numbers, base 10
		//base case
		if(k <= NUMDIGITS){
			System.out.println("\n~~  sorting column "+k +" ~~");
			// creation of the array
			ArrayList <QueueReferenceBased<Integer>> pockets = 
			    new ArrayList <QueueReferenceBased<Integer>>(NUMBASE);
			//instantiation of the array
			for (int i=0; i<NUMBASE; i++)
			    pockets.add(i,new QueueReferenceBased<Integer>());
			//enqueue the appropriate pockets
			while(!Q.isEmpty())
				pockets.get(getKthNumber(Q.peek(), k, NUMBASE)).enqueue(Q.dequeue());
			// dequeue the pockets in the appropriate order
			// and print the elements right-aligned
			for (int i=0; i<NUMBASE; i++){
			    while(!pockets.get(i).isEmpty()){
			    	System.out.printf("%5d\n", pockets.get(i).peek());
			    	Q.enqueue(pockets.get(i).dequeue());
			    }
			}
			// Make recursive call if necessary
			radix(Q,k+1);
		}
    } // end RadSort


    /** find the kth digit of a number num writen in base numbase
     * @param num is the number considered
     * @param k is the position of the digit we want to know (from the right)
     * @param numbase is the base used to write <code>num</code> (ex base 10).
     */
    public static int getKthNumber(Integer num, int k, int numbase){
    	return (num/(int)Math.pow(numbase,k-1))%numbase;
    }
    
}// end RadixSort

