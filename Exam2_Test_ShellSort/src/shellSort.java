
public class shellSort {

	public static int countComparison =0;
	
	
	public static void main(String[] args) {
		Integer[] array= {17,-2,2,25,1};
		shellSort(array, array.length);
	}
	
	public static void shellSort(Comparable[] theArray, int n) {
		int loc;
		Comparable nextItem;
		
		System.out.println("The oringinal order:");
		printArray(theArray);
		
		for (int h = n/2; h > 0; h = h/2) {
			for (int unsorted = h; unsorted < n; ++unsorted) {
				nextItem = theArray[unsorted];
				loc = unsorted;
				while ((loc >= h) && 
						countComparison() //This is a way to count function call in while loop
						&& (theArray[loc-h].compareTo(nextItem) > 0)) {
					theArray[loc] = theArray[loc-h];
					loc = loc - h;
				} // end while
				theArray[loc] = nextItem;
			
				//System.out.println("The current order:");
				printArray(theArray);
			} // end for unsorted
			//printArray(theArray);
		} // end for h
		System.out.printf("%d comparison(s) was/were made in the while loop!\n", countComparison);
	}
	
	public static void printArray(Comparable[] theArray){
		for (int index =0; index < theArray.length; index++){
			System.out.printf("%4d",theArray[index]);
		}
		System.out.println();
	}
	
	public static boolean countComparison(){
		countComparison++;
		return true;
	}

}
