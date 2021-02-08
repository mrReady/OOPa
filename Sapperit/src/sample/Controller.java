package sample;

public class Controller {
    Model model;

    Controller(Model mod){
        this.model = mod;
    }

    void openCell(int row, int column) throws NullPointerException{
        model.openCell(row, column);
    }

    void newGame(){
        model.genBoard(Model.FEILD_SIZE, Model.MINES);
    }
}
