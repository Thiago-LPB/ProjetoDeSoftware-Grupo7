package nozama.payment;

import java.awt.image.*;

import nozama.Profile;
import nozama.products.Product;
import java.io.File;

public class BuyPix extends Buy{
    
    private int vencimento;
    private float valorMinimo;
    private String productState;
    private BufferedImage QrCode;
    
    public BuyPix(Profile buyer, Profile seller, Product product){
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
        return super.toString()+"\n\n"+gerarQrCode();
    }

    public File gerarQrCode(){
//nome do comprador como chave
        File qr = new File("/QrCode.png");
        return qr;
    }
    
}