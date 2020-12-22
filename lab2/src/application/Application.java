package application;

import shapes.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.System.out;

public class Application {

    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();

        shapes.add(new Circle(6));
        shapes.add(new Circle(3));
        shapes.add(new Square(6));
        shapes.add(new Square(9));
        shapes.add(new Rectangle(3, 4));
        shapes.add(new Rectangle(6, 3));
        shapes.add(new Triangle(4, 5, 6));
        shapes.add(new Triangle(2, 1, 2));

        double S = shapes.stream().mapToDouble(Shape::calcArea).sum();
        out.println("Total square: " + S);

        out.println("Shapes sorted by area: ");
        shapes.sort(Comparator.comparingDouble(Shape::calcArea).reversed());
        shapes.forEach(shape -> out.println(shape.toString()));

        out.println("Shapes sorted by perimeter: ");
        shapes.sort(Comparator.comparingDouble(Shape::calcPerimeter).reversed());
        shapes.forEach(shape -> out.println(shape.toString()));
    }
}
