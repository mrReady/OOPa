package shapes.entities;

public class Rectangle implements Shape {

    public final double a;
    public final double b;

    public Rectangle(double a, double b) {
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
