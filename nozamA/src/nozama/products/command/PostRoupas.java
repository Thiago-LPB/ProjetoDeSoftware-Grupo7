package nozama.products.command;
import nozama.products.Roupas;
public class PostRoupas implements Command {
    private CommandReceiver postCommand;    
    private Roupas roupasObj;
    public PostRoupas(CommandReceiver buyCommand, Roupas roupasObj){
        this.postCommand = buyCommand;
        this.roupasObj = roupasObj;
    }
    public void execute(){
        postCommand.roupas(roupasObj);
    }
}
