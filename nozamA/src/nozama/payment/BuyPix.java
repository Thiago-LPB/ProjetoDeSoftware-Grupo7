package nozama.payment;

import java.time.LocalDateTime;

import nozama.users.*;
import nozama.products.Product;


public class BuyPix extends Buy{
    
    private LocalDateTime vencimento;

    public BuyPix(Profile buyer, Profile seller, Product product){
        super(buyer, seller, product);        
    }

    public String imprimir(){
        LocalDateTime now = LocalDateTime.now();
        this.vencimento = now.plusDays(7);
        this.vencimento = this.vencimento.plusDays(3);
        return "\nDia de hoje: "+now.getDayOfMonth()+"/"+now.getMonthValue()+"/"
        +now.getYear()+"\n"+"Data de Vencimento do pix: "+(vencimento.getDayOfMonth())+"/"+vencimento.getMonthValue()+"/"+vencimento.getYear()+
        "\nNome do comprador: "+ buyer.getUser()+"\nNome do vendedor: "+ seller.getUser()+"\nValor:"+product.getPrice()+"\n"+"Chave Pix: "+seller.getEmail();
    }


    @Override
    public String getVencimento() {
        return "Data de Vencimento: "+this.vencimento.getDayOfMonth()+"/"+this.vencimento.getMonthValue()+"/"+this.vencimento.getYear();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}