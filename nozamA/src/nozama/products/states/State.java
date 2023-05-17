package nozama.products.states;

import nozama.products.Product;

public interface State{
    public abstract String actionState(Product product);
}