/**
* @author Zimo Chai (Jerry)
* Student ID: 1495687
* Lab Section: 1
* Date: 3/30/2017
* Description: This program will evaluate several sorting algorithms.
 */

import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class SortDriver {

	public static void main(String[] args) {
		Double[][] timeTable1 = timeTableGenerator(100);
		Double[][] timeTable2 = timeTableGenerator(1000);
		Double[][] timeTable3 = timeTableGenerator(10000);
		
		writeTable_timing(timeTable1);
		writeTable_timing(timeTable2);
		writeTable_timing(timeTable3);
		
		writeTable_tValue(timeTable1);
		writeTable_tValue(timeTable2);
		writeTable_tValue(timeTable3);
	}

	public static Double[][] timeTableGenerator(int dataSetSize){
		double startTime;
		double endTime;
		double totalTime;
		
		Integer[] dataSet;
		Integer[] dataSet_tmp;
		
		
		// 10 dataSet + average + standard deviation = 12, 6 different sorting algorithms
		Double[][] timeTable = new Double[6][12];
		
		for (int i=0; i<10;i++){
			// dataSet is an array which has dataSetSize number of random Integer
			dataSet = dataSetGenerator(dataSetSize);
			dataSet_tmp = new Integer[dataSet.length];

			// Selection Sort
			// Notice that dataSet_tmp has to be the deep copy of dataSet!!!
			for(int k=0; k<dataSetSize; k++){
				dataSet_tmp[k] = dataSet[k];	
			}
			startTime = System.currentTimeMillis();
			Sorting.selectionSort( dataSet_tmp, dataSet_tmp.length);
			endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			timeTable[0][i] = totalTime;
			
			// Bubble Sort
			// Notice that dataSet_tmp has to be the deep copy of dataSet!!!
			for(int k=0; k<dataSetSize; k++){
				dataSet_tmp[k] = dataSet[k];	
			}
			startTime = System.currentTimeMillis();
			Sorting.bubbleSort( dataSet_tmp, dataSet_tmp.length);
			endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			timeTable[1][i] = totalTime;
			
			// Insertion Sort
			// Notice that dataSet_tmp has to be the deep copy of dataSet!!!
			for(int k=0; k<dataSetSize; k++){
				dataSet_tmp[k] = dataSet[k];	
			}
			startTime = System.currentTimeMillis();
			Sorting.insertionSort( dataSet_tmp, dataSet_tmp.length);
			endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			timeTable[2][i] = totalTime;
			
			// Merge Sort
			// Notice that dataSet_tmp has to be the deep copy of dataSet!!!
			for(int k=0; k<dataSetSize; k++){
				dataSet_tmp[k] = dataSet[k];	
			}	
			startTime = System.currentTimeMillis();
			Sorting.mergeSort( dataSet_tmp, 0, dataSet_tmp.length-1);
			endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			timeTable[3][i] = totalTime;
			
			// Quick Sort
			// Notice that dataSet_tmp has to be the deep copy of dataSet!!!
			for(int k=0; k<dataSetSize; k++){
				dataSet_tmp[k] = dataSet[k];	
			}
			startTime = System.currentTimeMillis();
			Sorting.quickSort( dataSet_tmp, 0, dataSet_tmp.length-1);
			endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			timeTable[4][i] = totalTime;
			
			// Radix Sort
			// Notice that dataSet_tmp has to be the deep copy of dataSet!!!
			for(int k=0; k<dataSetSize; k++){
				dataSet_tmp[k] = dataSet[k];	
			}
			startTime = System.currentTimeMillis();
			RadixSort.radixSort( dataSet_tmp);
			endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			timeTable[5][i] = totalTime;
			
		}  // end for loop
		
		// Store average time into timeTable[j][10]
		for(int j=0; j<6; j++){
			Double total = 0.0;
			for(int k=0; k<10; k++){
				total += timeTable[j][k];
			}
			timeTable[j][10] = total/10.0;
		}
		
		// Store standard deviation into timeTable[j][11]
		for(int j=0; j<6; j++){
			Double total = 0.0;
			for(int k=0; k<10; k++){
				total += Math.pow((timeTable[j][k]- timeTable[j][10]), 2);
			}
			timeTable[j][11] = Math.sqrt( (total/(10.0-1.0)) );
		}
		return timeTable;
	}
	
	public static void writeTable_timing(Double[][] table)    
	//throws IOException         
	{       	
		try{
			FileWriter writeIt = new FileWriter("timings.dat", true);  // true to enable appending
			PrintWriter out = new PrintWriter(writeIt);
			
			for(int i=0; i< table.length; i++){
				for(int j=0; j< table[0].length-2; j++){
					out.printf("%4.0f", table[i][j]);
					System.out.printf("%4.0f", table[i][j]);
				}
				
				// print average
				out.printf("%8.3f", table[i][10]);
				System.out.printf("%8.3f", table[i][10]);
				
				// print standard deviation
				out.printf("%8.4f", table[i][11]);
				System.out.printf("%8.4f", table[i][11]);
				
				out.printf("\n");
				System.out.printf("\n");
			}
			out.printf("\n");
			System.out.println();
			out.close();
		}
		catch (IOException e)		{
			System.out.printf("IOException error %s.\n", e);
		}
	}  // end of writeData 
	
	public static void writeTable_tValue(Double[][] table)
	//throws IOException         
	{       	
		
		try{
			FileWriter writeIt = new FileWriter("tValue.dat", true);  // true to enable appending
			PrintWriter out = new PrintWriter(writeIt);
			
			for(int i=0; i < table.length; i++){
				for(int j=0; j < i ; j++){
					out.printf("%s", "          ");
					System.out.printf("%s", "          ");
				}
				
				for(int j=i+1; j < table.length; j++){
					double t = tValue(table, i, j);
					out.printf("%10.4f", t);
					System.out.printf("%10.4f", t);
				}
				out.printf("\n");
				System.out.printf("\n");
			}
			
			// The following is for debugging use.
			// One can use it to examine the wanted output
			/*
			for(int i=0; i < table.length; i++){
				for(int j=0; j < table.length; j++){
					double t = tValue(table, i, j);
					out.printf("%10.4f", t);
					System.out.printf("%10.4f", t);
				}
				out.printf("\n");
				System.out.printf("\n");
			}
			out.printf("\n");
			System.out.println();*/
			
			out.close();
		}
		catch (IOException e)		{
			System.out.printf("IOException error %s.\n", e);
		}
	}  // end of writeData 
	
	public static Integer[] dataSetGenerator(int size){
		Random rand = new Random();
		Integer[] dataSet = new Integer[size];
		for (int i=0; i<size; i++){
			dataSet[i]=rand.nextInt(999) + 1;  // interval is [1,999]
		}	
		return dataSet;
		
	}
	
	public static double tValue(Double[][] table, int i, int j){
		double tValue = 0;
		double avg_A = table[i][10];
		double avg_B = table[j][10];
		
		double total =0;
		for(int k=0; k<10; k++){
			total += Math.pow( ((table[i][k]-avg_A)-(table[j][k]-avg_B)) , 2);
		}
		tValue = (avg_A - avg_B)*Math.sqrt(10*(10-1)/total);
		return tValue;
	}
}