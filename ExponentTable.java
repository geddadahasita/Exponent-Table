import java.util.Scanner;

public class ExponentTable {
	
	public static void main(String[] args) {
		
		//create a Scanner which takes a user input and stores it as an int
		Scanner scn = new Scanner(System.in);
		System.out.print("Enter the size (number of columns) of the exponent chart: ");
		int columnSize = scn.nextInt();
		
		//check if column size is positive
		while(columnSize <= 0) {
			System.out.print("Invalid, enter a new number: ");
			columnSize = scn.nextInt(); 
			
		}
		
		//create a 2D array with 10 rows 
		int exponentTable[][] = new int[10][columnSize];
		
		//assign values to 2D array
		for(int row = 0; row < exponentTable.length; row ++) {
			for(int col = 0; col < exponentTable[0].length; col ++) {
				
				exponentTable[row][col] = (int) Math.pow(row + 1, col + 1);
				
			}
		}
		
		//print 2D array
		for(int i = 0; i < exponentTable.length; i ++) {
			for(int j = 0; j < exponentTable[0].length; j ++) {
				
				System.out.print(exponentTable[i][j] + "\t\t");
			}
			
			System.out.println();
		}		
		
	}
	
}
