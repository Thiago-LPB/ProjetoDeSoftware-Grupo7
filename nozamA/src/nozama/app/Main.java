package nozama.app;

import nozama.*;
import nozama.users.UserAdmin;
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{

    public Main() throws HeadlessException {
        try {
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
            bancoDados.getUsers().add(new UserAdmin("admin", "123.456.789-10", "admin", "admin"));

            /*** Atributos gráficos ***/

            JButton log      = new JButton("   Login   ");
            JButton cadastro = new JButton("Cadastro");
            JButton exit     = new JButton("     Exit     ");

            JPanel logPanel      = new JPanel();
            JPanel cadastroPanel = new JPanel();
            JPanel exitPanel     = new JPanel();

            ConfigMenu(log, logPanel);
            ConfigMenu(cadastro, cadastroPanel);
            ConfigMenu(exit, exitPanel);
            exit.setBackground(new Color(200,80,100));

            GridLayout layout = new GridLayout(3, 1);

            this.setLayout(layout);
            this.add(logPanel);
            this.add(cadastroPanel);

            this.add(exitPanel);
            this.repaint();
            this.setVisible(true);

            log.addActionListener(e -> {
                this.setVisible(false);
                try {
                    login.checkLogin();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error em LOGIN", "Aviso", JOptionPane.ERROR_MESSAGE);
                }
                this.setVisible(true);
            });

            cadastro.addActionListener(e -> {
                this.setVisible(false);
                String message = login.setRegister(true);
                JOptionPane.showMessageDialog(this, message);
                this.setVisible(true);
            });


            exit.addActionListener(e -> {
                try {
                    System.exit(0);
                } catch (SecurityException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error: Could not exit program.", "Aviso", JOptionPane.ERROR_MESSAGE);
                }
            });
        } catch (HeadlessException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Headless environment detected.", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ConfigMenu(JButton button, JPanel panel){
        button.setCursor(new Cursor(12));
        button.setBackground(new Color(225,127,17));
        panel.setBackground(new Color(51,60,76));

        panel.add(button);
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }
}
