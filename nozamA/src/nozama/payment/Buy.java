package nozama.payment;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;

import nozama.users.*;
import nozama.products.Product;
import nozama.products.command.*;
import nozama.products.command.CommandReceiver;


public abstract class Buy{
    
    public Profile buyer, seller;
    public Product product;

    public JTextArea txt;
    JDialog j;
    JButton comprar, cancelar;
    JPanel panel;

    public Buy(Profile buyer, Profile seller, Product product){
        this.buyer = buyer;
        this.seller = seller;
        this.product = product;
        j = new JDialog();
        comprar = new JButton("Comprar");
        cancelar =  new JButton("Cancelar");
        cancelar.addActionListener(e -> {
            j.dispose();
        });
        comprar.addActionListener(e -> {
            Command newBuy = new BuyAll(new CommandReceiver(), this, product);
            newBuy.execute();
            j.dispose();
        });
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(comprar);
        panel.add(cancelar);
        j.setLayout(new BorderLayout());
        j.setModal(true);
        txt = new JTextArea(this.imprimir());
        j.add(txt,BorderLayout.CENTER);        
        j.add(panel,BorderLayout.SOUTH);
        j.setTitle("Finalizar pagamento");
        j.pack();
        j.setVisible(true);
    }

    private boolean ItsPossibleBuy(){
        if(buyer.getValorBank() >= product.getPrice()) return true;
        return false;
    }

    public boolean Pay(){
        if(ItsPossibleBuy()){
          
            this.seller.setValorBank(product.getPrice());
            this.buyer.compra(product.getPrice());
            product.compra();
            JOptionPane.showMessageDialog(null, "Produto comprado!", "Compra", JOptionPane.INFORMATION_MESSAGE);
            System.out.printf("\nCompra efetuada com sucesso!!\n");

            return true;
        } 
        JOptionPane.showMessageDialog(null, "Saldo Insuficiente", "Erro", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public abstract String imprimir ();
  
    public abstract String getVencimento();

    @Override
    public String toString() {
        return "Produto comprado com sucesso por "+buyer.getUser()+"\nE-mail: "+buyer.getEmail();
    }
}