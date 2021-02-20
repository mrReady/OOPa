package sample;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        Model model = new Model();
        View view = new View(model);

        model.addListener(view);
    }
}