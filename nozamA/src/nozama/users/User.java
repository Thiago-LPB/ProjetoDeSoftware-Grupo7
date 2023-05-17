package nozama.users;

import nozama.products.Product;
import nozama.app.Message;
import nozama.app.Bd;

import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.regex.PatternSyntaxException;


public abstract class User extends JDialog{
    //Arrays dos produtos que eu compro e vendo.
    private ArrayList<Product> myProduct  = new ArrayList<Product>();
    private ArrayList<Product> myCart     = new ArrayList<Product>();
    private ArrayList<String>  favoritos  = new ArrayList<String>();
    private ArrayList<Message> messageBox = new ArrayList<Message>();

    //Atributos básicos
    public String  user     = "[edit_user]",
                   age      = "[edit_age]",
                   gender   = "[edit_gender]",
                   cpf      = "[edit_cpf]",
                   adress   = "[edit_adress]",
                   email    = "[edit_email]",
                   password = "[edit_password]";
    boolean aux = false;  
    float bank = 50;

    public JLabel     lblUsername;
    public JLabel     lblAge;
    public JLabel     lblGender;
    public JLabel     lblCpf;
    public JLabel     lblAdress;
    public JLabel     lblEmail;
    public JLabel     lblPassword;
    public JTextField txtUsername;
    public JFormattedTextField txtAge;
    public JTextField txtGender;
    public JFormattedTextField txtCpf;
    public JTextField txtAdress;
    public JTextField txtEmail;
    private JPasswordField txtPassword;

    public abstract void menu();
    

    //Método construtor para criar usuários, inserindo os dados iniciais.
    public User(){
        this.getContentPane().removeAll();
        this.revalidate();
        this.repaint();
        
        this.setTitle("Cadastro");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(false);
        this.setModal(true);
        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(7,2));
        JPanel menu  = new JPanel(new GridLayout(1,2));

        JButton enviar   = new JButton("Enviar");
        JButton cancelar = new JButton("Cancelar");

        enviar.setBackground(new Color(225,127,17));
        cancelar.setBackground(new Color(200,80,100));

        enviar.setCursor(new Cursor(12));
        cancelar.setCursor(new Cursor(12));

        menu.add(cancelar);
        menu.add(enviar);


        setUsername();
        panel.add(lblUsername);
        panel.add(txtUsername);

        setAge();
        panel.add(lblAge);
        panel.add(txtAge);

        setGender();
        panel.add(lblGender);
        panel.add(txtGender);

        setCpf();
        panel.add(lblCpf);
        panel.add(txtCpf);

        setAdress();
        panel.add(lblAdress);
        panel.add(txtAdress);

        setEmail();
        panel.add(lblEmail);
        panel.add(txtEmail);

        setPassword();
        panel.add(lblPassword);
        panel.add(txtPassword);

        this.add(panel, BorderLayout.CENTER);
        this.add(menu, BorderLayout.SOUTH);
        this.config();
        this.pack();
        this.repaint();   

        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user = txtUsername.getText();
                age = txtAge.getText();
                gender = txtGender.getText();
                cpf = txtCpf.getText();
                adress = txtAdress.getText();
                email = txtEmail.getText();
                password = new String(txtPassword.getPassword());

                if(!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                +"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") || password.length()<8){
                    JOptionPane.showMessageDialog(null, "E-mail ínvalido ou senha inferior a 8 de tamanho.\n\tTente Novamente.", "Aviso!", -1);

                }else {
                    if(validarCPF(cpf)){
                        Bd.getInstance().creatUser = true;
                        dispose();
                    }else {
                        JOptionPane.showMessageDialog(null, "CPF inválido.\n\tTente Novamente.", "Aviso!", -1);
                    }
                }
            }
        });

        cancelar.addActionListener(e -> {
            Bd.getInstance().creatUser = false;
            this.setVisible(false);
        });
    }

    //Método para editar alguns atributos dessa classe.text
    public void editUser(){
        Bd bancoDados = Bd.getInstance();
        JDialog window = new JDialog(bancoDados.mainWindow);
        window.setTitle("Edição");
        window.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(false);
        window.setModal(true);

        JPanel painel = new JPanel(new GridLayout(7,2));
        JPanel menu   = new JPanel(new GridLayout(1,3));

        JButton senha    = new JButton("Senha");
        JButton enviar   = new JButton("Enviar");
        JButton cancelar = new JButton("Cancelar");

        senha.setBackground(new Color(120,150,200));
        enviar.setBackground(new Color(0,200,200));
        cancelar.setBackground(new Color(200,80,100));

        senha.setCursor(new Cursor(12));
        enviar.setCursor(new Cursor(12));
        cancelar.setCursor(new Cursor(12));

        senha.setToolTipText("Habilita a edição da senha!");

        menu.add(enviar);
        menu.add(senha);
        menu.add(cancelar);

        this.txtCpf.setEditable(false);
        this.txtPassword.setEditable(false);

        painel.add(lblUsername);
        painel.add(txtUsername);

        painel.add(lblAge);
        painel.add(txtAge);

        painel.add(lblGender);
        painel.add(txtGender);

        painel.add(lblCpf);
        painel.add(txtCpf);

        painel.add(lblAdress);
        painel.add(txtAdress);

        painel.add(lblEmail);
        painel.add(txtEmail);

        painel.add(lblPassword);
        painel.add(txtPassword);
        

        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user = txtUsername.getText();
                age = txtAge.getText();
                gender = txtGender.getText();
                adress = txtAdress.getText();


                email = txtEmail.getText();
                password = new String(txtPassword.getPassword());

                if(!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                +"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") || password.length()<8){
                    JOptionPane.showMessageDialog(null, "E-mail ínvalido ou senha inferior a 8 de tamanho.\nTente Novamente.", "Aviso!", -1);

                }else {
                    Bd.getInstance().creatUser = true;
                    window.setVisible(false);
                }
            }
        });

        cancelar.addActionListener(e -> {
            Bd.getInstance().creatUser = false;
            window.setVisible(false);
        });

        senha.addActionListener(e -> {
            String passwordTest = JOptionPane.showInputDialog(this, "Insira sua senha atual:", "Verificador de senha.", -1);

            if(getPassword().equals(passwordTest)){
                txtPassword.setEditable(true); 
                JOptionPane.showMessageDialog(this, "Senha compatível campo 'Senha' liberado.", "", -1);
            }else{
                JOptionPane.showMessageDialog(this, "Senha imcompatível.", "Aviso!", 0);
            }
            window.repaint();
        });
        
        window.add(painel, BorderLayout.CENTER);
        window.add(menu, BorderLayout.SOUTH);
        window.pack();  
        window.setVisible(true); 
    }

    //Override para substituir o método toString() da class object para um padrão mais coerente a essa classe
    @Override
    public String toString() {
        return "User: "+this.user+"\n"+
               "Valor no banco: "+this.getValorBank()+"\n"+
               "Categoria preferida: \n"+getFavorito()+"\n"+
               "Age: "+this.age+"\n"+
               "Gender: "+this.gender+"\n"+
               "CPF: "+this.cpf+"\n"+
               "Adress: "+this.adress+"\n"+
               "E-mail: "+this.email+"\n";
    }

    //      getters and setters
    public void setUsername()
    {
        lblUsername = new JLabel("Digite seu nome:");
        txtUsername = new JTextField();
    }

    public void setAge()
    {
        MaskFormatter ageMask = null;
        try {
            ageMask = new MaskFormatter("##");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        lblAge = new JLabel("Digite sua idade:");
        txtAge = new JFormattedTextField(ageMask);
        txtAge.setColumns(2);
    }

    public void setGender()
    {
        lblGender = new JLabel("Digite seu gênero:");
        txtGender = new JTextField();
    }

    public void setCpf()
    {
        MaskFormatter cpfMask = null;
        try {
            cpfMask = new MaskFormatter("###.###.###-##");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        lblCpf = new JLabel("Digite seu CPF:");
        txtCpf = new JFormattedTextField(cpfMask);
        txtCpf.setColumns(15);
    }

    public void setAdress()
    {
        lblAdress = new JLabel("Digite seu endereço:");
        txtAdress = new JTextField();
    }

    public void setEmail()
    {
        lblEmail = new JLabel("Digite seu E-mail:");
        txtEmail = new JTextField();
    }

    public void setPassword()
    {
        lblPassword = new JLabel("Digite sua senha:");
        txtPassword = new JPasswordField();
    }

    public void setPassword(String str)
    {
        this.txtPassword.setText(str);
    }

    public String getPassword() {return this.password;}
    public String getEmail() {return this.email;}
    public String getAdress() {return this.adress;}
    public String getCpf() {return this.cpf;}
    public String getGender() {return this.gender;}
    public String getAge() {return this.age;}
    public String getUser() {return this.user;}


    public void setMyCart(Product product) {
        this.myCart.add(product);
    }

    public void setValorBank(float plus){
        bank += plus;
    }   
    public void compra(float m){

        this.bank -= m;
    }

    public float getValorBank(){return this.bank;}

    //Calcula a moda das categorias dos intem comprados por (this) 
    public String getFavorito() {
        Map<String, Integer> counts = new HashMap<>();
        
        for (String categoria : favoritos) {
            counts.put(categoria, counts.getOrDefault(categoria, 0) + 1);
        }
        
        String moda = "";
        int maxCount = 0;
        
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > maxCount) {
                moda = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        
        return moda;
    }

    public ArrayList<Product> getMycart(){return this.myCart;}

    public void setMyProduct(Product product) {this.myProduct.add(product);}
    
    public ArrayList<Product> getMyProduct() {return this.myProduct;}

    public void setMessageBox(Message e) {this.messageBox.add(e);}

    public ArrayList<Message> getMessageBox() {return this.messageBox;}

    private void config(){
        test(lblUsername, txtUsername);
        test(lblAge, txtAge);
        test(lblGender, txtGender);
        test(lblCpf, txtCpf);
        test(lblAdress, txtAdress);
        test(lblEmail, txtEmail);
        test(lblPassword, txtPassword);
    }

    private void test(JLabel lbl, JTextField txt){
        lbl.setBackground(new Color(51,60,76));
        lbl.setOpaque(true);
        lbl.setForeground(new Color(225,127,17));

        txt.setBackground(Color.lightGray);
    }

    private boolean validarCPF(String cpf) {
        try{
            cpf = cpf.replaceAll("[^0-9]", "");
        }catch(PatternSyntaxException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Err sintaxe CPF' String", "Exception", 0);
            return false;
        }
    
        if (cpf.length() != 11) {
            return false;
        }
    
        int soma = 0;
        int digito1, digito2;
    
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
    
        digito1 = 11 - (soma % 11);
    
        if (digito1 > 9) {
            digito1 = 0;
        }
    
        soma = 0;
    
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
    
        digito2 = 11 - (soma % 11);
    
        if (digito2 > 9) {
            digito2 = 0;
        }
    
        return Character.getNumericValue(cpf.charAt(9)) == digito1 && Character.getNumericValue(cpf.charAt(10)) == digito2;
    }
}
