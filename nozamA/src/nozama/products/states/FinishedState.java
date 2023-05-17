package nozama.products.states;

import nozama.products.Product;

//Estado para produto finalizado(compra, envio e recebimento)
public class FinishedState implements State{
    @Override
    public String actionState(Product product){
        String str = "Parabéns por ter comprado, não esqueça de avaliar-nos!!!";
        return str;
    }
}