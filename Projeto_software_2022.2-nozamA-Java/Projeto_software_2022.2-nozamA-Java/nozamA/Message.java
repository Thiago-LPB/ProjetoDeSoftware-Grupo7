import java.util.Scanner;

class Message{
    // input: entrada de daods do usuário.
    Scanner input = new Scanner(System.in);

    //Atributos básicos da classe.
    private String message;
    Profile origem;
    String admin;

    // Construtor com atribuição de quem mandou a mensagem.
    public Message(Profile user) {
        this.origem = user;
        this.admin = this.origem.user.getUser();
    }

    // Construtor com assinatura para receber a mensagem já pronta.
    public Message(String str) {this.admin = str+"\n";}

    // Método para registar a mensagem do usuário.
    public void setMessageSend(){
        System.out.printf("Qual a mensagem deseja enviar:\n=>");
    
        this.message = input.nextLine();
    }

    // Método com assinatura paar registrar uma mensagem já pronta do usuário.
    public void sendMessageSend(String message){
        this.message = message;
    }

    //retorna a mesagem q estar registrada no objeto.
    public String getMessage() {return this.message;}


    // Override do método toString() para um retorno mais addequado.
    @Override
    public String toString() {
        return "Usuário: "+admin+"\n"+
               "Mensagem: "+getMessage()+"\n";
    }
}
