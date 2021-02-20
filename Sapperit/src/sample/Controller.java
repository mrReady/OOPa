package sample;

public class Controller {
    Model model;

    Controller(Model model){
        this.model = model;
    }

    void openCell(int row, int column) throws NullPointerException{
        model.openCell(row, column);
    }

    void newGame(int f, int m){
        model.genBoard(f, m);
    }

    void flagCell(int row, int column) throws NullPointerException{
        model.flagCell(row, column);
    }
}
