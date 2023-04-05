package nozama.payment;

import nozama.Profile;
import nozama.products.Product;

public class BuyBoleto  extends Buy{

    private int vencimento;
    private float valorMinimo;
    private String productState;
    public BuyBoleto(Profile buyer, Profile seller, Product product){
        super(buyer, seller, product);
    }

    @Override
    public void setVencimento(int v) {
       this.vencimento = v;
    }

    @Override
    public void setValorMinimo(float v) {
        this.valorMinimo = v;
    }

    @Override
    public void setProductState(String c) {
       this.productState = c;

    }

    @Override
    public int getVencimento() {
        return this.vencimento;
    }

    @Override
    public float getValorMinimo() {
        return this.valorMinimo;
    }

    @Override
    public String getProductState() {
        return this.productState;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
}
