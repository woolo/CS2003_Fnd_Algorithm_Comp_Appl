/**
* @author Zimo Chai (Jerry)
* Student ID: 1495687
* Lab Section: 1
* Date: 3/26/2017
* Description: This program is the implementation of SortedList.
 */

public class SortedList<E extends Comparable <? super E>>
    implements SortedListInterface<E>{

	ListArrayListBased<E> list;
	
	public SortedList(){
		list = new ListArrayListBased<E>();
	}
	
	public SortedList(E item){
		list = new ListArrayListBased<E>();
		list.add(1, item);
	}
	
    /** Tests if this list has no elements.
     * @return <code>true</code> if this list has no elements;
     * <code>false</code> otherwise.
     */
    public boolean isEmpty(){
    	return list.isEmpty();
    }
    
    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list.
    */
    public int size(){
    	return list.size();
    }
    
    /** Removes all of the elements from this list.
     */
    public void removeAll(){
    	list.removeAll();
    }

      /**
     * Returns the element at the specified position in this list.
     * @param index index of element to return.
     * @return the element at the specified position in this list.
     * @throws IndexOutOfBoundsException - if index is out of range
     * <code>(index < 0 || index > size())</code>.
     */
    public E get(int index) 
    		throws ListIndexOutOfBoundsException{
    	return list.get(index);
    }
    
    
    /** adds a new item in the sorted list.
     * @param newItem new item to be inserted in the list.
     * @throws ListException
     */
    public void sortedAdd(E newItem)
    		throws ListException{
    	if(list.isEmpty()){
    		list.add(1, newItem);
    	}
    	else{
    		list.add(this.locateIndex(newItem), newItem);
    	}
    }
    
    /** remove the first occurrence of the specified item from the list
     * @param anItem is the item to be remove
     */
    public void sortedRemove(E anItem){
    	// Q: Will that be a good implementation that
    	// we let ListArrayListBased to handle any exception?
    	list.delete(anItem);
    }
    
    /** locate the index of the specified element in the list. NB the
     * index of a list starts at index 1.
     * @param anItem is the item which index is wanted.
     */
    public int locateIndex(E anItem){

    	// binary-insertion
    	int index = 1;
    	int low = 1; // List index stars at 1
        int high = list.size();
        while(low <= high){
        	index = (low + high)/2;
        	if (anItem.compareTo(list.get(index)) == 0){
        		break;
        	}
        	else if (anItem.compareTo(list.get(index)) > 0){
        		low = index +1;
        	}
        	else if (anItem.compareTo(list.get(index)) < 0){
        		high = index -1;
        	}
        }
        
        if(anItem.compareTo(list.get(index)) >= 0)
            return index + 1;
        else return index;  	
    	
    }
    
    public void display(){
    	list.display();
    }


	

}  // end class