package application;

import data.FileSource;
import data.ShapeSource;
import data.shapes.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();

        shapes.add(new Circle(4));
        shapes.add(new Square(6));
        shapes.add(new Rectangle(3, 4));
        shapes.add(new Triangle(4, 5, 6));

        FileSource<Shape> converter = new ShapeSource("shapes.json");
        try {
            converter.toFile(shapes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            List<Shape> shapesFromFile = converter.fromFile();
            shapesFromFile.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
