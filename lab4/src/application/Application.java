package application;

import shapes.ShapeAccumulator;
import shapes.entities.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Application {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();

        shapes.add(new Triangle(5, 5, 5));
        shapes.add(new Circle(5));
        shapes.add(new Rectangle(5, 2));

        Queue<Square> squares = new ArrayDeque<>();
        squares.add(new Square(2));
        squares.add(new Square(6));

        ShapeAccumulator accumulator = new ShapeAccumulator();

        accumulator.addAll(shapes);
        accumulator.addAll(squares);


        System.out.println("Shape with max area:\n" + accumulator.getMaxAreaShape());
        System.out.println("Shape with min area:\n" + accumulator.getMinAreaShape());
        System.out.println("Shape with max perimeter:\n" + accumulator.getMaxPerimeterShape());
        System.out.println("Shape with min perimeter:\n" + accumulator.getMinPerimeterShape());

        System.out.println("Total area: " + accumulator.getTotalArea());
        System.out.println("Total perimeter: " + accumulator.getTotalPerimeter());
    }
}
