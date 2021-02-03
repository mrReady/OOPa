package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements MinerView, ActionListener {
    Cell[][] cel = new Cell[Model.FEILD_SIZE][Model.FEILD_SIZE];
    JButton[][] buttons = new JButton[Model.FEILD_SIZE][Model.FEILD_SIZE];
    Controller controller;
    GridLayout grid = new GridLayout(Model.FEILD_SIZE, Model.FEILD_SIZE);

    View (Model mod){
        controller = new Controller(mod);

        setTitle("Сапёр");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500,500);

        setResizable(true);

        Canvas canvas = new Canvas();
        canvas.setBackground(Color.black);
        add(BorderLayout.CENTER, canvas);
        canvas.setLayout(grid);

        for(int j = 0; j < Model.FEILD_SIZE; j++){
            for(int i = 0; i < Model.FEILD_SIZE; i++){
                buttons[i][j] = new JButton();
                cel[i][j] = new Cell(j, i);
                buttons[i][j].addActionListener(this);
                canvas.add(buttons[i][j]);
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

    public void update(Cell cell){
        cel[cell.column][cell.row] = cell;
        if (cell.state.equals("opened")){
            if(cell.mined) {
                buttons[cell.column][cell.row].setText("Б");
            }
            else {
                buttons[cell.column][cell.row].setText(cell.count + "");
            }
            if(cel[cell.column][cell.row].state.equals("opened")) {
                buttons[cell.column][cell.row].setEnabled(false);
            }
        }
    }


    @Override
    public void gamend(){
        this.setTitle("Конец игры");

        for(int j = 0; j < buttons.length; j++){
            for(int i = 0; i < buttons.length; i++) {
                if (cel[i][j].mined) {
                    buttons[i][j].setBackground(Color.RED);
                    buttons[i][j].setText("Б");
                }
                buttons[i][j].setEnabled(false);
            }
        }
        JOptionPane.showMessageDialog(this, "Конец игры");
    }

    @Override
    public void start(){
        this.setTitle("Игра в процессе");
        for(int j = 0; j < buttons.length; j++){
            for (int i = 0; i < buttons[0].length; i++){
                buttons[i][j].setEnabled(true);
                buttons[i][j].setText("");
                buttons[i][j].setBackground((new JButton()).getBackground());
                buttons[i][j].setDisabledIcon((new JButton()).getDisabledIcon());
            }
        }
    }

    @Override
    public void win(){
        this.setTitle("Сапёр");
        for(int j = 0; j < buttons.length; j++){
            for(int i = 0; i < buttons[0].length; i++){
                buttons[i][j].setEnabled(false);
                if(cel[i][j].mined){
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

        for(int j = 0; j < buttons[0].length; j++){
            for (int i = 0; i < buttons.length; i++){
                if(e.getSource().equals(buttons[i][j])){
                    try{
                        controller.openCell(j, i);
                    }
                    catch (NullPointerException w){
                        JOptionPane.showMessageDialog(this,"Игра не начата");
                    }
                    if (cel[i][j].mined){
                        buttons[i][j].setForeground(Color.RED);
                        buttons[i][j].setText("Б");
                    }
                    else {
                        buttons[i][j].setText(cel[i][j].count + "");
                    }
                    buttons[i][j].setEnabled(false);
                }
            }
        }
    }
}
