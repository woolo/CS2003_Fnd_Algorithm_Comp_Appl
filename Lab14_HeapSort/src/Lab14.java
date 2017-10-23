
/**
* @author Zimo Chai (Jerry)
* Student ID: 1495687
* Lab Section: 1
* Date: 4/11/2017
* Description: This program will perform the HeapSort to sort students based on their GPA.
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Lab14 {

	private static Comparable<StudentGPA> student;
	private static Vector<Comparable<StudentGPA>> studentVector;

	public static void main(String[] args) {

		// Read information to a Binary Search Tree
		try {
			studentVector = new Vector<Comparable<StudentGPA>>();
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

				// Put the student into the Vector
				studentVector.addElement(student);
			} // end while loop
			input.close();
		} catch (IOException e) {
			System.out.println("IOException in reading input file!!!" + e);
		}

		// do the Heap Sort
		HeapSort sorter = new HeapSort();
		sorter.heapSort(studentVector);

		// print the students in order of their GPA
		for (int i = 0; i < studentVector.size(); i++) {
			System.out.println(studentVector.get(i));
		}

	}

}
