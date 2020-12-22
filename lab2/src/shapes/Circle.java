package shapes;

public class Circle implements Shape {

    public final double radius;

    public static final String INCORRECT_RADIUS_MESSAGE = "Circle with this radius doesn't exist";

    public Circle(double radius) {
        if (radius <= 0)
            throw new IllegalArgumentException(INCORRECT_RADIUS_MESSAGE);

        this.radius = radius;
    }

    @Override
    public double calcArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double calcPerimeter() {
        return 2 * Math.PI * radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Circle (Radius = " + radius + "):\n" +
                "Area = " + calcArea() + "\n" +
                "Perimeter = " + calcPerimeter() + "\n";
    }
}
