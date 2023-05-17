package nozama.products;

import nozama.products.command.*;
import nozama.users.*;
import nozama.products.states.AvailableState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import javax.swing.*;

public class Casa extends Product implements ActionListener{

    public int largura, comprimento;
    public boolean construido, loc;
    JLabel lblLargura;
    JLabel lblComprimento,lblConst,lblTer;
    public JTextField txtLargura,txtComprimento;
    public JCheckBox boxConst, boxTer;
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
        JDialog window = new JDialog(this, "Edição de produto", true);
        window.setResizable(false);
        window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        window.setLayout(new BorderLayout());

        JPanel center      = new JPanel(new GridLayout(6, 2));
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

        center.add(lblLargura);
        center.add(txtLargura);

        center.add(lblComprimento);
        center.add(txtComprimento);
        
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
                this.largura = Integer.valueOf(txtLargura.getText());
                this.comprimento = Integer.valueOf(txtComprimento.getText());
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

            Command buyCasa = new PostCasa(new CommandReceiver(), this);
            buyCasa.execute();
            tela.dispose();


        }
    }
    
}