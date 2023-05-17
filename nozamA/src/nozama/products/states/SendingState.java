package nozama.products.states;

import nozama.products.Product;
import nozama.app.Message;;

//Estado para produto enviado(após pagamento)
public class SendingState implements State{
    @Override
    public String actionState(Product product){

        Message newMessage = new Message(product.owner);
        newMessage.setMessage("Sistema nozamA.", "O produto "+product.getName()+" está Com uma solicitação de envio.\naguardando envio.", 1);
        product.owner.getMessageBox().add(newMessage);

        String str = "Seu produto já foi enviado com sucesso e em breve chegará a sua casa.";
        return str;
    }
}