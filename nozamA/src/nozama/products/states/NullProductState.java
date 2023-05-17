package nozama.products.states;

import nozama.products.Product;
import nozama.app.Message;

//Estado para produto em Criação 
public class NullProductState implements State{
    @Override
    public String actionState(Product product){

        Message newMessage = new Message(product.owner);
        newMessage.setMessage("Sistema nozamA.", "O produto "+product.getName()+" está em estado Nulo finalizea criação do mesmo.", 1);
        product.owner.getMessageBox().add(newMessage);

        String str =  "Produto em estado de Criação. Por favor finalizar o produto ou excluo-a!";
        return str;
    }
}