package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class View extends JFrame implements MinerView, MouseListener, ActionListener {
    Cell[][] cell = new Cell[Model.FEILD_SIZE][Model.FEILD_SIZE];
    JButton[][] buttons = new JButton[Model.FEILD_SIZE][Model.FEILD_SIZE];
    Controller controller;
    GridLayout grid = new GridLayout(Model.FEILD_SIZE, Model.FEILD_SIZE);

    View (Model model){
        controller = new Controller(model);

        setTitle("Сапёр");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000,1000);  // Оптимально 1000 на 1000
        setResizable(true);

        JPanel panel = new JPanel();
        panel.setBackground(Color.black);
        add(BorderLayout.CENTER, panel);
        panel.setLayout(grid);
/*
        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    for (int j = 0; j < buttons[0].length; j++) {
                        for (int i = 0; i < buttons.length; i++) {
                            if (e.getSource().equals(buttons[i][j])) {
                                try {
                                    controller.flagCell(j, i);
                                    if (cell[i][j].state.equals(s.flag)) {
                                        buttons[i][j].setText("Ф");
                                    } else {
                                        buttons[i][j].setText("");
                                    }
                                } catch (NullPointerException w) {
                                    //JOptionPane.showMessageDialog(this,"Игра не начата");
                                }
                            }
                        }
                    }
                }
            }
        });
*/
        for (int j = 0; j < Model.FEILD_SIZE; j++){
            for (int i = 0; i < Model.FEILD_SIZE; i++){
                buttons[i][j] = new JButton();
                cell[i][j] = new Cell(j, i);
                buttons[i][j].addMouseListener(this);
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
        if (cells.state.equals(s.flag)){
            buttons[cells.column][cells.row].setEnabled(false);
        }
        if (cells.state.equals(s.closed)){
            buttons[cells.column][cells.row].setEnabled(true);
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

    /*@Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(this.getJMenuBar().getMenu(0).getItem(0))){
            controller.newGame();
        }

        for (int j = 0; j < buttons[0].length; j++){
            for (int i = 0; i < buttons.length; i++){
                if (e.getSource().equals(buttons[i][j])){
                    try {
                        if (!cell[i][j].state.equals(s.flag)){
                            controller.openCell(j, i);
                        }
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
    }*/

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            for (int j = 0; j < buttons[0].length; j++) {
                for (int i = 0; i < buttons.length; i++) {
                    if (e.getSource().equals(buttons[i][j]) && cell[i][j].state.equals(s.closed)) {
                        try {
                            controller.flagCell(j, i);
                            if (cell[i][j].state.equals(s.closed)) {
                                buttons[i][j].setForeground(Color.green);
                                buttons[i][j].setText("М");
                            } else {
                                buttons[i][j].setText("");
                            }
                        } catch (NullPointerException w) {
                            JOptionPane.showMessageDialog(this, "Игра не начата");
                        }
                    }
                }
            }
        }

        if (e.getButton() == MouseEvent.BUTTON1) {
            for (int j = 0; j < buttons[0].length; j++) {
                for (int i = 0; i < buttons.length; i++) {
                    if (!cell[i][j].state.equals(s.flag)) {
                        if (e.getSource().equals(buttons[i][j])) {
                            try {
                                controller.openCell(j, i);
                            } catch (NullPointerException w) {
                                JOptionPane.showMessageDialog(this, "Игра не начата");
                            }
                        }
                        if (cell[i][j].mined) {
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

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.getJMenuBar().getMenu(0).getItem(0))) {
            controller.newGame();
        }
    }
}