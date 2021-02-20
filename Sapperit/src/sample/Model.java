package sample;

import javax.swing.*;
import java.util.ArrayList;

import static java.lang.Math.pow;

public class Model extends JFrame {

    static final int FEILD_SIZE = 21; //max 21
    public static Integer fei, min;

    Cell[][] cell;
    ArrayList<MinerView> listener = new ArrayList<>();

    void addListener(MinerView view) {
        listener.add(view);
    }

    void openCell(int row, int column) throws NullPointerException {
        cell[column][row].state = s.opened;

        if (cell[column][row].count == 0) {
            for (int j = -1; j < 2; j++) {
                for (int i = -1; i < 2; i++) {
                    if (i != 0 || j != 0) {
                        if (!outOfBounds(column + i, row + j)) {
                            if (!cell[column + i][row + j].state.equals(s.opened)) {
                                openCell(row + j, column + i);
                            }
                        }
                    }
                }
            }
        }

        for (MinerView view : listener) {
            view.update(cell[column][row]);
        }

        if (cell[column][row].mined) {
            for (MinerView view : listener) {
                view.gameEnd();
                return;
            }
        }

        if (isWon()) {
            for (MinerView view : listener) {
                view.won();
                return;
            }
        }
    }

    private boolean isWon() {
        for (int j = 0; j < cell.length; j++) {
            for (int i = 0; i < cell[0].length; i++) {
                if ((cell[i][j].state.equals(s.closed)) && (!cell[i][j].mined)) {
                    return false;
                }
            }
        }
        return true;
    }

    Cell getCell(int row, int column) {
        return cell[column][row];
    }

    public void genBoard(int f, int m) {
        cell = new Cell[f][f];
        for (int j = 0; j < f; j++) {
            for (int i = 0; i < f; i++) {
                cell[i][j] = new Cell(j, i);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int row = 0; row < f; row++) {
            for (int column = 0; column < f; column++) {
                list.add(row * 1000 + column);
            }
        }

        for (int a = 0; a < m; a++) {
            int chek = (int) (Math.random() * list.size());
            cell[list.get(chek) / 1000][list.get(chek) % 1000].mined = true;
            incrementCountAroundCell(list.get(chek) % 1000, list.get(chek) / 1000);
            list.remove(chek);
        }

        for (MinerView view : listener) {
            view.start();
        }
    }

    private void incrementCountAroundCell(int column, int row) {
        for (int j = -1; j < 2; j++) {
            for (int i = -1; i < 2; i++) {
                if (!outOfBounds(row + i, column + j)) {
                    if (i == 0 && j == 0) {
                        cell[row + i][column + j].count = 10;
                        cell[row + i][column + j].mined = true;
                    } else {
                        cell[row + i][column + j].count++;
                    }
                    for (MinerView view : listener) {
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
        } catch (IndexOutOfBoundsException e) {
            return true;
        }
    }

    public void flagCell(int row, int column) throws NullPointerException {
        if (cell[column][row].state.equals(s.closed)) {
            cell[column][row].state = s.flag;
        } else {
            if (cell[column][row].state.equals(s.flag)) {
                cell[column][row].state = s.closed;
            }
        }
    }

    Model() {
        fei = 0; min = 0;
        while (fei < 1 || fei > FEILD_SIZE || min < 1 || min > pow(fei,2)) {
            String f = JOptionPane.showInputDialog("Какого размера будет квадрат поля?");
            String m = JOptionPane.showInputDialog("Сколько мин поставить?");
            fei = Integer.valueOf(f);
            min = Integer.valueOf(m);
            if (fei < 1 || fei > FEILD_SIZE || min < 1 || min > pow(fei,2)) {
                JOptionPane.showMessageDialog(this, "Не подходящее значение! Повторите.");
            }
        }
    }
}
