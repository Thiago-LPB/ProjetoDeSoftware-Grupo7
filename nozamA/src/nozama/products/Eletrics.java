package nozama.products;

import nozama.products.command.*;
import nozama.users.*;
import nozama.products.states.AvailableState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import javax.swing.*;

public class Eletrics extends Product implements ActionListener{

    public int resolucao;
    public int capacidade;
    public int choise;
    private String category = "Eletrônicos";
    String menuReso = "\n1 - 3 polegadas\n2 - 5 polegadas\n3 - 7 polegadas\n=>";
    String menuCapa = "\n1 - 32 gigas\n2 - 64gigas\n3 - 128 gigas\n=>";


    JLabel lblRes,lblCapa;
    String[] listReso = {"3 polegadas", "5 polegadas", "7 polegadas"};
    String[] listCapa = {"32 gigas", "64gigas", "128 gigas"};
    public JComboBox comboMenuR, comboMenuCapa;
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
        JDialog window = new JDialog(this, "Edição de produto", true);
        window.setResizable(false);
        window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        window.setLayout(new BorderLayout());

        JPanel center      = new JPanel(new GridLayout(4, 2));
        JPanel confirPanel = new JPanel();

        JButton button = new JButton("Confirmar");
        button.setCursor(new Cursor(12));
        button.setBackground(new Color(0,200,200));
        confirPanel.setBackground(new Color(51, 60, 76));

        confirPanel.add(button);

        center.add(lblName);
        center.add(txtName);

        center.add(lblDescription);
        center.add(txtDescription);
        
        center.add(lblPrice);
        center.add(txtPrice);

        center.add(lblQtd);
        center.add(txtQtd);
        
        JTextField productState = new JTextField(this.getState().actionState(null));
        productState.setEditable(false);
        productState.setOpaque(true);

        window.add(productState, BorderLayout.NORTH);
        window.add(center, BorderLayout.CENTER);
        window.add(confirPanel, BorderLayout.SOUTH);

        button.addActionListener(e -> {
            try{
                if(!txtName.getText().isEmpty()) this.name = txtName.getText();
                if(!txtDescription.getText().isEmpty()) this.description = txtDescription.getText();
                this.price       = Float.parseFloat(txtPrice.getText());
                this.qtdProduto  = Integer.parseInt(txtQtd.getText());
            }catch(NumberFormatException b){
                JOptionPane.showMessageDialog(this, "Insira um número válido.", "Aviso: Exception", -1, null);
            }catch(NullPointerException c){
                JOptionPane.showMessageDialog(this, "Preencha todos os valores.", "Aviso: Exception", -1, null);
            } finally{
                JOptionPane.showMessageDialog(null, "Produto editado com sucesso!");
                if(this.qtdProduto > 0) this.setState(new AvailableState());
                window.dispose();
            }
        });
        window.pack();
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == enviar){

            Command buyEletrics = new PostEletrics(new CommandReceiver(), this);
            buyEletrics.execute();
            tela.dispose();
        }
    }

    
}