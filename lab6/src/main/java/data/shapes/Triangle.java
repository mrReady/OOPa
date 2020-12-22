package data.shapes;

public class Triangle implements Shape {

    public final double a;
    public final double b;
    public final double c;

    private static final String INCORRECT_SIDES_MESSAGE = "Triangle with these sides doesn't exist";

    public Triangle(double a, double b, double c) {
        if (a + b <= c || a + c <= b || b + c <= a)
            throw new IllegalArgumentException(INCORRECT_SIDES_MESSAGE);

        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double calcArea() {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public double calcPerimeter() {
        return a + b + c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    @Override
    public String toString() {
        return "Triangle (" + a + ", " + b + ", " + c + ")";
    }
}
