/**
* @author Zimo Chai (Jerry)
* Student ID: 1495687
* Date: 2/3/2017
* Description: This program demonstrates that String has been designed to be immutable.
*/

public class String_test {

	public static void main(String[] args) {
		String str = "AAA";
		System.out.printf("The text in str is: %s.\n",str);
		
		String anotherStr = str;
		System.out.printf("The text in anotherStr is: %s.\n",anotherStr);
		
		
		anotherStr = "BBB";
		
		System.out.printf("The text in str is: %s.\n",str);
		System.out.printf("The text in anotherStr is: %s.\n",anotherStr);
		
		
		//https://stackoverflow.com/questions/334518/java-strings-string-s-new-stringsilly
		String s1="foo";
		//literal will go in pool and s1 will refer.

		String s2="foo";
		//this time it will check "foo" literal is already available in StringPool or not 
		//as now it exist so s2 will refer the same literal.

		String s3=new String("foo");
		//"foo" literal will be created in StringPool first 
		//then through string arg constructor String Object will be created 
		//i.e "foo" in the heap due to object creation through new operator then s3 will refer it.
		
		String s4=new String("foo");
		//same as s3 so 
		
		System.out.println(s1==s2);// **true** due to literal comparison.
		
		System.out.println(s3==s4);// **false** due to object
		//comparison(s3 and s4 is created at different places in heap)

		
	}

}
