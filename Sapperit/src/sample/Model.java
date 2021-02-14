package sample;

import java.util.ArrayList;

public class Model {

    static final int FEILD_SIZE = 16; //max 23
    static final int MINES = 40; // max FEILD_SIZE^2 / 8

    Cell[][] cell;
    ArrayList<MinerView> listener = new ArrayList<>();

    void addListener(MinerView view){
        listener.add(view);
    }

    void openCell(int row, int column) throws NullPointerException{
        cell[column][row].state = s.opened;

        if (cell[column][row].count == 0){
            for (int j = -1; j < 2; j++){
                for (int i = -1; i < 2; i++){
                    if (i != 0 || j != 0){
                        if (!outOfBounds(column + i, row + j)){
                            if (!cell[column + i][row + j].state.equals(s.opened)){
                                openCell(row + j, column + i);
                            }
                        }
                    }
                }
            }
        }

        for (MinerView view : listener){
            view.update(cell[column][row]);
        }

        if (cell[column][row].mined){
            for (MinerView view : listener){
                view.gameEnd();
                return;
            }
        }

        if (isWon()){
            for (MinerView view : listener){
                view.won();
                return;
            }
        }
    }

    private boolean isWon() {
        for (int j = 0; j < cell.length; j++){
            for (int i = 0; i < cell[0].length; i++){
                if ((cell[i][j].state.equals(s.closed)) && (!cell[i][j].mined)){
                    return false;
                }
            }
        }
        return true;
    }

    Cell getCell(int row, int column){
        return cell[column][row];
    }

    public void genBoard(int FEILD_SIZE, int MINES){
        cell = new Cell[FEILD_SIZE][FEILD_SIZE];
        for (int j = 0; j < FEILD_SIZE; j++){
            for (int i = 0; i < FEILD_SIZE; i++){
                cell[i][j] = new Cell(j, i);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int row = 0; row < FEILD_SIZE; row++){
            for (int column = 0; column < FEILD_SIZE; column++){
                list.add(row*100 + column);
            }
        }

        for (int a = 0; a < MINES; a++){
            int chek = (int)(Math.random()*list.size());
            cell[list.get(chek)/100][list.get(chek)%100].mined = true;
            incrementCountAroundCell(list.get(chek)%100,list.get(chek)/100);
            list.remove(chek);
        }

        for (MinerView view : listener){
            view.start();
        }
    }

    private void incrementCountAroundCell(int column, int row) {
        for (int j = -1; j < 2; j++){
            for (int i = -1; i < 2; i++){
                if (!outOfBounds(row + i, column + j)){
                    if (i == 0 && j == 0){
                        cell[row + i][column + j].count = 10;
                        cell[row + i][column + j].mined = true;
                    } else {
                        cell[row + i][column + j].count++;
                    }
                   for(MinerView view : listener){
                        view.update(cell[row + i][column + j]);
                   }
                }
            }
        }
    }

    private boolean outOfBounds(int column, int row) {
        try {
            Cell cells = cell[column][row];
            return false;
        }
        catch (IndexOutOfBoundsException e){
            return true;
        }
    }

    public void flagCell(int row, int column)throws NullPointerException{
        if (cell[column][row].state.equals(s.closed)) {
            cell[column][row].state = s.flag;
        } else {
            if (cell[column][row].state.equals(s.flag)) {
                cell[column][row].state = s.closed;
            }
        }
        for (MinerView view : listener){
            view.update(cell[column][row]);
        }
    }
}
