package data.shapes;

public class Square implements Shape {

    public final double a;

    public Square(double a) {
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
