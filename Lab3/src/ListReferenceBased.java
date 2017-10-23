/**
* @author Zimo Chai (Jerry)
* Student ID: 1495687
* Lab Section: 1
* Date: 1/24/2017
* Description: Reference-based implementation of ADT list.
* 				Modify the code to implement a doubly linked list.
*/

import java.util.Iterator;

public class ListReferenceBased<E> implements ListInterface<E>, Iterable<E>{

    /** reference to the first element of the list */
    private Node<E> head; 
    /** number of items in list */
    private int numItems; 
    
    /** default constructor */
    public ListReferenceBased() {
	numItems = 0;
	head = null;
    }  // end default constructor
    
    /** constructor with the first item: constructs a list with the
     * specified item as single element of this list
     * @param item first element of the list
     */
    public ListReferenceBased(E item) { 
	numItems = 1;
	Node<E> newNode = new Node<E>(item);
	head = newNode;
	//head.setPrev(head);
    }  // end constructor
    
    /** copy constructor 
     * @param List a ListReferenceBased list
     */
    public ListReferenceBased(ListReferenceBased<E> List) {
	numItems = 0;
	//Notice that List.size() can not be replaced by numItems=List.size()!
	//This is because numItems increase every time add method (validly) called!
	for (int i =1; i <= List.size(); i++){
		//System.out.println("the item get from List is : "+List.get(i));
		add(i, List.get(i));
	}
    }  // end default constructor
    
    /** Tests if this list has no elements.
     * @return <code>true</code> if this list has no elements;
     * <code>false</code> otherwise.
     */
    public boolean isEmpty() {
	return numItems == 0;
    }  // end isEmpty
    
    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list.
     */
    public int size() {
	return numItems;
    }  // end size
    

    /** Locates a specified node in a linked list.
     * <li> Postcondition: 
     * @param index position of the desired node. Assumes that <code>
     * 1 <= index <= numItems+1</code>
     * @return a reference to the desired Node.
     */
    private Node<E> find(int index) {
	Node<E> curr = head;
	for (int skip = 1; skip < index; skip++) {
	    curr = curr.getNext();
	} // end for
	return curr;
    } // end find

    /** get the item located at the specified position in the list 
     * @param index position of the node containing the item
     * @return the Object located at the specified index
     * @throws ListIndexOutOfBoundsException if the index is out of
     * range, i.e. when <code> index <=0 || index > size() </code>
    */ 
    public E get(int index) 
    		throws ListIndexOutOfBoundsException {
    	//System.out.println("index in get method is: "+index);
	if (index >= 1 && index <= size()) {
	    // get reference to node, then data in node
	    Node<E> curr = find(index);
	    E dataItem = curr.getItem();
	    return dataItem;
	} 
	else {
	    throw new ListIndexOutOfBoundsException(
						    "List index out of bounds exception on get");
	} // end if
    } // end get
    
    
    
    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any)
     * and any subsequent elements to the right (adds one to their
     * indices).
     * @param index index at which the specified element is to be
     * inserted.(Notice that the current index ranges from 1 to numItems)
     * @param item element to be inserted.
     * @throws IndexOutOfBoundsException - if index is out of range
     * (index < 0 || index > size()).
     */
    public void add(int index, E item)
	throws ListIndexOutOfBoundsException {
	if (index >= 1 && index <= numItems+1) {
	// Q: Do we still use numItems+1? A: Yes, numItems+1 means the new last one
	    if (index == 1) {
			// insert the new node containing item at beginning of list
			Node<E> newNode = new Node<E>(item, null, head);
			head = newNode;
			//head.setPrev(head);
	    } 
	    else{
			Node<E> prev = find(index-1);
			Node<E> next = find(index);//What if index does not exist right now?! Doesn't matter! Just return null.
			// insert the new node containing item after the node that prev references
			Node<E> newNode = new Node<E>(item, prev, next);// create newNode which links to prev and next.
			
			//Make it doubly
			if (prev != null)//This check do not have to be done, because the situation has been described in index==1.
				prev.setNext(newNode);//prev to newNode
			if (next != null)
				next.setPrev(newNode);//link next to newNode
	    } // end if
	    numItems++;
	} 
	else {
	    throw new ListIndexOutOfBoundsException(
						    "List index out of bounds exception on add");
	} // end if
    }  // end add
    

    /**
     * appends the specified element to the end of this list.
     * @param elt element to be added at the end of the list
     */
    public void append(E item)
    {
	// TO COMPLETE
    	Node<E> prev = find(numItems);
    	Node<E> newNode = new Node<E>(item, prev, null);
		prev.setNext(newNode);
		//head.setPrev(newNode);
		numItems++;
    }//end append
    

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
	if (index >= 1 && index <= numItems) {
	    if (index == 1) {
		// delete the first node from the list
		head = head.getNext();
	    } 
	    else{
		Node<E> prev = find(index-1);
		// delete the node after the node that prev
		// references, save reference to node
		//Node<E> curr = prev.getNext(); 
		prev.setNext(prev.getNext().getNext());
		//Make it doubly
		if (prev.getNext() != null)//in case the one removed is the last item in the list
			prev.getNext().setPrev(prev);
	    } // end if
	    numItems--;
	} // end if
	else {
	    throw new ListIndexOutOfBoundsException(
						    "List index out of bounds exception on remove");
	} // end if
    }   // end remove
    
  
    /**
     * Remove all the elements in this list.
     */
    public void removeAll() {
	// setting head to null causes list to be
	// unreachable and thus marked for garbage 
	// collection
	head = null;
	numItems = 0;
    } // end removeAll
    


    /**
     * delete the the specified element in this list if exists,
     * otherwise state that the item is not in the current
     * list. Shifts any subsequent elements to the left (subtracts one
     * from their indices).
     * @param elt the element, if it exists, to delete
     */
    public void delete(E item){
	//TO COMPLETE
    	int index = contains(item);
    	if (index != -1)
    		remove(index);
    	numItems--;
    }
    
    /**
     * Looks for the index of the specified element in this list. If
     * the element is not present, the method returns <code>-1</code>
     * @param elt the element which index is looked for.
     * @return either the index of the location in the list where the
     * argument is present or <code>-1</code> if the argument is not
     * contained in the list.
     */
    public int contains(E elt){
	// TO COMPLETE
    	for (int i=1; i <= numItems; i++)
    	{
    		if (elt.equals(get(i)))
    			return i;
    	}
    	return -1;
    }
    
    /**
     * Prints all the elements in this list on the console in sequence
     */
    public void display(){
	// TO COMPLETE
    	for (int i=1; i <= numItems; i++)
    	{
    		System.out.printf("%s " ,get(i).toString());
       	}
    	System.out.println();
    }

    /**
     * Prints all the elements in this list on the console in reverse order
     * According to the requirement:
     * Use getNext() and/or getPrev
     * Do not use get();
     */
    public void displayReverse(){
	// TO COMPLETE
    	Node<E> curr = find(numItems);
    	for (int skip = 1; skip <= numItems; skip++) {
    		System.out.printf("%s ", curr.getItem().toString());
    		if (curr.getPrev() != null)//in case index has gone to 1(head).
    			curr = curr.getPrev();
    	} // end for
    	System.out.println();
    }

    public ListReferenceBasedIterator<E> iterator(){
	return  new ListReferenceBasedIterator<E>(head);
    }
    public class ListReferenceBasedIterator<E> implements Iterator<E>{
	Node<E> current;

	public ListReferenceBasedIterator(Node<E> node){
	    current=node;
	}
	
	public boolean hasNext(){
	    return (current!=null);
	}

	public E next(){
	    E elt = current.getItem();
	    current = current.getNext();
	    return elt;
	}

	public void remove(){}
    }
} // end ListReferenceBased
