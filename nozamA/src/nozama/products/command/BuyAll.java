package nozama.products.command;

import nozama.payment.*;
import nozama.products.*;

public class BuyAll implements Command {
    private CommandReceiver buyCommand;
    private Buy buy;
    private Product product;
    public BuyAll(CommandReceiver buyCommand, Buy buy, Product product){
        this.buyCommand = buyCommand;
        this.buy = buy;
        this.product = product;
    }

    public void execute(){
        buyCommand.buy(buy,product);
    }
}
