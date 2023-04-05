package nozama.products;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;

import nozama.Profile;

public abstract class Product extends JDialog{  
    Scanner input = new Scanner(System.in);

    //Array para avaliações
    ArrayList<Integer> rate = new ArrayList<Integer>();

    //Usuário q postou
    public Profile owner;

    //Atributos básicos
             String name,
                   description, 
                   category = "default";
     float price;
     int qtdProduto;

    JLabel lblName;
    JLabel lblDescription;
    JLabel lblPrice;
    JLabel lblQtd;
    JTextField txtName;
    JTextField txtDescription;
    JTextField txtPrice;
    JTextField txtQtd;
    JDialog tela;
    JPanel panel;
    JButton enviar;
    //Método construtor para criar produtos, obrigando a inserir os dados relevantes.
    public Product(Profile o){
        this.owner = o;
        tela = new JDialog(this, "Default", true);
        tela.setResizable(false);
        tela.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        tela.setLayout(new BorderLayout());

         panel = new JPanel(new GridLayout(4,2));
        JPanel panel2 = new JPanel();

         enviar = new JButton("Enviar");

        enviar.setCursor(new Cursor(12));
        enviar.setBackground(new Color(0,200,200));

        panel2.add(enviar);

        setName();
        panel.add(lblName);
        panel.add(txtName);

        setDescripton();
        panel.add(lblDescription);
        panel.add(txtDescription);

        setPrice();
        panel.add(lblPrice);
        panel.add(txtPrice);

        setQtd();
        panel.add(lblQtd);
        panel.add(txtQtd);

        panel.setVisible(true);
        panel2.setVisible(true);

        tela.add(panel, BorderLayout.CENTER);
        tela.add(panel2, BorderLayout.SOUTH);

       /*  enviar.addActionListener(e ->{
            this.name        = txtName.getText();
            this.description = txtDescription.getText();
            this.price       = Float.parseFloat(txtPrice.getText());
            this.qtdProduto  = Integer.parseInt(txtQtd.getText());
            this.add(new JLabel(this.name));
        });*/
    }

    //Método para editar alguns atributos dessa classe, individualmente.
    public abstract void editProduct();

    //Override para substituir o método toString() da class object para um padrão mais coerente a essa classe
    @Override
    public String toString() { 
        return "\n\n\tDono do produto: "+this.owner.getUser()+";\n"+
               "Nome do produto: "+getName()+";\n"+
               "Descrição do produto: "+getDescription()+";\n"+
               "Categoria do produto: "+getCategory()+";\n"+
               "Preço do produto: "+getPrice()+";\n"+
               "Quantidade:"+this.qtdProduto+"\n"+
               "Avaliação: "+getRate();
    }

    //      getters and setters
    public void setName(){
        lblName = new JLabel("Insira o nome do produto:");
        txtName = new JTextField();
    }

    public String getName() {return this.name;}

    public void setDescripton(){
        lblDescription = new JLabel("Insira a descrição do produto:");
        txtDescription = new JTextField();
    }

    public String getDescription() {return this.description;}

    public void setPrice(){
        lblPrice = new JLabel("Insira o preço do produto:");
        txtPrice = new JTextField();
    }

    public float getPrice() {return this.price;}

    public void setQtd(){
        lblQtd = new JLabel("Insira a quantidade do produto:");
        txtQtd = new JTextField();
    }

    // Insere uma nova avaliação no produto.
    public void setRate(int aval){
       

        this.rate.add(aval);
    }


    // Calcula a média das avaliações.
    public String getRate(){
        int size = this.rate.size(), star = 0;

        for(Integer i : this.rate) star += i;

        if(size == 0) { return "Não há avaliações ainda.\n"; }
        star /= size;

        return ""+star;
    }

    // Reduz o número do produto(this) disponivel no feed
    public void compra(){
        this.qtdProduto--;
    }

    public int getQtd(){
        return this.qtdProduto;
    }

    public String getCategory(){
        return this.category;
    }
}