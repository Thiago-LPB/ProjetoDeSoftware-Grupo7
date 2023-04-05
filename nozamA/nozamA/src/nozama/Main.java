package nozama;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{

    public Main(){
        this.setSize(300, 200);
        this.setVisible(false);
        this.setResizable(false);
        this.setTitle("nozamA!");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(51,60,76));

        Login login = new Login();
        // Iniciando o "banco de dados".
        Bd bancoDados = Bd.getInstance();

        bancoDados.mainWindow = this;

         //Admin default do sistema.
         bancoDados.getUserAdmin().add(new UserAdmin("admin", "123.456.789-10", "admin", "admin"));

        /*** Atributos gráficos ***/

        JButton log      = new JButton("   Login   ");
        JButton cadastro = new JButton("Cadastro");
        JButton admin    = new JButton("   Admin   ");
        JButton exit     = new JButton("     Exit     ");

        log.setCursor(new Cursor(12));
        cadastro.setCursor(new Cursor(12));
        admin.setCursor(new Cursor(12));
        exit.setCursor(new Cursor(12));

        log.setBackground(new Color(225,127,17));
        cadastro.setBackground(new Color(225,127,17));
        admin.setBackground(new Color(225,127,17));
        exit.setBackground(new Color(200,80,100));

        JPanel logPanel = new JPanel();
        JPanel cadastroPanel = new JPanel();
        JPanel adminPanel = new JPanel();
        JPanel exitPanel = new JPanel();

        logPanel.setBackground(new Color(51,60,76));
        cadastroPanel.setBackground(new Color(51,60,76));
        adminPanel.setBackground(new Color(51,60,76));
        exitPanel.setBackground(new Color(51,60,76));

        logPanel.add(log);
        cadastroPanel.add(cadastro);
        adminPanel.add(admin);
        exitPanel.add(exit);

        GridLayout layout = new GridLayout(4, 1);

        this.setLayout(layout);
        this.add(logPanel);
        this.add(cadastroPanel);
        this.add(adminPanel);
        this.add(exitPanel);
        this.repaint();
        this.setVisible(true);


        log.addActionListener(e -> {
            this.setVisible(false);
            login.checkLogin();
            this.setVisible(true);
        }); 

        cadastro.addActionListener(e -> {
            this.setVisible(false);
            String message = login.setRegister(true);
            JOptionPane.showMessageDialog(this, message);
            this.setVisible(true);
        });

        admin.addActionListener(e -> {
            this.setVisible(false);
            login.checkLoginAdmin();
            this.setVisible(true);
        });

        exit.addActionListener(e -> {
            System.exit(0);
        });
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }
}