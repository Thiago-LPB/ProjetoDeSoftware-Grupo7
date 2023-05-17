package nozama.products;

import nozama.products.states.State;

public class CloneProduct{
    String product;
    public State state;

    public CloneProduct(Product o) {
        this.product = o.toString();
        this.state = o.getState();
    }    

    public void setState(State state){
        this.state = state;
    }
}
