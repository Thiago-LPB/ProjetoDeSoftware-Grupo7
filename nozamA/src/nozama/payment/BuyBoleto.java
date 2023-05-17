package nozama.payment;

import java.time.LocalDateTime;
import java.util.Random;

import nozama.users.*;
import nozama.products.Product;


public class BuyBoleto extends Buy {

    private LocalDateTime vencimento;

    public BuyBoleto(Profile buyer, Profile seller, Product product){
        super(buyer, seller, product);       
    }

    public String imprimir(){
        LocalDateTime now = LocalDateTime.now();
        this.vencimento = now.plusDays(7);
        Random r = new Random();
        int n;
        
        String bar = r.nextInt(99999) + 11111+".";
        for(int i = 0;i<5;i++){
            n = r.nextInt(99999) + 11111;
            bar += "" + n;
            if(i+1==5) break;
            bar += ".";
        }
        this.vencimento = this.vencimento.plusDays(7);
        return "\nData de emissão do boleto: "+now.getDayOfMonth()+"/"+now.getMonthValue()+"/"
        +now.getYear()+"\n"+"Data de Vencimento do boleto: "+(vencimento.getDayOfMonth())+"/"+vencimento.getMonthValue()+"/"+vencimento.getYear()+
        "\nNome do comprador: "+ buyer.getUser()+"\nNome do vendedor: "+ seller.getUser()+"\nValor: "+product.getPrice()+"\nCódigo de Barras: "+bar;
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