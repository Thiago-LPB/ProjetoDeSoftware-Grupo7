package nozama.products.states;

import nozama.products.Product;
import nozama.app.Message;

//Produto esgotado
public class SoldOutState implements State{
    @Override
    public String actionState(Product product){
        
        Message newMessage = new Message(product.owner);
        newMessage.setMessage("Sistema nozamA.", "O produto "+product.getName()+" está esgotado.\naguardo de reposição.", 1);
        product.owner.getMessageBox().add(newMessage);

        String str =  "Produto Esgotado... Aguardando reposição!";
        return str;
    } 
}