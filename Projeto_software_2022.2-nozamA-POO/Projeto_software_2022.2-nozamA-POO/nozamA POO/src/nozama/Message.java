package nozama;

import java.util.Scanner;

import nozama.payment.Buy;

class Message{

    Scanner input = new Scanner(System.in);

    String message, who;
    Profile aux;
    int type = -1; // 0 - mensagem enviado por usuário
                   // 1 - mensagem enviada pelo sistema de producot
                   // 2 - mensagem de admin
                   // 3 - mensagem anonima

    public Message(){}

    public Message(Profile user){
        this.aux = user;
    }

    public void setMessage(String who){
        this.who = who;

        System.out.printf("\n\tQual a sua mensagem?\n=>");
        this.message = input.nextLine();
        this.type = 0;

    }

    public void setMessageProduct(String who, String productStr){
        this.who     = who;
        this.message = productStr;
        this.type = 1; 
    }

    public void setMessageAdmin(String who){
        this.setMessage(who);
        this.type = 2;
    }

    public void setMessage(){
        this.message = input.nextLine();
        this.type = 3;
    }

    public void setBuyProduct(String who, String product, Buy payment){
        this.who = who;
        this.message = "\tItem "+product+" foi Comprado por " + who+"\n"+"compra: "+payment.toString()+"\n";
        this.type = 4;
    }

    @Override
    public String toString() {
        switch(this.type){
            case 0: return "\tUsuário: "+this.who+", enviou uma message.\nMessage: "+this.message+"\n";
            case 1: return "\tVendedor: "+this.who+", postou um novo produto no feed.\nProduto:\n"+this.message+"\n";
            case 2: return "\tAdmin: "+this.who+".\nMessage: "+message+"\n"+"essa mensagem não poderá ser respondida"+"\n"; 
            case 3: return "\tAnônimo\nMessage:"+this.message;
            case 4: return this.message; 
            default: return "\nMessage: Algo de errado ocorreu com sua mensagem!\n";
        }
    }
}