package nozama.products.command;
import nozama.products.Eletrics;
public class PostEletrics implements Command{
    private CommandReceiver postCommand;    
    private Eletrics eletricsObj;
    public PostEletrics(CommandReceiver buyCommand, Eletrics eletricsObj){
        this.postCommand = buyCommand;
        this.eletricsObj = eletricsObj;
    }
    public void execute(){
        postCommand.eletrics(eletricsObj);
    }
}
