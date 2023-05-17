package nozama.products.states;

import nozama.products.Product;
import nozama.app.Message;

//Estado para produto disponível para venda
public class AvailableState implements State{
    @Override
    public String actionState(Product product){

        Message newMessage = new Message(product.owner);
        newMessage.setMessage("Sistema nozamA.", "O produto "+product.getName()+" está disponível para compra...\nBoas vendas!", 1);
        product.owner.getMessageBox().add(newMessage);

        String str = "Produto disponível, boas compras!";
        return str;
    }
}