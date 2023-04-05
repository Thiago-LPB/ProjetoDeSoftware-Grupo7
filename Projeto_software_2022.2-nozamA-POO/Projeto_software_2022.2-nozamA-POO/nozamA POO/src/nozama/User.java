package nozama;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import nozama.products.Product;

class User {

    //input: entrada de dadps do usuário.
    Scanner input = new Scanner(System.in);

    //Arrays dos produtos que eu compro e vendo.
    private ArrayList<Product> myProduct  = new ArrayList<Product>();
    private ArrayList<Product> myCart     = new ArrayList<Product>();
    private ArrayList<String>  favoritos  = new ArrayList<String>();
    private ArrayList<Message> messageBox = new ArrayList<Message>();

    //Atributos básicos
    public String  user     = "[edit_user]",
                   age      = "[edit_age]",
                   gender   = "[edit_gender]",
                   cpf      = "[edit_cpf]",
                   adress   = "[edit_adress]",
                   email    = "[edit_email]",
                   password = "[edit_password]";
    boolean aux = false;  
    float bank = 0f;
  
    //Método construtor para criar usuários, inserindo os dados iniciais.
    public void setUsers(){
        this.setUser(); System.out.println();
        this.setCpf(); System.out.println();
        this.setAdress(); System.out.println();
        this.setGender(); System.out.println();
        this.setAge(); System.out.println();
        this.setPassword(); System.out.println();
    }

    //Método para editar alguns atributos dessa classe.
    public void editUser(){

        //Atributos locais para o controle de edição.
        int userChoise;   
        boolean aux = true; 
        String userAux;

        String menuEdit = "\n\nQual característica gostaria de editar?\n"+
                          "1 - Nome do usuário;\n"+
                          "2 - Troca de endereço;\n"+
                          "3 - Troca de E-mail;\n"+
                          "4 - Troca de senha;\n"+
                          "5 - Sair.\n=>";
        while(aux){
            this.toString();
            System.out.printf(menuEdit);
            userChoise = input.nextInt();

            if(input.hasNextLine()) input.nextLine();
            

            switch(userChoise){
                // case 1: edita o nome de usuário.
                case 1: setUser();   break;

                // case 2: edita o endereço.
                case 2: setAdress(); break;

                // case 3: edita o email.
                case 3: setEmail();  break;

                // case 4: edita a senha, inserindo a senha anterior.
                case 4: System.out.printf("\n\nInsira sua senha atual:\n=>");
                        userAux = input.nextLine();

                        if(userAux.equals(getPassword())) setPassword();
                        else System.out.println("S\nenha incorreta. Não foi possível efetuar a troca de senha.\n");
                        break;
                // case 5: finaliza a edição.
                case 5: aux = false; break;
                default: System.out.println("\nInsira um valor válido.\n"); break;

            }

        }
    }

    //Override para substituir o método toString() da class object para um padrão mais coerente a essa classe
    @Override
    public String toString() {
        return "\n\nUser: "+this.user+"\n"+
               "Categoria preferida: "+getFavorito()+"\n"+
               "Age: "+this.age+"\n"+
               "Gender: "+this.gender+"\n"+
               "CPF: "+this.cpf+"\n"+
               "Adress: "+this.adress+"\n"+
               "E-mail: "+this.email+"\n";
    }

    //      getters and setters
    public void setUser()
    {
        System.out.printf("\nDigite seu nome:\n=>");
        this.user = input.nextLine();
    }

    public String getUser() {return this.user;}

    public void setAge()
    {
        System.out.printf("\nDigite sua idade:\n=>");
        this.age = input.nextLine();
    }

    public String getAge() {return this.age;}

    public void setGender()
    {
        System.out.printf("\nDigite seu gênero:\n=>");
        this.gender = input.nextLine();
    }

    public String getGender() {return this.gender;}

    public void setCpf()
    {
        System.out.printf("\nDigite seu CPF:\n=>");
        this.cpf = input.nextLine();
    }

    public String getCpf() {return this.cpf;}

    public void setAdress()
    {
        System.out.printf("\nDigite seu endereco:\n=>");
        this.adress = input.nextLine();
    }

    public String getAdress() {return this.adress;}

    public void setEmail()
    {
        System.out.printf("\nDigite seu e-mail:\n=>");
        this.email = input.nextLine();
    }

    public String getEmail() {return this.email;}

    public void setPassword()
    {
        System.out.printf("\nDigite sua senha:\n=>");
        password = input.nextLine();
    }

    public String getPassword() {return this.password;}

    public void setMyCart(Product product) {
        this.myCart.add(product);
    }

    public void setValorBank(float plus){
        bank += plus;
    }

    public float getValorBank(){return this.bank;}

    //Calcula a moda das categorias dos intem comprados por (this) 
    public String getFavorito(){
        int casa = 0, comida = 0, diversos = 0, eletronicos = 0, roupas = 0;
        Iterator<String> itr = favoritos.iterator();

        while(itr.hasNext()){
            String e = itr.next();

            if(e.equals("Casa")) casa++;
            if(e.equals("Comida")) comida++;
            if(e.equals("Diversos")) diversos++;
            if(e.equals("eletronicos")) eletronicos++;
            if(e.equals("Roupas")) roupas++;
        }

        if(casa >= comida && casa >= diversos && casa >= eletronicos && casa >= eletronicos && casa >= roupas){
            return "Casa";
        }else if(comida >= casa && comida >= diversos && comida >= eletronicos && comida >= eletronicos && comida >= roupas){
            return"Comida";
        }else if(diversos >= casa && diversos >= comida && diversos >= eletronicos && diversos >= eletronicos && diversos >= roupas){
            return "Diversos";
        }else if(eletronicos >= casa && eletronicos >= diversos && eletronicos >= comida && eletronicos >= eletronicos && eletronicos >= roupas){
            return "Eletrônicos";
        }else if(roupas >= casa && roupas >= diversos && roupas >= eletronicos && roupas >= eletronicos && roupas >= comida){
            return "Roupas";
        }

        return "Defaut";
    }

    public ArrayList<Product> getMycart(){return this.myCart;}

    public void setMyProduct(Product product) {this.myProduct.add(product);}
    
    public ArrayList<Product> getMyProduct() {return this.myProduct;}

    public void setMessageBox(Message e) {this.messageBox.add(e);}

    public ArrayList<Message> getMessageBox() {return this.messageBox;}
}