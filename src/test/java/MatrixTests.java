import de.redoak.cleancode.matrix.Matrix;
import de.redoak.cleancode.matrix.MatrixCalculationException;
import de.redoak.cleancode.matrix.MatrixCreationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatrixTests {


    @Test
    public void testMatrixFromValuesRowWise() throws MatrixCreationException {

        Matrix resultMatrix = Matrix.fromValuesRowWise(2, 2, 1,2,3,4);

        assertEquals(1, resultMatrix.getValue(0,0));
        assertEquals(2, resultMatrix.getValue(1,0));
        assertEquals(3, resultMatrix.getValue(0,1));
        assertEquals(4, resultMatrix.getValue(1,1));


        Matrix verticalVector = Matrix.fromValuesRowWise(1, 4, 1, 2, 3, 4);

        assertEquals(1, verticalVector.getValue(0,0));
        assertEquals(2, verticalVector.getValue(0,1));
        assertEquals(3, verticalVector.getValue(0,2));
        assertEquals(4, verticalVector.getValue(0,3));

        Matrix horizontalVector = Matrix.fromValuesRowWise(4, 1, 1,2,3,4);

        assertEquals(1, horizontalVector.getValue(0,0));
        assertEquals(2, horizontalVector.getValue(1,0));
        assertEquals(3, horizontalVector.getValue(2,0));
        assertEquals(4, horizontalVector.getValue(3,0));

    }

    @Test
    public void testMatrixFromValuesRowWiseIncorrectMatrixSize() {

        Assertions.assertThrows(MatrixCreationException.class,
                () -> Matrix.fromValuesRowWise(2,2, 1,2,3));
    }


    @Test
    public void testMultiplyIncorrectMatrixSizes() throws MatrixCreationException {
        Matrix left = Matrix.fromValuesRowWise(2, 2, 1, 2,3, 4);
        Matrix right = Matrix.fromValuesRowWise(2, 3, 1, 2, 3, 4, 5, 6);

        Assertions.assertThrows(MatrixCalculationException.class,
                () -> left.multiply(right));
    }

    @Test
    public void testMultiply() throws MatrixCreationException, MatrixCalculationException {
        Matrix left = Matrix.fromValuesRowWise(3, 2, 1, 2,3, 4, 5, 6);
        Matrix right = Matrix.fromValuesRowWise(2, 3, 1, 2, 3, 4, 5, 6);

        final Matrix multiplicationResult = left.multiply(right);

        assertEquals(22, multiplicationResult.getValue(0, 0));
        assertEquals(28, multiplicationResult.getValue(1, 0));
        assertEquals(49, multiplicationResult.getValue(0, 1));
        assertEquals(64, multiplicationResult.getValue(1, 1));
    }

    @Test
    public void testGetColumn() throws MatrixCreationException {
        Matrix matrixUnderTest = Matrix.fromValuesRowWise(2, 2, 1,2,3,4);
        int[] expectedColumn = {1,3};

        assertArrayEquals(expectedColumn, matrixUnderTest.getColumn(0));
    }
}
