package shapes;

public class Rectangle implements Shape {

    public final double a;
    public final double b;

    public static final String INCORRECT_SIDES_MESSAGE = "Rectangle with these sides doesn't exist";

    public Rectangle(double a, double b) {
        if (a <= 0 || b <= 0)
            throw new IllegalArgumentException(INCORRECT_SIDES_MESSAGE);

        this.a = a;
        this.b = b;
    }

    @Override
    public double calcArea() {
        return a * b;
    }

    @Override
    public double calcPerimeter() {
        return 2 * (a + b);
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    @Override
    public String toString() {
        return "Rectangle (a = " + a + ", b = " + b + "):\n" +
                "Area = " + calcArea() + "\n" +
                "Perimeter = " + calcPerimeter() + "\n";
    }
}
