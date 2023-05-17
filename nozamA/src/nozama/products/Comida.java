package nozama.products;

import nozama.products.command.*;
import nozama.users.*;
import nozama.products.states.AvailableState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import javax.swing.*;

public class Comida  extends Product implements ActionListener{


    public boolean lactose, gluten, lata;
    public int qtd, validade;
    JLabel lbltipo,lblqtd,lbldias;
    public JTextField txtqtd,txtdias;
    public JCheckBox cLac, cGl,cLat;
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
        JDialog window = new JDialog(this, "Edição de produto", true);
        window.setResizable(false);
        window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        window.setLayout(new BorderLayout());

        JPanel center      = new JPanel(new GridLayout(5, 2));
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
        
        center.add(lbldias);
        center.add(txtdias);

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
                this.price = Float.parseFloat(txtPrice.getText());
                this.qtdProduto = Integer.parseInt(txtQtd.getText());
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

            Command buyComida = new PostComida(new CommandReceiver(), this);
            buyComida.execute();
            tela.dispose();
        }
    }
}