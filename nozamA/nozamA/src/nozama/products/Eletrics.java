package nozama.products;

import nozama.Profile;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JLabel;

public class Eletrics extends Product implements ActionListener{

    Scanner input = new Scanner(System.in);

    int resolucao;
    int capacidade;
    int choise;
    private String category = "Eletrônicos";
    String menuReso = "\n1 - 3 polegadas\n2 - 5 polegadas\n3 - 7 polegadas\n=>";
    String menuCapa = "\n1 - 32 gigas\n2 - 64gigas\n3 - 128 gigas\n=>";


    JLabel lblRes,lblCapa;
    String[] listReso = {"3 polegadas", "5 polegadas", "7 polegadas"};
    String[] listCapa = {"32 gigas", "64gigas", "128 gigas"};
    JComboBox comboMenuR, comboMenuCapa;
    public Eletrics(Profile e){
        super(e);

        setCapa();
        setReso();
        panel.setLayout(new GridLayout(6,2));

        panel.add(lblRes);
        panel.add(comboMenuR);
        panel.add(lblCapa);
        panel.add(comboMenuCapa);
        enviar.addActionListener(this);
        tela.setTitle("Eletronicos");
        tela.pack();
        tela.setVisible(true);
        

    }

    public void setReso(){
        
        lblRes = new JLabel("Selecione a Resolução do produto:");

        comboMenuR = new JComboBox(listReso);


        

    }

    public void setCapa(){
       

        lblCapa = new JLabel("Selecione a Capacidade do produto:");
        comboMenuCapa =  new JComboBox(listCapa);
        
        
        

    }
    public int valoriza(){
        switch(this.capacidade){
            case 32:  return 50;
            case 64:  return 25;
            case 128: return 10;
            default: return 0;
        }
    }
    @Override
    public String toString() {
        String aux = "\nResolução do produto: "+resolucao+"polegadas;\n"+
                     "Capacidade do produto: "+capacidade+"gigas;\n"+
                     "Desvalorização do produto: "+valoriza()+"%\n";
        return super.toString()+aux;
    }
    

    @Override
    public String getCategory() {
        return this.category;
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

       String word = "";
            for(char k : comboMenuCapa.getSelectedItem().toString().toCharArray()){
                if(Character.isDigit(k)){
                    word += k;
                }
            
        }

        this.capacidade = Integer.parseInt(word);

       
        word = "";
        for(char k : comboMenuR.getSelectedItem().toString().toCharArray()){
            if(Character.isDigit(k)){
                word += k;
            }
    }
        this.resolucao = Integer.parseInt(word);
        tela.dispose();
        System.out.println(this.toString());
        }
    }

    
}