/* The ListException is needed when the array
 * storing the list is full.
 * 
 */

public class ListException extends RuntimeException {
  public ListException(String s) {
    super(s);
  }  // end constructor
}  // end ListException