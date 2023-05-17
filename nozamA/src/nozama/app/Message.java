package nozama.app;

import nozama.payment.*;
import nozama.users.*;

public class Message{
    String message, who;
    public User aux;
    public int type = -1; 

    // type 0 - mensagem enviado por usuário
    // type 1 - mensagem enviada pelo sistema de producot
    // type 2 - mensagem de admin
    // type 3 - mensagem anonima

    public Message(User user){
        this.aux = user;
    }

    public void setMessage(String who, String message, int type){
        this.who = who;
        this.message = message;
        this.type = type;

        switch(this.type){
            case -1: message = "Null message setting default!"; break;
            case 0:  message =  "\tUsuário: "+this.who+", enviou uma message.\nMessage: "+this.message+"\n"; break;
            case 1:  message = "\tVendedor: "+this.who+", postou um novo produto no feed.\nProduto:\n"+this.message+"\n"; break;
            case 2:  message = "\tAdmin: "+this.who+".\nMessage: "+message+"\n"+"essa mensagem não poderá ser respondida"+"\n"; break;
            case 3:  message = "\tAnônimo\nMessage:"+this.message; break;
            default: throw new IllegalStateException("Tipo de Mesangem não inserida.");
        }
    }

    public void setBuyProduct(String who, String product, Buy payment){
        this.who = who;
        if(payment != null) {
            this.message = "\tItem "+product+" foi Comprado por " + who+"\n"+"compra: "+payment.toString()+"\n";
        } else {
            throw new IllegalArgumentException("Método de pagamento não pode ser null!");
        }
        this.type = 4;
    }

    @Override
    public String toString() {
        return this.message;
    }
}