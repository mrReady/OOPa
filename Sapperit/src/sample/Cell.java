package sample;

enum s { closed, opened }

public class Cell {
    int     row, column;
    s       state;
    boolean mined;
    int     count;

    Cell (int row, int column){
        mined = false;
        count = 0;
        state = s.closed;
        this.row = row;
        this.column = column;
    }
}
