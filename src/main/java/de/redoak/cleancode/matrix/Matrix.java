package de.redoak.cleancode.matrix;

import java.text.MessageFormat;

public class Matrix {

    private final int columnSize;
    private final int rowSize;
    //columns x rows
    private final int[][] values;

    private Matrix(int columnSize, int rowSize) {
        this.columnSize = columnSize;
        this.rowSize = rowSize;
        values = new int[columnSize][rowSize];
    }

    public Matrix multiply(Matrix other) throws MatrixCalculationException {

        checkMultiplicationConstraint(this, other);

        Matrix resultMatrix = new Matrix(this.getRowSize(), other.getColumnSize());

        for(int currentResultRow = 0; currentResultRow < this.getRowSize(); currentResultRow++) {
            for(int currentResultColumn = 0; currentResultColumn < other.getColumnSize(); currentResultColumn++) {

                int[] leftRow = this.getRow(currentResultRow);
                int[] rightColumn = other.getColumn(currentResultColumn);

                int result = scalarProduct(leftRow, rightColumn);

                resultMatrix.setValue(currentResultColumn, currentResultRow, result);
            }
        }


        return resultMatrix;
    }

    public int[] getColumn(int column) {

        return this.values[column];
    }

    public int[] getRow(int row) {
        int[] columnValues = new int[this.columnSize];

        for(int i = 0; i < this.columnSize; i++) {
            columnValues[i] = this.getValue(i, row);
        }

        return columnValues;
    }

    private static void checkMultiplicationConstraint(Matrix left, Matrix right) throws MatrixCalculationException {
        if (left.getColumnSize() != right.getRowSize()) {
            throw new MatrixCalculationException(
                    MessageFormat.format(
                            "number of columns of first Matrix must match number of rows of second matrix ({0} != {1})",
                            left.getColumnSize(), right.getRowSize()
                    ));
        }
    }


    /*
    1 2
    3 4
     */
    public static Matrix fromValuesRowWise(int columns, int rows, int... values) throws MatrixCreationException {

        checkIfValueLengthFitsMatrix(columns, rows, values.length);

        Matrix matrix = new Matrix(columns, rows);

        for (int i = 0; i < values.length; i++) {
            int column = i % columns;
            int row = i / columns;
            int value = values[i];
            matrix.setValue(column, row, value);
        }

        return matrix;
    }

    private static void checkIfValueLengthFitsMatrix(int columns, int rows, int length) throws MatrixCreationException {
        if (columns * rows != length) {
            throw new MatrixCreationException(MessageFormat.format(
                    "{0} values passed but expected {1} ({2}*{3}) values.",
                    length, columns * rows, columns, rows));
        }
    }

    private static int scalarProduct(int[] a, int[] b) {

        int result = 0;

        for(int i = 0; i < a.length; i++) {
            result += a[i] * b[i];
        }

        return result;
    }

    public int getValue(int column, int row) {
        return values[column][row];
    }

    public void setValue(int column, int row, int value) {
        this.values[column][row] = value;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public int getRowSize() {
        return rowSize;
    }

    public String prettyPrint() {

        StringBuilder stringBuilder = new StringBuilder();

        for(int row = 0; row < this.rowSize; row++) {
            for(int column = 0; column < this.columnSize; column++) {
                stringBuilder.append(this.values[column][row]).append(" ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
