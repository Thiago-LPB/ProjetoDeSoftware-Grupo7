package nozama.products;

import nozama.Profile;
import java.util.Scanner;

public class Eletrics extends Product{

    Scanner input = new Scanner(System.in);

    int resolucao;
    int capacidade;
    int choise;
    private String category = "Eletrônicos";
    String menuReso = "\n1 - 3 polegadas\n2 - 5 polegadas\n3 - 7 polegadas\n=>";
    String menuCapa = "\n1 - 32 gigas\n2 - 64gigas\n3 - 128 gigas\n=>";

    public Eletrics(Profile e){
        super(e);

        setCapa();
        setReso();
    }

    public void setReso(){
        System.out.printf("\nQual a Resolução do produto:"+menuReso);
        choise = input.nextInt();

        if(input.hasNextLine()) input.nextLine();

        switch(choise){
            case 1: resolucao = 3; break;
            case 2: resolucao = 5; break;
            case 3: resolucao = 7; break;
        }

    }

    public void setCapa(){
        System.out.printf("\n\nQual a Capacidade do produto:"+menuCapa);
        choise = input.nextInt();

        if(input.hasNextLine()) input.nextLine();

        switch(choise){
            case 1: capacidade = 32; break;
            case 2: capacidade = 64; break;
            case 3: capacidade = 128; break;
        }

    }

    public int valoriza(){
        switch(capacidade){
            case 32:  return 50;
            case 64:  return 25;
            case 128: return 10;
        }
        return 0;
    }

    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public String toString() {
        String aux = "\nResolução do produto: "+resolucao+"polegadas;\n"+
                     "Capacidade do produto: "+capacidade+"gigas;\n"+
                     "Desvalorização do produto: "+valoriza()+"%%\n";
        return super.toString()+aux;
    }
    
}