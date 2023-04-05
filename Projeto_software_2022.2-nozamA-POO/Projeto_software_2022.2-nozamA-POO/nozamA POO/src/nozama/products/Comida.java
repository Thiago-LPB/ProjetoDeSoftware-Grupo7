package nozama.products;

import nozama.Profile;
import java.util.Scanner;

public class Comida  extends Product{

    Scanner input = new Scanner(System.in);

    boolean lactose, gluten, lata;
    int qdt, validade;

    public Comida(Profile e){
        super(e);

        setBools();
    }

    public void setBools(){
        System.out.printf("Quais do abaixo seu produto faz parte:\n"+
                          "1 - Lactose;\n"+
                          "2 - Gluten;\n"+
                          "3 - Lata;\n4 - Sair\n=>");

        int choise;
        boolean aux = true;

        while(aux){
            choise = input.nextInt(); if(input.hasNextLine()) input.nextLine();

            switch(choise){
                case 1: lactose = true; break;
                case 2: gluten  = true; break;
                case 3: lata    = true; break;
                case 4: aux = false; break;
                default: System.out.printf("\n\nOpção inválida!!\n\n");
            }
        }

    }

    public void setQdt(){
        System.out.printf("Quantas pessoas essa comida serve?\n=>");
        qdt = input.nextInt();

        if(input.hasNextLine()) input.nextLine();
    }


    public void setValidade(){
        System.out.printf("Quantos dias seu produto tem de valiadade?\n=>");
        validade = input.nextInt();

        if(input.hasNextLine()) input.nextLine();
    }


    @Override
    public String getCategory() {
        return this.getClass().getName();
    }

    @Override
    public String toString() {
        String aux = "\nContém Lactose: "+lactose+";\n"+
                     "Contém Gluten: "+gluten+";\n"+
                     "Enlatado: "+lata+";\n"+
                     "Quantidade de pessoas servidas: "+qdt+";\n"+
                     "Validade: "+validade+".\n";
        return super.toString()+aux;
    }
    
}