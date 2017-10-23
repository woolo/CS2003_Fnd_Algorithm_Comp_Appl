/**
* @author Zimo Chai (Jerry)
* Student ID: 1495687
* Lab Section: 1
* Date: 2/21/2017
* Description: This program serves as the basic implementation of NQueens Problem.
*/
class NQueens {
    /**squares per row or column */
    private int BOARD_SIZE;   
    /** indicate an empty square */
    public static final int EMPTY = 0; 
    /**indicate square contains a queen */
    public static final int QUEEN = -1;  
    /** chess board: each entry <code>board[i][j]</code> of the board
     * can take the following values:
     * <li> QUEEN = -1 : indicating the presence of a queen
     * <li> EMPTY = 0  : indicating an empty cell
     * <li> <code>i>0</code> : where <code>i</code> number of queens that can
     * attack the cell <code>(i,j)</code>
     */
    public int board[][]; 
    /** current number of backtracks */
    private int backTracks;
    /** number of locations that have been checked to be under attack
     * at some point in the search process */
    private int isUnderAttackCalls;
    /** current number of placed queens */
    private int numPlacedQueens;
    
    
    /** creates an empty square board */
    public NQueens(int size) {
	BOARD_SIZE = size;
	board = new int[BOARD_SIZE][BOARD_SIZE];
	backTracks = 0;
	isUnderAttackCalls = 0;
	numPlacedQueens = 0;
    }  // end constructor         

  	
    /**
     * Places queens by calling PlaceQueens with the first column.
     *  @return If a solution is found, each column of the board
     *    contains one queen and method returns true; otherwise,
     *    returns false (no solution exists for a queen anywhere
     *    in column specified).
     */
    public boolean placeQueens() {
	return placeQueens(0);
    }
    
    
    /**
     *  Places queens in the specified column of the board and
     *  recursvily place the queens on the successive columns when
     *  possible
     * @param column where the new queen is added. It is assumed that
     * queens are correctly placed in columns <code>1</code> through
     * <code>column-1</code>
     * @return If a solution is found, each column of the board
     *  contains one queen and method returns true; otherwise, returns
     *  false (no solution exists for a queen anywhere in column
     *  specified).
     */
    private boolean placeQueens(int column) {
    ++numPlacedQueens;
	if (column == BOARD_SIZE) {
	//Notice that column begins counting at 0, so when reaches BOARD_SIZE,
	//it actually has examined all the columns
	    return true;  // base case
	} 
	else {
	    boolean queenPlaced = false;
	    int row = 0;  // number of square in column
	    while ( !queenPlaced && (row < BOARD_SIZE) )  {
		// if square can be attacked
		if (isUnderAttack(row, column)) {
		    ++row;  // consider next row in column
		} // end if
		else { // place queen and consider next column
		    setQueenAndMarks(row, column);
		    queenPlaced = placeQueens(column+1);
		    // if no queen can be placed in the next column,
		    if (!queenPlaced) {
			// backtrack: remove queen placed earlier
			// and try next row in column
			removeQueenAndMarks(row, column);
			++row;
			++backTracks;
		    } // end if
		} // end else
	    } // end while
	    return queenPlaced;
	} // end else
    } // end placeQueens
    
    
    /**
     * Sets a queen at the location indicated by the specified row and
     * column and marks the columns attacked by this queen and no
     * other queen placed in prior columns.
     * Should call markBoard with appropriate arguments
     * @param row where the new queen is located
     * @param column where the new queen is located
     */
    private void setQueenAndMarks(int row, int column) {
    	//Set the queen
    	board[row][column] = QUEEN;
    	//Mark the attacked area
    	markBoard(row, column, EMPTY, column+1);
    }  // end setQueen
    
    
    /**
     * Removes a queen at the location indicated by row and column,
     * and marks in the following columns to denote attack by this
     * queen.
     * <li> Precondition: A queen was placed in this position
     * and it is being removed as part of the backtracking process. 
     * <li> Postcondition: Sets the square on the board in a given row and
     * column to EMPTY.  Also unmark all board positions attacked by
     * this queen and not by any previous queens.
     * Should call markBoard with appropriate arguments
     * @param row where the queen to be removed is located
     * @param column where the queen to be removed is located
     */
    private void removeQueenAndMarks(int row, int column) {
    	//Unset the queen
    	board[row][column] = EMPTY;
    	//Mark the attacked area
    	markBoard(row, column, column+1, EMPTY);
    }  // end removeQueen
    
    private void markBoard (int row, int column, int oldMarker, int newMarker){
    	markForward(row,column,1,oldMarker,newMarker);
    }

    /**
     * Marks the column number <code>col+step</code> to denote the
     * location that can be attacked by the queen being placed.  <li>
     * Precondition: A queen was placed in the position (row, col) and
     * the (col+step)th column is being marked as part of the
     * lookahead process.  
     * <li> Postcondition: Marks up to three
     * squares on the board in column (col+step).  These squares are
     * the one on the same row and the ones on the diagonals emanating
     * from the queen placed at (row, col).  Marks only those squares
     * not marked by a previously placed queen.
     * @param row where the new queen is located
     * @param col where the new queen is located
     * @param step identifies the state of the markup process: the 
     * <code>col+step</code> column will be marked up 
     * @param oldMarker identifies the old marker
     * @param newMarker identifies the new marker
     */
    private void markForward (int row, int col, int step, 
			      int oldMarker, int newMarker) {
    		boolean test1 = (col+step < BOARD_SIZE);
    		boolean test2 = (col+step < BOARD_SIZE & row-step >=0);
    		boolean test3 = (col+step < BOARD_SIZE & row+step < BOARD_SIZE);
	    	//Mark the empty suqares on the same row
	    	if(test1){
	    		if (board[row][col+step] == oldMarker)
	    			board[row][col+step] = newMarker;
	    	}
	    	//Mark up diagonal
	    	if(test2){
	    		if (board[row-step][col+step] == oldMarker)
	    			board[row-step][col+step] = newMarker;
	    	}
	    	//Mark down diagonal
	    	if(test3){
	    		if (board[row+step][col+step] == oldMarker)
	    			board[row+step][col+step] = newMarker;
	    	}
	    	if (test1 | test2 | test3)
	    		markForward(row,col,step+1,oldMarker,newMarker);
	    	
    }
    /**
     * Determines whether the square on the board at a given row and
     *  column is under attack by any queens in the columns 1 through
     *  column-1.  <li> Precondition: Each column between 1 and
     *  column-1 has a queen placed in a square at a specific row.
     *  None of these queens can be attacked by any other queen.
     * @param row of the considered square of the board
     * @param column of the considered square of the board
     * @return If the designated square is under attack, returns true;
     * otherwise, returns false.
     */
    private boolean isUnderAttack(int row, int column) {
	isUnderAttackCalls ++;
	if (board[row][column]!= EMPTY)
	    return true;
	else
	    return false;
    }  // end isUnderAttack
    
    public String getStatsInHTML(){
	return 
	    "Statistics for NQueens on a "+BOARD_SIZE+" x "+BOARD_SIZE
	    +" chess board <br>"
	    +"Number of Back Tracks: "+backTracks +"<br>"
	    +"Number of isUnderAttack() calls : "+isUnderAttackCalls +"<br>"
	    +"Number of times Queens were placed: "+numPlacedQueens +"<br>";
    }
    
    public int getBackTracks(){
    	return backTracks;
    }
    
    public int getIsUnderAttackCalls(){
    	return isUnderAttackCalls;
    }
    
    public int getNumPlacedQueens(){
    	return numPlacedQueens;
    }
} // end NQueens
