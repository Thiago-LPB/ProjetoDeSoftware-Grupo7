package nozama.payment;

import javax.swing.JOptionPane;

import nozama.Profile;
import nozama.products.Product;

public abstract class Buy{
    
    Profile buyer, seller;
    Product product;

    public Buy(Profile buyer, Profile seller, Product product){
        this.buyer = buyer;
        this.seller = seller;
        this.product = product;
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

  
    public abstract String getVencimento();


    @Override
    public String toString() {
        return "Produto comprado com sucesso por "+buyer.getUser()+"\nemial: "+buyer.getEmail();
    }
}