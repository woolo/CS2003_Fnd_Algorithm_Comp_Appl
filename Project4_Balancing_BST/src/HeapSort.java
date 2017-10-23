//package BinaryTrees;

import BinaryTrees.*;

import java.util.Vector;

public class HeapSort <E extends Comparable<? super E>>{
    
    /** sorts the input vector using heap Sort <ul> <li> iterates
     * through each element of the input vector and inserts each
     * element to the heap by calling {\tt heapInsert}.  <li> deletes
     * each of the inserted items by calling {\tt heapDelete} the
     * appropriate number of times, and fills up the vector with the
     * returned elements.  </ul> If you are using the
     * minheap implementation, this insertion and deletion of all
     * items will produce a list of items sorted by their key
     * attribute values.
     * @param vec input vector
     */
	public void heapSort(Vector<E> vec){
		
	    Heap<E> aHeap = new Heap<E>();
	    int size = vec.size();
	    
		for(int i = 0; i < size; i++){
			aHeap.heapInsert(vec.get(i));
		}
		
		vec.removeAllElements();
		
		for(int i = 0; i < size; i++){
			vec.addElement(aHeap.heapDelete());
		}
		
	}
	
}
