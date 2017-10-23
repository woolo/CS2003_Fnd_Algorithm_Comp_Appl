/**
* @author Zimo Chai (Jerry)
* Student ID: 1495687
* Lab Section: 1
* Date: 2/17/2017
* Description: This program will and evaluates postfix expressions contained in a file.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab7 {
	public static void main( String [] args ){
		
		/*Read a valid file from user input.*/
		boolean tryAgain;
		Scanner tokens = null;
		   
		do{	
	   	   Scanner console = new Scanner( System.in );         
		   System.out.print( "Please enter the file name to read: " );         			   
		   String inFile = console.next();  
		   tryAgain = false;
			   
		   try{
		       File file = new File( inFile );// connect to the file         
		       tokens = new Scanner( file ); 
		       }
			   catch (FileNotFoundException er)
			   {
				   System.out.printf( "Error Opening file %s, %s\n"
						   + "How about trying another file?\n\n", inFile, er );
				   tryAgain = true;
			   }
		   }while (tryAgain);	
		
		/*  For each line of the file, read the postfix expression,
		 *  call the method evaluatePostfix on this expression, 
		 *  and print the expression and its evaluation on the console.
		*/
       
		while(tokens.hasNext()){
			String expression = tokens.nextLine();
			int evaluation = evaluatePostfix(expression);
			System.out.printf("The expression is: %s\nThe evaluation is: %d\n\n",
					expression, evaluation);
			System.out.println();
		}
	}
	
	public	static	int evaluatePostfix(String	expr){
		StackVectorBased<Integer> stack = new StackVectorBased<Integer>();
		Scanner token = new Scanner(expr);
		while(token.hasNext()){
			if (token.hasNextInt()){
				stack.push(token.nextInt());
			}
			else{
				int num2 = stack.pop();
				int num1 = stack.pop();
				String operand = token.next();
				
				if(operand.equals("+"))
					stack.push(num1 + num2);
				if(operand.equals("-"))
					stack.push(num1 - num2);
				if(operand.equals("*"))
					stack.push(num1 * num2);
				if(operand.equals("/"))
					stack.push(num1 / num2);
			}
		}
		return stack.pop();
	}
}

