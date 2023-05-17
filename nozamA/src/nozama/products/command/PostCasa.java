package nozama.products.command;

import nozama.products.Casa;

public class PostCasa implements Command{
    private CommandReceiver postCommand;    
    private Casa casaObj;
    public PostCasa(CommandReceiver buyCommand, Casa casaObj){
        this.postCommand = buyCommand;
        this.casaObj = casaObj;
    }
    public void execute(){
        postCommand.casa(casaObj);
    }
}
