package nozama.products.command;
import nozama.products.Comida;
public class PostComida implements Command{
    private CommandReceiver postCommand;    
    private Comida comidaObj;
    public PostComida(CommandReceiver buyCommand, Comida comidaObj){
        this.postCommand = buyCommand;
        this.comidaObj = comidaObj;
    }
    public void execute(){
        postCommand.comida(comidaObj);
    }
}
