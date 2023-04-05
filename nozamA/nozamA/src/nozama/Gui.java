package nozama;

import javax.swing.*;

public class Gui extends JFrame{
    public Gui(String strTitle){
        this.setSize(300, 200);
        this.setVisible(true);
        this.setResizable(true);
        this.setTitle(strTitle);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}