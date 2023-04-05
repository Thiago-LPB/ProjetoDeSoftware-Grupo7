package nozama.products;

import nozama.Profile;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JCheckBox;

import javax.swing.JLabel;
import javax.swing.JTextField;


public class Casa extends Product implements ActionListener{

    Scanner input = new Scanner(System.in);

    int largura, comprimento;
    boolean construido, loc;
    JLabel lblLargura;
    JLabel lblComprimento,lblConst,lblTer;
    JTextField txtLargura,txtComprimento;
    JCheckBox boxConst, boxTer;
    public Casa(Profile e){
        
        super(e);
        setTam();
        setBool();

        panel.setLayout(new GridLayout(9,2));
        
        panel.add(lblLargura);
        panel.add(txtLargura);
        panel.add(lblComprimento);
        panel.add(txtComprimento);
        
        panel.add(lblConst);
        panel.add(boxConst);
        panel.add(lblTer);
        panel.add(boxTer);
        tela.setTitle("Casa");
       
        
        enviar.addActionListener(this);
         tela.pack();
        tela.repaint();
        tela.setVisible(true);

    }

    private void setTam(){
      

        lblLargura = new JLabel("Qual a largura do terreno? ");
        txtLargura = new JTextField();
    
        lblComprimento = new JLabel("Qual o comprimento do terreno? ");
        txtComprimento = new JTextField();
    }

    private void setBool(){
 
lblConst = new JLabel("Há área construida no terreno?");
boxConst = new JCheckBox();

lblTer = new JLabel("Terreno bem localizado?");
boxTer = new JCheckBox();

        
    }

    @Override
    public String getCategory() {
        return this.getClass().getName();
    }

    @Override
    public String toString() {
        String aux = "\nLargura: "+largura+";\n"+
                     "Comprimento: "+comprimento+";\n"+
                     "Área construida: "+construido+";\n"+
                     "Boa localização: "+loc+".\n";
        return super.toString()+aux;
    }

    @Override
    public void editProduct() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enviar){
            name        = txtName.getText();
        description = txtDescription.getText();
        price       = Float.parseFloat(txtPrice.getText());
        qtdProduto  = Integer.parseInt(txtQtd.getText());
        //this.add(new JLabel(this.name));
        this.largura = Integer.valueOf(txtLargura.getText());
        this.comprimento = Integer.valueOf(txtComprimento.getText());
        this.construido = boxConst.isSelected();
        this.loc = boxTer.isSelected();
        tela.dispose();
        System.out.println(this.toString());
        }
    }
    
}