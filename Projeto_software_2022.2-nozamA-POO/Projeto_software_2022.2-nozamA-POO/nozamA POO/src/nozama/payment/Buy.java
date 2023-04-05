package nozama.payment;

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
            System.out.printf("\nCompra efetuada com sucesso!!\n");
            return true;
        } 
        return false;
    }

    public abstract void setVencimento(int v);
    public abstract void setValorMinimo(float v);
    public abstract void setProductState(String c);
    public abstract int getVencimento();
    public abstract float getValorMinimo();
    public abstract String getProductState();

    @Override
    public String toString() {
        return "Produto comprado com sucesso por "+buyer.getUser()+"\nemial: "+buyer.getEmail();
    }
}