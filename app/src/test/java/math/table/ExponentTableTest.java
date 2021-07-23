package math.table;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExponentTableTest {

    @Test
    void getColumnSize() {
        List<Integer> inputs = list(-3, 0, 42);
        StringBuilder output = new StringBuilder();

        int columnSize = ExponentTable.getColumnSize(inputs.iterator()::next, output::append);

        assertEquals(42, columnSize);
        assertEquals("Enter the size (number of columns) of the exponent chart:\n" +
                     "Invalid, enter a new number:\n" +
                     "Invalid, enter a new number:\n",
                     output.toString());
    }

    @Test
    void newExponentTable() {
        assertArrayEquals(new int[][]{{1, 1, 1}, {2, 4, 8}, {3, 9, 27}},
                          ExponentTable.newExponentTable(3, 3));
    }

    @Test
    void print() {
        StringBuilder output = new StringBuilder();

        ExponentTable.print(output::append, new int[][]{{1, 1, 1}, {2, 4, 8}, {3, 9, 27}});

        assertEquals("1\t\t1\t\t1\t\t\n" +
                     "2\t\t4\t\t8\t\t\n" +
                     "3\t\t9\t\t27\t\t\n",
                     output.toString());
    }

    // Simple generic function to create list of any type
    @SafeVarargs static <T> List<T> list(T... values) {
        return new ArrayList<>(asList(values));
    }
}
