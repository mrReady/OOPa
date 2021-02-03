package sample;


import java.util.ArrayList;

public class Model {

    static final int FEILD_SIZE = 10;
    static final int mines = 7;

    Cell[][] cel;
    ArrayList<MinerView> listener = new ArrayList<>();

    void  addListener(MinerView vi){
        listener.add(vi);
    }

    void openCell(int row, int column) throws NullPointerException{
        cel[column][row].state = s.opened;

        if(cel[column][row].count == 0){
            for(int j = -1; j < 2; j++){
                for(int i = -1;i < 2; i++){
                    if(i != 0 || j != 0){
                        if(!outOfBounds(column + i, row + j)){
                            if (!cel[column + i][row + j].state.equals(s.opened)){
                                openCell(row + j, column + i);
                            }}}}}}

        for (MinerView vi : listener){
            vi.update(cel[column][row]);
        }

        if (cel[column][row].mined){
            for(MinerView vi : listener){
                vi.gamend();
                return;
            }
        }

        if(isWon()){
            for(MinerView vi : listener){
                vi.win();
                return;
            }
        }
    }

    private boolean isWon() {
        for(int j = 0; j < cel.length; j++){
            for(int i = 0; i < cel[0].length; i++){
                if((cel[i][j].state.equals(s.closed)) && (!cel[i][j].mined)){
                    return false;
                }
            }
        }
        return true;
    }

    Cell getCell(int row, int column){
        return cel[column][row];
    }

    public void genBoard(int FEILD_SIZE, int mines){
        cel = new Cell[FEILD_SIZE][FEILD_SIZE];
        for(int j = 0; j < FEILD_SIZE; j++){
            for(int i = 0; i < FEILD_SIZE; i++){
                cel[i][j] = new Cell(j, i);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for(int row = 0; row < FEILD_SIZE; row++){
            for (int column = 0; column < FEILD_SIZE; column++){
                list.add(row*100 + column);
            }
        }

        for(int a = 0; a < mines; a++){
            int chek = (int)(Math.random()*list.size());
            cel[list.get(chek)/100][list.get(chek)%100].mined = true;
            incrementCountAroundCell(list.get(chek)%100,list.get(chek)/100);
            list.remove(chek);
        }

        for (MinerView vi : listener){
            vi.start();
        }
    }

    private void incrementCountAroundCell(int i, int i1) {
        for(int j = -1; j < 2; j++){
            for (int k = -1; k < 2; k++){
                if(!outOfBounds(i1 + k, i + j)){
                    if(k == 0 && j == 0){
                        cel[i1 + k][i + j].count = 10;
                        cel[i1 + k][i + j].mined = true;
                    }
                    else{
                        cel[i1 + k][i + j].count++;
                    }

                   for(MinerView vi : listener){
                        vi.update(cel[i1 + k][i + j]);
                   }
                }
            }
        }
    }

    private boolean outOfBounds(int i, int i1) {
        try {
            Cell cell = cel[i][i1];
            return false;
        }
        catch (IndexOutOfBoundsException e){
            return true;
        }
    }


}
