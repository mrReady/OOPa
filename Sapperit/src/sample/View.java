package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements MinerView, ActionListener {
    Cell[][] cell = new Cell[Model.FEILD_SIZE][Model.FEILD_SIZE];
    JButton[][] buttons = new JButton[Model.FEILD_SIZE][Model.FEILD_SIZE];
    Controller controller;
    GridLayout grid = new GridLayout(Model.FEILD_SIZE, Model.FEILD_SIZE);

    View (Model model){
        controller = new Controller(model);

        setTitle("Сапёр");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500,500);

        setResizable(true);

        JPanel panel = new JPanel();
        panel.setBackground(Color.black);
        add(BorderLayout.CENTER, panel);
        panel.setLayout(grid);

        for (int j = 0; j < Model.FEILD_SIZE; j++){
            for (int i = 0; i < Model.FEILD_SIZE; i++){
                buttons[i][j] = new JButton();
                cell[i][j] = new Cell(j, i);
                buttons[i][j].addActionListener(this);
                panel.add(buttons[i][j]);
            }
        }

        JMenuBar jmb = new JMenuBar();
        JMenu jm = new JMenu("Игра");
        JMenuItem jmi = new JMenuItem("Новая игра");
        jmi.addActionListener(this);
        jm.add(jmi);
        jmb.add(jm);
        this.setJMenuBar(jmb);

        setVisible(true);
    }

    public void update(Cell cells){
        cell[cells.column][cells.row] = cells;
        if (cells.state.equals(s.opened)){
            if (cells.mined) {
                buttons[cells.column][cells.row].setText("Б");
            } else {
                buttons[cells.column][cells.row].setText(cells.count + "");
            }
            if (cell[cells.column][cells.row].state.equals(s.opened)) {
                buttons[cells.column][cells.row].setEnabled(false);
            }
        }
    }


    @Override public void gameEnd(){
        this.setTitle("Конец игры");

        for (int j = 0; j < buttons.length; j++){
            for (int i = 0; i < buttons.length; i++) {
                if (cell[i][j].mined) {
                    buttons[i][j].setBackground(Color.RED);
                    buttons[i][j].setText("Б");
                }
                buttons[i][j].setEnabled(false);
            }
        }
        JOptionPane.showMessageDialog(this, "Конец игры");
    }

    @Override public void start(){
        this.setTitle("Игра в процессе");
        for (int j = 0; j < buttons.length; j++){
            for (int i = 0; i < buttons[0].length; i++){
                buttons[i][j].setEnabled(true);
                buttons[i][j].setText("");
                buttons[i][j].setBackground((new JButton()).getBackground());
                buttons[i][j].setDisabledIcon((new JButton()).getDisabledIcon());
            }
        }
    }

    @Override public void won(){
        this.setTitle("Сапёр");
        for (int j = 0; j < buttons.length; j++){
            for (int i = 0; i < buttons[0].length; i++){
                buttons[i][j].setEnabled(false);
                if (cell[i][j].mined){
                    buttons[i][j].setText("Б");
                    buttons[i][j].setBackground(Color.blue);
                }
            }
        }
        JOptionPane.showMessageDialog(this, "Победа!!!");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(this.getJMenuBar().getMenu(0).getItem(0))){
            controller.newGame();
        }

        for (int j = 0; j < buttons[0].length; j++){
            for (int i = 0; i < buttons.length; i++){
                if (e.getSource().equals(buttons[i][j])){
                    try {
                        controller.openCell(j, i);
                    }
                    catch (NullPointerException w){
                        JOptionPane.showMessageDialog(this,"Игра не начата");
                    }
                    if (cell[i][j].mined){
                        buttons[i][j].setForeground(Color.RED);
                        buttons[i][j].setText("Б");
                    } else {
                        buttons[i][j].setText(cell[i][j].count + "");
                    }
                    buttons[i][j].setEnabled(false);
                }
            }
        }
    }
}
