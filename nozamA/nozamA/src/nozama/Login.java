package nozama;

import java.util.Iterator;

import javax.swing.*;
import java.awt.*;

/*      Classe dedicada a administração de login(usuário e admin) */
class Login extends JFrame{

    Bd bancoDados = Bd.getInstance();


    public Login(){
        this.setVisible(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        
        bancoDados.loginWindow = this;
    }

    //Checklogin de usuário comum retornanod sua posição no array.
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
            String strEmail, strPassword;
            janela.setVisible(false);

            strEmail = email.getText();
            strPassword = new String(password.getPassword());
            int index = valid(strEmail, strPassword, true);
    
            if(index == -1){
                JOptionPane.showMessageDialog(this, "E-mail ou senha incorretos. Tente novamente");
            }else{
                JOptionPane.showMessageDialog(this, "Login efetuado com sucesso!");
                bancoDados.getUsers().get(index).menuProfile();
            }
                janela.dispose();
        });
    }
    //Checklogin de usuário admin retorna sua posição no array.
    public void checkLoginAdmin(){
        JDialog janela = new JDialog();
        JPanel painel  = new JPanel(new GridLayout(2,2));
        JPanel button  = new JPanel();

        janela.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        janela.setTitle("Check Admin Login");
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

        confir.addActionListener(e -> {
            String strEmail, strPassword;
            janela.setVisible(false);

            strEmail = email.getText();
            strPassword = new String(password.getPassword());
            int index = valid(strEmail, strPassword, false);
    
            if(index == -1){
                JOptionPane.showMessageDialog(this, "E-mail ou senha incorretos. Tente novamente");
            }else{
                JOptionPane.showMessageDialog(this, "Login efetuado com sucesso!");
                bancoDados.getUserAdmin().get(index).menuUserAdmin();
            }

            janela.dispose();
        });

    } 
    
    //Inicia um novo registro e adciona ao sistema
    public String setRegister(Boolean check){
        if(check){
            Profile novoPerfil = new Profile();
            novoPerfil.setVisible(true);
            novoPerfil.repaint();

            Iterator<Profile> itr = bancoDados.getUsers().iterator();

            while(itr.hasNext()){
                Profile e = itr.next();

                if(e.getEmail().equals(novoPerfil.getEmail())){
                    return "E-mail já cadastrado!. Tente novamente mais tarde.";
                }
            }
            bancoDados.getUsers().add(novoPerfil);
            if(bancoDados.creatUser) return "Usuário Registrado!\n"+novoPerfil.toString();
            else return "Operação cancelada\nOK para continiar..";
        }else {
            UserAdmin novoPerfil = new UserAdmin();
            novoPerfil.setVisible(true);
            novoPerfil.repaint();

            Iterator<UserAdmin> itr = bancoDados.getUserAdmin().iterator();

            while(itr.hasNext()){
                UserAdmin e = itr.next();

                if(e.getEmail().equals(novoPerfil.getEmail())){
                    return "E-mail já cadastrado!. Tente novamente mais tarde.";
                }
            }
            bancoDados.getUserAdmin().add(novoPerfil);
            if(bancoDados.creatUser) return "Usuário Registrado!\n"+novoPerfil.toString();
            else return "Operação cancelada\nOK para continiar..";
        }
    }
    
    private int valid(String testEmail, String testPassword, boolean check){
        int retorno = -1;
        System.out.println(testEmail+" "+testPassword);
        if(check){
            Iterator<Profile> itrUsers = bancoDados.getUsers().iterator();

            while(itrUsers.hasNext()){
                Profile current = itrUsers.next();
                retorno++;

                if(current.getEmail().equals(testEmail) && current.getPassword().equals(testPassword)){
                    System.out.println(retorno);
                    break;
                }
                if(!itrUsers.hasNext()) retorno = -1;
            }
            return retorno;
        }else{
            Iterator<UserAdmin> itrUserAdmin = bancoDados.getUserAdmin().iterator();

            while(itrUserAdmin.hasNext()){
                UserAdmin current = itrUserAdmin.next();
                retorno++;

                if(current.getEmail().equals(testEmail) && current.getPassword().equals(testPassword)){
                    System.out.println(retorno);
                    break;
                }
                if(!itrUserAdmin.hasNext()) retorno = -1;
            }
            return retorno;
        }
    }
}