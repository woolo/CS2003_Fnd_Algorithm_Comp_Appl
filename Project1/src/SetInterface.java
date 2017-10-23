
public interface SetInterface<E> {
    
    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list.
    */
    public int Setsize();
    
    /**
     * prints out the contents of the set
     */
    public void print();
    
    
    /**
     * takes an element of type E and inserts it into the set
     * @param item element to be inserted.
     */
    public void insert(E item) ;
    		//throws ListException;

    /**
     * takes an array of elements of type E and inserts them into the set
     * @param array an array of elements of type E to be inserted.
     */
    public void arrayInsert(E[] array) ;
    		//throws ListException;//Q: Do I need to write a throws Exception?
    
    /**
     * takes a Set as an argument and returns a new Set 
     * that is the union of the current set and the argument
     * @param aSet element to be combined together.
     * @return a new set that is the union of the current set and the argument
     */
    public Set<E> union(Set<E> aSet);

    /**
     * a method that takes a Set as an argument and returns a new Set that is the inter-
     * section of the current set and the argument
     * @param aSet element to be intersected.
     * @return a new set that is the intersection of the current set and the argument
     */
    public Set<E> intersection(Set<E> aSet);
    
    
    /**
     * takes a Set as an argument and returns a new Set that is the difference
     * of the current set and the argument
     * @param aSet element to be combined together.
     * @return a new set that is different from the current set and the argument
     */
    public Set<E> difference(Set<E> aSet);
    
    /**
     * takes an element of type E and returns a boolean value depending on whether
     * the given E is contained in the current set or not.
     * @param ele element to be checked.
     * @return E is in the current set or not
     */
    public boolean in(E ele);

}
