/*
	If one of the operations is provided an index value that is out of range, an
exception should be thrown. Here is a definition of an exception that can be
used for an out-of-bounds list index called ListIndexOutOfBoundsException.
 */
public class ListIndexOutOfBoundsException 
            extends IndexOutOfBoundsException {
  public ListIndexOutOfBoundsException(String s) {
    super(s);
  }  // end constructor
}  // end ListIndexOutOfBoundsException