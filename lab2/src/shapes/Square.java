package shapes;

public class Square implements Shape {

    public final double a;

    public static final String INCORRECT_SIDES_MESSAGE = "Square with these sides doesn't exist";

    public Square(double a) {
        if (a <= 0)
            throw new IllegalArgumentException(INCORRECT_SIDES_MESSAGE);
        this.a = a;
    }

    @Override
    public double calcArea() {
        return a * a;
    }

    @Override
    public double calcPerimeter() {
        return 4 * a;
    }

    public double getA() {
        return a;
    }

    @Override
    public String toString() {
        return "Square (a = " + a + "):\n" +
                "Area = " + calcArea() + "\n" +
                "Perimeter = " + calcPerimeter() + "\n";
    }
}
