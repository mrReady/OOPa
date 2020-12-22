import static java.lang.System.out;

public class Application {
    public static void main(String[] args) {
        Matrix A = new Matrix(3, 2);
        Matrix B = new Matrix(2, 3);

        A.set(1, 0, 0);
        A.set(2, 0, 1);
        A.set(3, 1, 0);
        A.set(4, 1, 1);
        A.set(5, 2, 0);
        A.set(6, 2, 1);

        B.set(1, 0, 0);
        B.set(2, 0, 1);
        B.set(3, 0, 2);
        B.set(4, 1, 0);
        B.set(5, 1, 1);
        B.set(6, 1, 2);

        Matrix C = A.multiply(B);

        out.println("Matrix A:");
        out.println(A.toString());

        out.println("Matrix B:");
        out.println(B.toString());

        out.println("Matrix C = A * B:");
        out.println(C.toString());
        out.println("C(0, 2) = " + C.get(0, 2));
        out.println("Matrix C dimension: " + C.getRows() + "x" + C.getColumns());
        out.println("det(C) = " + C.det() + "\n");

        Matrix D = A.multiply(3);
        out.println("Matrix D = 3 * A:");
        out.println(D.toString());
        out.println("D = A - D:");
        D = A.minus(D);
        out.println(D.toString());
        out.println("D = A + D:");
        D = A.plus(D);
        out.println(D.toString());

        out.println("A == D?\n" + A.equals(D));
        D = D.multiply(-1);
        out.println("D *= -1");
        out.println("A == D?\n" + A.equals(D));
    }
}
