package nozama.products;

import java.util.ArrayList;
import java.util.Scanner;

import nozama.Profile;

public abstract class Product {  
    Scanner input = new Scanner(System.in);

    //Array para avaliações
    ArrayList<Integer> rate = new ArrayList<Integer>();

    //Usuário q postou
    public Profile owner;

    //Atributos básicos
    private String name,
                   description, 
                   category = "default";
    private float price;
    private int qtdProduto;


    //Método construtor para criar produtos, obrigando a inserir os dados relevantes.
    public Product(Profile e){
        this.setName();       System.out.println();
        this.setDescripton(); System.out.println();
        this.setPrice();      System.out.println();
        System.out.printf("\nQual a quantidade desse produto?\n=>");
        this.qtdProduto = input.nextInt();

        if(input.hasNextLine()) input.nextLine();

        this.owner = e;
       
        System.out.println(qtdProduto);
    }

    //Método para editar alguns atributos dessa classe, individualmente.
    public void editProduct(){
        int userChoise;
        boolean aux = true;
        String menuEdit = "\n\nQual característica gostaria de editar?\n"+
                          "1 - Nome do produto;\n"+
                          "2 - Descrição do produto;\n"+
                          "3 - Preço do produto;\n"+
                          "4 - Quantidade de produto;\n"+
                          "5 - Sair.\n=>";
        while(aux){            
            System.out.println(this.toString());
            System.out.println(menuEdit);
            userChoise = input.nextInt();

            if(input.hasNextLine()) input.nextLine();
            
            switch(userChoise){
                // case 1: edita o nome do produto.
                case 1:  System.out.printf("Digite um novo nome:\n=>");   
                         input.nextLine();
                         this.name = input.nextLine();
                         break;

                // case 2: edita a descrição do produto.
                case 2:  System.out.printf("Digite uma nova descrição:\n=>");   
                         input.nextLine();
                         this.description = input.nextLine(); 
                         break;  

                // case 3: edita o preço do produto.
                case 3:  System.out.printf("Digite um novo preço:\n=>");   
                          input.nextLine();
                          this.price = input.nextFloat();     
                          if(input.hasNextLine()) input.nextLine();
                          break;

                // case 4: edita a quanitdade de produto.
                case 4: System.out.printf("Digite uma nova quantidade:\n=>");
                        this.qtdProduto = input.nextInt();

                        if(input.hasNextLine())input.nextLine();

                        break;

                // case 5: encerra a edição do produto.
                case 5:   aux = false;     
                          break;
                default: System.out.println("Insira um valor válido.\n"); break;
            }
        }


    }

    //Override para substituir o método toString() da class object para um padrão mais coerente a essa classe
    @Override
    public String toString() { 
        return "\n\n\tDono do produto: "+this.owner.getUser()+";\n"+
               "Nome do produto: "+getName()+";\n"+
               "Descrição do produto: "+getDescription()+";\n"+
               "Categoria do produto: "+getCategory()+";\n"+
               "Preço do produto: "+getPrice()+";\n"+
               "Quantidade:"+this.qtdProduto+"\n"+
               "Avaliação: "+getRate();
    }

    //      getters and setters
    public void setName(){
     
        System.out.printf("Qual é o nome do produto?\n=>");
        this.name = input.nextLine();

        System.out.println(this.name.intern());
    }

    public String getName() {return this.name;}

    public void setDescripton(){
        System.out.printf("Escreva uma descrição do produto:\n=>");

        this.description = input.nextLine();
    }

    public String getDescription() {return this.description;}

    public void setPrice(){
        System.out.printf("Qual é o preço do produto:\n=>");

        this.price = input.nextFloat();

        if(input.hasNextLine()) input.nextLine();
    }

    public float getPrice() {return this.price;}

    // Insere uma nova avaliação no produto.
    public void setRate(){
        int aval;

        System.out.printf("Qual é sua avalação do produto:\n=>");
        aval = input.nextInt();
        System.out.println("Obrigado pela sua avaliação!\n");
        this.rate.add(aval);
    }

    // Calcula a média das avaliações.
    public String getRate(){
        int size = this.rate.size(), star = 0;

        for(Integer i : this.rate) star += i;

        if(size == 0) { return "Não há avaliações ainda.\n"; }
        star /= size;

        return ""+star;
    }

    // Reduz o número do produto(this) disponivel no feed
    public void compra(){
        this.qtdProduto--;
    }

    public int getQtd(){
        return this.qtdProduto;
    }

    public String getCategory(){
        return this.category;
    }
}