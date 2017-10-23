/**
* @author Zimo Chai (Jerry)
* Student ID: 1495687
* Lab Section: 1
* Date: 1/14/2017
* Description: Array-based implementation of the ADT list.
*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public  class ListArrayListBased<E> implements ListInterface<E>, Iterable<E> {


    /** array on which the list is based */
  
    public ArrayList<E> items = new ArrayList<E>();
    
    /** default Constructor */
    //TO COMPLETE
    public ListArrayListBased(){
    	
    }
    
    /** constructor with the first item: constructs a list with the
     * specified item as single element of this list
     * @param item first element of the list
     */
    // TO COMPLETE
    public ListArrayListBased(E item){
    	items.add(0,item);
    }
    
    /** copy constructor: create a duplicate of the specified list
     * @param list to be copied
     */
    //TO COMPLETE
    public ListArrayListBased(ListArrayListBased<E> aList){
    	for (int i = 0; i < aList.size(); i++){
    		items.add(i,aList.get(i+1));
    	}
    }
    
     /** Tests if this list has no elements.
     * @return <code>true</code> if this list has no elements;
     * <code>false</code> otherwise.
     */
    public boolean isEmpty() {
    	return items.isEmpty();
    } // end isEmpty
    
    
    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list.
     */
    public int size() {
    	return items.size();
    }  // end size
    
    /**
     * Remove all the elements in this list.
     */
    public void removeAll() {
    	items.clear();
    } // end removeAll


    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any)
     * and any subsequent elements to the right (adds one to their
     * indices).
     * @param index index at which the specified element is to be
     * inserted. 
     * @param item element to be inserted.
     * @throws IndexOutOfBoundsException - if index is out of range
     * (index < 0 || index > size()).
     */
    public void add(int index, E item) 
	throws  ListIndexOutOfBoundsException {
    	//Notice that the index of a ArrayListBased list starts at 1, not 0!
    	//This is because we assume the client user is common people 
    	//who get used to the index that is from 1 to size()+1.
		if (index >= 1 && index <= items.size()+1) {
		    items.add(index-1,item);// Since item is an arrayList, the index of it starts from 0!
		} 
		else {  // index out of range
		    throw new ListIndexOutOfBoundsException(
							    "ListIndexOutOfBoundsException on add");//Q: What if I use try-catch? Won't it be simpler in structure?
	}  // end if
    } //end add
    

    /**
     * appends the specified element to the end of this list.
     * @param elt element to be added at the end of the list
     */
    // TO COMPLETE
    public void append(E elt){
    	items.add(items.size(),elt);
    }
    /**
     * Returns the element at the specified position in this list.
     * @param index index of element to return.
     * @return the element at the specified position in this list.
     * @throws IndexOutOfBoundsException - if index is out of range
     * (index < 0 || index > size()).
     */
    public E get(int index) 
	throws ListIndexOutOfBoundsException {
		if (index >= 1 && index <= items.size()) {//The index here starts at 1, not 0!
		    return items.get(index-1);
		}
		else  {  // index out of range
		    throw new ListIndexOutOfBoundsException(
							    "ListIndexOutOfBoundsException on get");
	}  // end if
    } // end get
   

    /**
     * Removes the element at the specified position in this
     * list. Shifts any subsequent elements to the left (subtracts one
     * from their indices).
     * @param index the index of the element to remove
     * @throws IndexOutOfBoundsException - if index is out of range
     * (index < 0 || index > size()).
     */
    public void remove(int index) 
	throws ListIndexOutOfBoundsException {
		if (index >= 1 && index <= items.size()) {
		    // delete item by shifting all items at 
		    // positions > index toward the beginning of the list
		    // (no shift if index == size)
		    items.remove(index-1);
		}
		else {  // index out of range
		    throw new ListIndexOutOfBoundsException(
							    "ListIndexOutOfBoundsException on remove");
	}  // end if
    } //end remove
    

    /**  delete
     * delete the the specified element in this list if exists. Shifts
     * any subsequent elements to the left (subtracts one from their
     * indices).
     * @param elt the element, if it exists, to delete
     */
    // TO COMPLETE
    public void delete(E elt){
    	if (items.contains(elt))// Notice that the contains() method we used here is from ArrayList object, not ListArrayListBased!
    		items.remove(elt);//Notice that the shift operation is done by the remove method automatically.
    	else System.out.printf("No matched element found in the list! Nothing deleted!\n");
    }
        
    /** contains
     * Looks for the index of the specified element in this list. If
     * the element is not present, the method returns <code>-1</code>
     * @param elt the element which index is looked for.
     * @return either the index of the location in the list where the
     * argument is present or <code>-1</code> if the argument is not
     * contained in the list.
     */
    // TO COMPLETE
    public int contains(E elt){
	    // find the element (use a random index to start the search)
	    Random generator = new Random();
	    int itemsSize = items.size();  
	    int index = generator.nextInt(itemsSize);
	    for(int i = 0; i < itemsSize; i++){
			E element = items.get((index+i)%itemsSize);
			if(elt.equals(element))
			    return ((index+i)%itemsSize + 1);
	    }
	    return -1;
    }
    /** display
     * Prints all the elements in this list on the console in sequence
     */
    // TO COMPLETE
    public void display(){
    	for(int i=0;i<items.size();i++){
    		System.out.printf("%s ", items.get(i) );
	    }
    	System.out.println();
    }

    /** method to make the class iterable */

    public Iterator<E> iterator(){
    	return items.iterator();
    }


	/*@Override
	public void append(E elt) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(E elt) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int contains(E elt) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}*/

}  // end ListVectorBased
