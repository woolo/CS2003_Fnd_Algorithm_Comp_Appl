/**
* @author Zimo Chai (Jerry)
* Student ID: 1495687
* Lab Section: 1
* Date: 1/28/2017
* Description: implement  the  functionalities  of  the  Set  Class,  
* 				as  given  below,  in  Java  by  using  a  private
* 				data  member  of  type ListReferenceBased.
*/

public class Set<E> implements SetInterface<E> {
	
	/** All the functionalities in Set class is implemented
	 * 	by the private <code>ListReferenceBased<E><code> data member
	*/
	private ListReferenceBased<E> aList;
	
    /** default constructor */
    public Set() {
	aList = new ListReferenceBased<E>();
    }  // end default constructor
   
    /** Constructor that takes a single argument of type E and initializing the set to contain that object
     * @param item: item that is inserted in this new set.
    */
    public Set(E item){
    aList = new ListReferenceBased<E>(item);
    }

    /** copy constructor: create a new Set that is a copy of the
     * specified Set
     * @param aSet: Set copied
     */
    public Set(Set<E> aSet){
    aList = new ListReferenceBased<E>(aSet.aList);
    
    //Notice that below is not the proper implementation.
    //If the object you are trying to copy also has an object as a field
    //we should use the "field object"'s copy constructor, just like code above
	/*for (E item: aSet.aList)
		insert(item);*/
    }
	
	
	/**
     * Returns the number of elements in this list.
     * @return the number of elements in this list.
    */
    public int Setsize(){
    	return aList.size();
    }
    
    /**
     * prints out the contents of the set
     */
    public void print(){
    	aList.display();
    }
    
    
    /**
     * takes an element of type E and inserts it into the set
     * @param item element to be inserted.
     */
    public void insert(E item){ 
	if (in(item) == false)//Check if the item is already in the set
    		aList.append(item);
    }

    /**
     * takes an element of type E and inserts it into the set
     * @param array element to be inserted.
     */
    public void arrayInsert(E[] array){
    	for (E item : array){
    		if (in(item) == false)//Check if the item is already in the set
        		aList.append(item);
    	}
    		
    }
    
    /**
     * takes a Set as an argument and returns a new Set 
     * that is the union of the current set and the argument
     * @param aSet a Set to be combined together.
     * @return a new set that is the union of the current set and the argument
     */
    public Set<E> union(Set<E> aSet){
    	Set<E> aNewSet = new Set<E>(aSet); 

    	for (E item: this.aList)
    	{
    		aNewSet.insert(item);
    	}
    	return aNewSet;
    }

    /**
     * a method that takes a Set as an argument and returns a new Set that is the inter-
     * section of the current set and the argument
     * @param aSet a Set to be intersected.
     * @return a new set that is the intersection of the current set and the argument
     */
    public Set<E> intersection(Set<E> aSet){
    	//System.out.println("An intersection starts:");
    	Set<E> aNewSet = new Set<E>(); 
    	for (E item: this.aList){
    		if (aSet.in(item)){//Check if the item in this.aList is also in the aSet.
    			aNewSet.insert(item);
    			//System.out.println(item);
    		}
    	}
    	
    	//Another way to implement the for loop is:
    	//Notice that this part has not been revised, which means it may accesses
    	//the private field of parameter aSet, which is not recommended.
    	/*for (int i=1; i<=aSet.Setsize();i++){
    		//System.out.println(aSet.aList.get(i));
    		if (this.in(aSet.aList.get(i))){
    			aNewSet.insert(aSet.aList.get(i));
    			//System.out.println(aSet.aList.get(i));
    		}
    	}*/
    	return aNewSet;
    }
    
    
    /**
     * takes a Set as an argument and returns a new Set that is the difference
     * of the current set and the argument
     * @param aSet a Set to be combined together.
     * @return a new set that is different of the current set and the argument
     */
    public Set<E> difference(Set<E> aSet){
    	
    	Set<E> intersectionSet = aSet.intersection(this);
 
    	Set<E> aNewSet = new Set<E>(this);
    	
    		for (E itemInIntersecion: intersectionSet.aList){
    			//if (aNewSet.in(itemInIntersecion))//Do not have to write this.
    				aNewSet.aList.delete(itemInIntersecion);
    		}
    		
    	return aNewSet;
    }
    
    /**
     * takes an element of type E and returns a boolean value depending on whether
     * the given E is contained in the current set or not.
     */
    public boolean in(E ele){
    	return (aList.contains(ele) != -1);
    }
}
