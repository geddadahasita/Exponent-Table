package math.table;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.IntSupplier;

/**
 * Hasita,
 *
 * Your Exponent table was well designed, and you should not change it. The
 * changes I made are to teach you some new tools and approaches to software
 * development.
 *
 * The changes I have made are to introduce three new concepts:
 *
 * 		1) build tooling
 * 		2) unit testing
 * 		3) functional programming
 * 		4) dependency injection
 *
 * Build tools are used in all software projects. In Java, the two most popular
 * are Maven and Gradle. This project uses Gradle - see gradle.org for more info.
 *
 * Testing is crucial part of software development. There are many types of
 * testing, but it usually starts with unit testing. JUnit is the most popular
 * Java framework. In most orginizations, tests part of the build, and must run
 * successfully before code is released to production.
 *
 * Functional programming treats functions as first class objects. In order to
 * unit test the code, I extracted the getColumnSize(), newExponentTable(), and
 * print() methods. These methods accept functional interfaces to allow
 * dependency injection. The main class still uses Scanner::nextInt and
 * PrintStream::print to satisfy to dependencies. The ExponentTableTest,
 * however, uses List.iterator()::next and StringBuilder::append, allowing the
 * unit test to verify all the functions are working as expected.
 */

public class ExponentTable {

	public static void main(String[] args) {
		//create a Scanner which to read User Input
		Scanner scanner = new Scanner(System.in);

		IntSupplier      intReader   = scanner::nextInt;
		Consumer<String> linePrinter = System.out::print;

		int columnSize = getColumnSize(intReader, linePrinter);

		//create a 2D array with 10 rows
		int[][] exponentTable = newExponentTable(columnSize, 10);

		//print 2D array
		print(linePrinter, exponentTable);
	}

	static int getColumnSize(IntSupplier reader, Consumer<String> printer) {
		printer.accept("Enter the size (number of columns) of the exponent chart:\n");
		int columnSize = reader.getAsInt();
		//check if column size is positive
		while (columnSize <= 0) {
			printer.accept("Invalid, enter a new number:\n");
			columnSize = reader.getAsInt();
		}
		return columnSize;
	}

	static int[][] newExponentTable(int columnSize, int rowSize) {
		int[][] exponentTable = new int[rowSize][columnSize];

		//assign values to 2D array
		for (int row = 0; row < exponentTable.length; row++) {
			for (int col = 0; col < exponentTable[0].length; col++) {
				// Use double representation of 1 to implicitly cast arguments of Math.pow()
				exponentTable[row][col] = (int) Math.pow(row + 1D, col + 1D);
			}
		}
		return exponentTable;
	}

	static void print(Consumer<String> printer, int[][] exponentTable) {
		for (int[] ints : exponentTable) {
			for (int j = 0; j < exponentTable[0].length; j++) {
				printer.accept(ints[j] + "\t\t");
			}
			printer.accept("\n");
		}
	}
}
