import org.jetbrains.annotations.NotNull;

public class Matrix {
    private final int[][] matrix;
    public final int rows;
    public final int columns;

    private static final String INCORRECT_DIMENSION_MESSAGE = "Matrix dimension has to be greater than 0";

    private static final String INCORRECT_INDICES_MESSAGE = "Indices mustn't be negative " +
            "and have to be less than the matrix dimension";

    private static final String NOT_EQUAL_DIMENSIONS_MESSAGE = "Matrices dimensions aren't equals";

    private static final String INCORRECT_DIMENSIONS_TO_MULTIPLY = "Amount of columns in the first matrix " +
            "is not equal to amount of rows in the second one";

    private static final String NOT_SQUARE_MATRIX = "Not square matrix is provided to calculate determinant";

    public Matrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0)
            throw new IllegalArgumentException(INCORRECT_DIMENSION_MESSAGE);

        matrix = new int[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    public void set(int value, int row, int column) {
        if (row < 0 || column < 0 || row >= rows || column >= columns)
            throw new IllegalArgumentException(INCORRECT_INDICES_MESSAGE);

        matrix[row][column] = value;
    }

    public int get(int row, int column) {
        if (rows < 0 || columns < 0 || row >= rows || column >= columns)
            throw new IllegalArgumentException(INCORRECT_INDICES_MESSAGE);

        return matrix[row][column];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Matrix plus(@NotNull Matrix B) {
        if (rows != B.rows || columns != B.columns)
            throw new IllegalArgumentException(NOT_EQUAL_DIMENSIONS_MESSAGE);

        Matrix A = this;
        Matrix C = new Matrix(A.rows, A.columns);

        int value;
        for(int i = 0; i < A.rows; i++) {
            for(int j = 0; j < A.columns; j++) {
                value = A.get(i, j) + B.get(i, j);
                C.set(value, i, j);
            }
        }

        return C;
    }

    public Matrix minus(@NotNull Matrix B) {
        if (rows != B.rows || columns != B.columns)
            throw new IllegalArgumentException(NOT_EQUAL_DIMENSIONS_MESSAGE);

        Matrix A = this;
        Matrix C = new Matrix(A.rows, A.columns);

        int value;
        for(int i = 0; i < A.rows; i++) {
            for(int j = 0; j < A.columns; j++) {
                value = A.get(i, j) - B.get(i, j);
                C.set(value, i, j);
            }
        }

        return C;
    }

    public Matrix multiply(@NotNull Matrix B) {
        if (columns != B.rows)
            throw new IllegalArgumentException(INCORRECT_DIMENSIONS_TO_MULTIPLY);

        Matrix A = this;
        Matrix C = new Matrix(A.rows, B.columns);

        int value = 0;
        for(int i = 0; i < A.rows; i++) {
            for(int j = 0; j < B.columns; j++) {
                for(int k = 0; k < A.columns; k++) {
                    value += A.get(i, k) * B.get(k, j);
                }

                C.set(value, i, j);
                value = 0;
            }
        }

        return C;
    }

    public Matrix multiply(int scalar) {
        Matrix A = this;
        Matrix C = new Matrix(A.rows, A.columns);

        int value;
        for(int i = 0; i < A.rows; i++) {
            for(int j = 0; j < A.columns; j++) {
                value = scalar * A.get(i, j);
                C.set(value, i, j);
            }
        }

        return C;
    }

    public int det() {
        if (rows != columns)
            throw new IllegalArgumentException(NOT_SQUARE_MATRIX);
        return calculateDeterminant(matrix);
    }

    private int calculateDeterminant(int[][] arr) {
        int result = 0;
        if (arr.length == 1) {
            result = arr[0][0];
            return result;
        }
        if (arr.length == 2) {
            result = arr[0][0] * arr[1][1] - arr[0][1] * arr[1][0];
            return result;
        }
        for (int i = 0; i < arr[0].length; i++) {
            int[][] temp = new int[arr.length - 1][arr[0].length - 1];

            for (int j = 1; j < arr.length; j++) {
                for (int k = 0; k < arr[0].length; k++) {
                    if (k < i) {
                        temp[j - 1][k] = arr[j][k];
                    } else if (k > i) {
                        temp[j - 1][k - 1] = arr[j][k];
                    }
                }
            }
            result += arr[0][i] * Math.pow(-1, i) * calculateDeterminant(temp);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        Matrix that = (Matrix) obj;
        if (rows != that.rows || columns != that.columns) return false;

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if (matrix[i][j] != that.get(i, j)) return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
               str.append(matrix[i][j] + " ");
            }
            str.append("\n");
        }
        
        str.append("\n");
        return str.toString();
    }
}
