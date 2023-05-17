package nozama.app;

import java.util.Iterator;
import nozama.users.*;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame{

    Bd bancoDados = Bd.getInstance();

    public Login(){
        this.setVisible(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        bancoDados.loginWindow = this;
    }

    public void checkLogin(){
        JDialog janela = new JDialog();
        JPanel painel  = new JPanel(new GridLayout(2,2));
        JPanel button  = new JPanel();

        janela.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        janela.setTitle("Check Login");
        janela.setLayout(new BorderLayout());
        janela.setSize(350, 150);
        janela.setVisible(true);
        janela.setModal(true);
        janela.getContentPane().setBackground(new Color(51,60,76));

        JButton confir          = new JButton("Confirmar");
        JLabel lblemail         = new JLabel("E-mail");
        JLabel lblpassword      = new JLabel("Senha");
        JTextField email        = new JTextField();
        JPasswordField password = new JPasswordField();

        lblemail.setBackground(new Color(51,60,76));
        lblpassword.setBackground(new Color(51,60,76));

        lblemail.setOpaque(true);
        lblpassword.setOpaque(true);
        lblemail.setForeground(new Color(225,127,17));
        lblpassword.setForeground(new Color(225,127,17));

        email.setBackground(Color.lightGray);
        password.setBackground(Color.lightGray);

        confir.setCursor(new Cursor(12));
        confir.setBackground(new Color(225,127,17));

        button.add(confir);
        button.setBackground(new Color(51,60,76));

        painel.add(lblemail);
        painel.add(email);
        painel.add(lblpassword);
        painel.add(password);
        janela.add(painel, BorderLayout.CENTER);
        janela.add(button, BorderLayout.SOUTH);

        janela.repaint();
        janela.setVisible(true);

        confir.addActionListener(event -> {
            String strEmail = new String(), strPassword = new String();
            janela.setVisible(false);

            try {
                strEmail = email.getText();
                strPassword = new String(password.getPassword());
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Os Campos de senha e e-mail não podem estar vazios", "Aviso", 0);
            }

            try {
                int index = valid(strEmail, strPassword);
                if (index == -1) {
                    JOptionPane.showMessageDialog(this, "E-mail ou senha incorretos. Tente novamente");
                } else {
                    JOptionPane.showMessageDialog(this, "Login efetuado com sucesso!");
                    bancoDados.getUsers().get(index).menu();
                }
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(this, "E-mail ou senha não fornecidos. Tente novamente.", "Aviso", -1);
            }

            janela.dispose();
        });
    }
    
    public int valid(String email, String password) {
            Iterator<User> iterator = bancoDados.getUsers().iterator();
            int i = 0;
            while (iterator.hasNext()) {
                User usuario = iterator.next();
                if (usuario.getEmail().equals(email) && usuario.getPassword().equals(password)) {
                    return i;
                }
                i++;
            }
        return -1;
    }

    public String setRegister(Boolean check){
        User novoPerfil = (check) ? new Profile() : new UserAdmin();
        novoPerfil.setVisible(true);
        novoPerfil.repaint();

        Iterator<User> users = bancoDados.getUsers().iterator();

        while(users.hasNext()){
            User user = users.next();

            if(user.getEmail().equals(novoPerfil.getEmail())){
                return "E-mail já cadastrado!. Tente novamente mais tarde.";
            }
        }
        bancoDados.getUsers().add(novoPerfil);
        if(bancoDados.creatUser) return "Usuário Registrado!\n"+novoPerfil.toString();
        else return "Operação cancelada\nOK para continiar..";
    } 
}    
