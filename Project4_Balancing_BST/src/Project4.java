
/**
* @author Zimo Chai (Jerry)
* Student ID: 1495687
* Lab Section: 1
* Date: 4/19/2017
* Description: This program will balance a BST.
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import BinaryTrees.BinarySearchTree;
import BinaryTrees.BinaryTree;
import BinaryTrees.TreeIterator;

import java.util.ArrayList;;

public class Project4 {

	private static Comparable<StudentGPA> student;
	private static BinarySearchTree BST;
	private static BinaryTree<Comparable<StudentGPA>> BT;
	private static ArrayList<Comparable<StudentGPA>> studentList;

	public static void main(String[] args) {

		// Read information to a Binary Search Tree
		try {
			BST = new BinarySearchTree<StudentGPA>();
			Scanner scanLine;
			Scanner input = new Scanner(new File("students.in"));
			while (input.hasNextLine()) {
				// Read one line and convert to a student
				scanLine = new Scanner(input.nextLine());
				int id = Integer.parseInt(scanLine.next());
				String name = scanLine.next();
				double GPA = Double.parseDouble(scanLine.next());
				if (scanLine.hasNext()) { // if it is a graduate student
					String advisor = scanLine.next();
					student = new GraduateStudentGPA(id, name, GPA, advisor);
				} else {
					student = new StudentGPA(id, name, GPA);
				}			
			    // Put the student into BST
			    BST.insert(student);	
			} // end while loop
			input.close();
		} catch (IOException e) {
			System.out.println("IOException in reading input file!!!" + e);
		}

		
		// inoder traversal the BST and store data in an ArrayList
		studentList = new ArrayList<Comparable<StudentGPA>>();
		TreeIterator<Comparable<StudentGPA>> BSTIterator = new TreeIterator<Comparable<StudentGPA>>(BST, 0);  // 0 means in-oder traversal
		while(BSTIterator.hasNext()){
			studentList.add((Comparable<StudentGPA>) BSTIterator.next());
		}
		
		System.out.println("The sequence in the arrayList:");
		// debug use: print the students in order of their GPA
		for (int i = 0; i < studentList.size(); i++) {
			System.out.println(studentList.get(i));
		}
		
		System.out.println();
		System.out.println("Inoder traversal the Balanced Binary Tree (linear-display):");
		// generate a balanced BST using the data from the ArrayList
		BT = BalancedBST(studentList, studentList.size());
		
		// inoder traversal the BT and print data
		TreeIterator<Comparable<StudentGPA>> BTIterator = new TreeIterator<Comparable<StudentGPA>>(BT, 0);  // 0 means in-oder traversal
		while(BTIterator.hasNext()){
			System.out.println(BTIterator.next());
		}
		
		System.out.println();
		System.out.println("Inoder traversal the Balanced Binary Tree (layer-display):");
		BTIterator.printInorder();
	}
	
	public static BinaryTree<Comparable<StudentGPA>> BalancedBST(ArrayList<Comparable<StudentGPA>> list, int index){
		BinaryTree<Comparable<StudentGPA>> BT = null; 
		if(index > 0){
			BT = new BinaryTree<Comparable<StudentGPA>>(list.remove(index/2));
			
			BinaryTree<Comparable<StudentGPA>> leftSubTree = BalancedBST(list, index/2);
			if (leftSubTree != null){
				BT.attachLeftSubtree(leftSubTree);
			}
			BinaryTree<Comparable<StudentGPA>> rightSubTree = BalancedBST(list, (index-1)/2);
			if (rightSubTree != null){
				BT.attachRightSubtree(rightSubTree);
			}
		}
		return BT;
	}

}
