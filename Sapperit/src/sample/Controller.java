package sample;

public class Controller {
    Model mod;

    Controller(Model mod){
        this.mod = mod;
    }

    void openCell(int row, int column) throws NullPointerException{
        mod.openCell(row, column);
    }

    void newGame(){
        mod.genBoard(Model.FEILD_SIZE, Model.mines);
    }
}
