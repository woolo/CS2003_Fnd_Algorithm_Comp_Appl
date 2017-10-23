/**
* @author Zimo Chai (Jerry)
* Student ID: 1495687
* Lab Section: 1
* Date: 4/11/2017
* Description: This program will perform the TreeSort to sort students based on their GPA.
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import BinaryTrees.BinarySearchTree;
import BinaryTrees.TreeIterator;

public class Lab13 {
	
	private static Comparable<StudentGPA> student;
	private static BinarySearchTree BST = new BinarySearchTree<StudentGPA>();
	
	public static void main(String[] args) {
		
		// Read information to a Binary Search Tree
		try {
		    Scanner scanLine;
		    Scanner input = new Scanner(new File("students.in"));
		    while (input.hasNextLine()){
		    	
				// Read one line and convert to a student
				scanLine = new Scanner(input.nextLine());
			    int id = Integer.parseInt(scanLine.next());
			    String name = scanLine.next();
			    double GPA = Double.parseDouble(scanLine.next());
			    if(scanLine.hasNext()){  // if it is a graduate student
			    	String advisor = scanLine.next();
			    	student = new GraduateStudentGPA(id, name, GPA, advisor);
			    }
			    else{
			    	student = new StudentGPA(id, name, GPA);
			    }
			    
			    // Put the student into BST
			    BST.insert(student);
		    }
		    input.close();
		} 
		catch (IOException e) {
		    System.out.println("IOException in reading input file!!!"+e);
		}	
		
		// print the students in order of their GPA
		TreeIterator BSTIterator = new TreeIterator(BST, 0);  // 0 means in-oder traversal
		while(BSTIterator.hasNext()){
			System.out.println(BSTIterator.next());
		}

	}

}
