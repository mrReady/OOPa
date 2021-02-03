package sample;

public class Cell {
    int row, column;
    String state;
    boolean mined;
    int count;

    Cell(int row, int column){
        mined = false;
        count = 0;
        state = "closed";
        this.row = row;
        this.column = column;
    }
}
