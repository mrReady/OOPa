package sample;

public class Main {

    public static void main(String[] args) {
       Model mod = new Model();
       View vi = new View(mod);

       mod.addListener(vi);
    }
}