package nozama.products;

import nozama.products.command.*;
import nozama.users.*;
import nozama.products.states.AvailableState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import javax.swing.*;

public class Roupas  extends Product implements ActionListener{

    public String size;
    public String color;
    int choise;
    JLabel lblTamanho,lblCor;
    public JComboBox comboTamanho, comboCor;
    String[] listTam = {"Small", "Medium", "Large"};
    String[] listCor = {"Branco","Preto", "Colorido"};
   

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

        center.add(lblTamanho);
        center.add(comboTamanho);

        center.add(lblCor);
        center.add(comboCor);
        
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
                this.color = comboCor.getSelectedItem().toString();
                this.size = comboTamanho.getSelectedItem().toString();
            }catch(NumberFormatException b){
                JOptionPane.showMessageDialog(this, "Insira um número válido.", "Aviso: Exception", -1, null);
            }catch(NullPointerException c){
                JOptionPane.showMessageDialog(this, "Preencha todos os valores.", "Aviso: Exception", -1, null);
            } finally{
                if(this.qtdProduto > 0) this.setState(new AvailableState());
                JOptionPane.showMessageDialog(null, "Produto editado com sucesso!");
                window.dispose();
            }
        });
        window.pack();
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == enviar){

            Command buyRoupa = new PostRoupas(new CommandReceiver(), this);
            buyRoupa.execute();
            tela.dispose();
            
        }
    }
}