package data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import data.shapes.Shape;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ShapeSource implements FileSource<Shape> {
    private final String path;

    public ShapeSource(String path) {
        this.path = path;
    }

    public void toFile(List<Shape> shapes) throws IOException {
        try (Writer writer = new FileWriter(path, false)) {
            GsonBuilder builder = new GsonBuilder();
            Type type = new TypeToken<List<Shape>>() {
            }.getType();

            builder.registerTypeAdapter(Shape.class, new InterfaceAdapter());
            Gson gson = builder.create();
            gson.toJson(shapes, type, writer);
        } catch (IOException e) {
            throw new IOException("An error has occurred while working with the file. Check file path.");
        }
    }

    public List<Shape> fromFile() throws IOException {
        try (FileReader reader = new FileReader(path)) {
            GsonBuilder builder = new GsonBuilder();
            Type type = new TypeToken<List<Shape>>() {
            }.getType();

            builder.registerTypeAdapter(Shape.class, new InterfaceAdapter());
            Gson gson = builder.create();

            List<Shape> shapes = gson.fromJson(reader, type);
            return shapes != null ? shapes : Collections.emptyList();
        } catch (IOException e) {
            throw new IOException("An error has occurred while working with the file. Check file path.");
        }
    }
}
