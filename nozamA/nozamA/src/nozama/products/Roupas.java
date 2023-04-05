package nozama.products;

import nozama.Profile;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JLabel;

public class Roupas  extends Product implements ActionListener{

    Scanner input = new Scanner(System.in);

    String size;
    String color;
    int choise;
    JLabel lblTamanho,lblCor;
    JComboBox comboTamanho, comboCor;
    String[] listTam = {"Small", "Medium", "Large"};
    String[] listCor = {"Branco","Preto", "Large"};
   

    public Roupas(Profile e){
        super(e);
        setDate();
        panel.setLayout(new GridLayout(6,2));
        panel.add(lblTamanho);
        panel.add(comboTamanho);
        panel.add(lblCor);
        panel.add(comboCor);
        tela.setTitle("Roupas");
        tela.pack();
        enviar.addActionListener(this);
        tela.setVisible(true);
    }

    public void setDate(){
    
        lblTamanho = new JLabel("Selecione o tamanho do produto:");

        comboTamanho = new JComboBox(listTam);

       
        lblCor = new JLabel("Selecione a cor da roupa:");
        comboCor = new JComboBox(listCor);
        
    }

    @Override
    public String getCategory() {
        return this.getClass().getName();
    }

    @Override
    public String toString() {
        String aux = "\nTamanho da roupa: "+size+";\n"+
                     "Cor da roupa: "+color+"\n";
        return super.toString() + aux;
    }
    
    @Override
    public void editProduct() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == enviar){
            this.name        = txtName.getText();
            this.description = txtDescription.getText();
            this.price       = Float.parseFloat(txtPrice.getText());
            this.qtdProduto  = Integer.parseInt(txtQtd.getText());
            this.color = comboCor.getSelectedItem().toString();
            this.size = comboTamanho.getSelectedItem().toString();
            tela.dispose();
            System.out.println(this.toString());
        }
    }
}