/**
* @author Zimo Chai (Jerry)
* Student ID: 1495687
* Lab Section: 1
* Date: 2/21/2017
* Description: This program will print several statements into the console
* 				 which can be modified into Mathematica code to make plots.
*/
public class DataGenerator {

	public static void main(String[] args) {
		//Print out useful statement which can be used to do plot analysis.
		System.out.println("The following data is related to the number of calls of backTracks.\n"
				+ "One can copy and paste it into Mathematica to plot the data:");
		for (int size = 4; size <= 25; size++){
			NQueens nQueens = new NQueens(size);
			if (nQueens.placeQueens()) {
				System.out.printf("{%d,%d}, ",
						size, nQueens.getBackTracks() );
			}
			else
				System.out.println("Not Sccusess");
		}
		//Blank lines for formatting.
		System.out.println();
		System.out.println();
		//Print out useful statement which can be used to do plot analysis.
		System.out.println("The following data is related to the number of calls of isUnderAttack.\n"
				+ "One can copy and paste it into Mathematica to plot the data:");
		for (int size = 4; size <= 25; size++){
			NQueens nQueens = new NQueens(size);
			if (nQueens.placeQueens()) {
				System.out.printf("{%d,%d}, ",
						size, nQueens.getIsUnderAttackCalls() );
			}
			else
				System.out.println("Not Sccusess");
		}
		//Blank lines for formatting.
		System.out.println();
		System.out.println();
		//Print out useful statement which can be used to do plot analysis.
		System.out.println("The following data is related to the number of calls of placeQueens.\n"
				+ "One can copy and paste it into Mathematica to plot the data:");
		for (int size = 4; size <= 25; size++){
			NQueens nQueens = new NQueens(size);
			if (nQueens.placeQueens()) {
				System.out.printf("{%d,%d}, ",
						size, nQueens.getNumPlacedQueens() );
			}
			else
				System.out.println("Not Sccusess");
		}
	}

}
