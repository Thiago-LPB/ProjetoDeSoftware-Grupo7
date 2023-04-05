package nozama.products;

import nozama.Profile;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Comida  extends Product implements ActionListener{

    Scanner input = new Scanner(System.in);

    boolean lactose, gluten, lata;
    int qtd, validade;
    JLabel lbltipo,lblqtd,lbldias;
    JTextField txtqtd,txtdias;
    JCheckBox cLac, cGl,cLat;
    JPanel p;
    public Comida(Profile e){
        super(e);
        setBools();
        setQdt();
        setValidade();
        p = new JPanel();
        p.setLayout(new GridLayout(1,3));
        panel.setLayout(new GridLayout(8,2));
            
        
        
        panel.add(lblqtd);
        panel.add(txtqtd);
        
        panel.add(lbldias);
        panel.add(txtdias);


        panel.add(lbltipo);

        p.add(cLac);
        p.add(cGl);
        p.add(cLat);
        p.setSize(50,50);
        panel.add(p);
        tela.pack();
        tela.setTitle("Comida");
       
        enviar.addActionListener(this);
        
        tela.repaint();
        tela.setVisible(true);
        
    }

    public void setBools(){
       
        lbltipo = new JLabel("Selecione caracteristicas do produto:");
        cLac = new JCheckBox("Lactose ");
        cGl = new JCheckBox("Gluten");
        cLat = new JCheckBox("Enlatado");


    }

    public void setQdt(){
     
        lblqtd = new JLabel("Quantas pessoas essa comida serve?");
        txtqtd = new JTextField();
       
    }


    public void setValidade(){
        lbldias = new JLabel("Quantos dias seu produto tem de valiadade?");
        txtdias = new JTextField();
    }


    @Override
    public String getCategory() {
        return this.getClass().getName();
    }

    @Override
    public String toString() {
        String aux = "\nContém Lactose: "+lactose+";\n"+
                     "Contém Gluten: "+gluten+";\n"+
                     "Enlatado: "+lata+";\n"+
                     "Quantidade de pessoas servidas: "+qtd+";\n"+
                     "Validade: "+validade+".\n";
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
        this.lactose = cLac.isSelected();
        this.gluten = cGl.isSelected();
        this.lata = cLat.isSelected();
        this.qtd = Integer.valueOf(txtqtd.getText());
        this.validade = Integer.valueOf(txtdias.getText());
        tela.dispose();
        System.out.println(this.toString());
        }
    }
}