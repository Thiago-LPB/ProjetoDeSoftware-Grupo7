package nozama.products.command;
import nozama.products.*;
import nozama.users.*;
public class BuyButton implements Command{
    private CommandReceiver buyCommand;    
    private Profile profile;
    private Product product;
    public BuyButton(CommandReceiver buyCommand, Profile profile, Product product){
        this.buyCommand = buyCommand;
        this.profile = profile;
        this.product = product;
    }
    public void execute(){
        buyCommand.buyButton(profile,product);
    }
}
