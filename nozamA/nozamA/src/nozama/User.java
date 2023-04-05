package nozama;

import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import nozama.products.Product;

class User extends JDialog{
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
    public JTextField txtAge;
    public JTextField txtGender;
    public JTextField txtCpf;
    public JTextField txtAdress;
    public JTextField txtEmail;
    private JPasswordField txtPassword;
    

    //Método construtor para criar usuários, inserindo os dados iniciais.
    public User(){
        this.getContentPane().removeAll();
        this.revalidate();
        this.repaint();
        
        this.setTitle("Cadastro");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //this.setSize(200, 100);
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
                Bd.getInstance().creatUser = true;
                dispose();
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
                Bd.getInstance().creatUser = true;
                window.setVisible(false);
            }
        });

        cancelar.addActionListener(e -> {
            Bd.getInstance().creatUser = false;
            window.setVisible(false);
        });

        senha.addActionListener(e -> {
            txtPassword.setEditable(true);
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
       lblAge = new JLabel("Digite sua idade:");
       txtAge = new JTextField();
    }

    public void setGender()
    {
        lblGender = new JLabel("Digite seu gênero:");
        txtGender = new JTextField();
    }

    public void setCpf()
    {
        lblCpf = new JLabel("Digite seu CPF:");
        txtCpf = new JTextField();
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
        lblUsername.setBackground(new Color(51,60,76));
        lblAge.setBackground(new Color(51,60,76));
        lblGender.setBackground(new Color(51,60,76));
        lblCpf.setBackground(new Color(51,60,76));
        lblAdress.setBackground(new Color(51,60,76));
        lblEmail.setBackground(new Color(51,60,76));
        lblPassword.setBackground(new Color(51,60,76));

        lblUsername.setOpaque(true);
        lblAge.setOpaque(true);
        lblGender.setOpaque(true);
        lblCpf.setOpaque(true);
        lblAdress.setOpaque(true);
        lblEmail.setOpaque(true);
        lblPassword.setOpaque(true);

        lblUsername.setForeground(new Color(225,127,17));
        lblAge.setForeground(new Color(225,127,17));
        lblGender.setForeground(new Color(225,127,17));
        lblCpf.setForeground(new Color(225,127,17));
        lblAdress.setForeground(new Color(225,127,17));
        lblEmail.setForeground(new Color(225,127,17));
        lblPassword.setForeground(new Color(225,127,17));

        txtUsername.setBackground(Color.lightGray);
        txtAge.setBackground(Color.lightGray);
        txtGender.setBackground(Color.lightGray);
        txtCpf.setBackground(Color.lightGray);
        txtAdress.setBackground(Color.lightGray);
        txtEmail.setBackground(Color.lightGray);
        txtPassword.setBackground(Color.lightGray);
    }
}
