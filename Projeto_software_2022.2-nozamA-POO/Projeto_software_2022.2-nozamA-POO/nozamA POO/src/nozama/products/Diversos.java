package nozama.products;

import nozama.Profile;
import java.util.Scanner;

public class Diversos extends Product{

    Scanner input = new Scanner(System.in);
    int peso;
    int volume;
    String perigo;

    public Diversos(Profile e){
        super(e);
        setCoisa();
        setPer();
    }

    private void setCoisa(){
        System.out.printf("Digite o peso do produto: ");
        peso = input.nextInt(); if(input.hasNextLine()) input.nextLine();

        System.out.printf("Digite o volume do produto: ");
        volume  = input.nextInt(); if(input.hasNextLine()) input.nextLine();
    }

    public void setPer(){
        System.out.printf("Digite o nível do perigo:\n"+
                          "1 - Baixo\n2 - Médio\n3 - Alto\n");
        int choise = input.nextInt();

        if(input.hasNextLine()) input.nextLine();

        switch(choise){
            case 1: perigo = "Baixo"; break;
            case 2: perigo = "Médio"; break;
            case 3: perigo = "Alto"; break;
        }

    }

    @Override
    public String getCategory() {
        return this.getClass().getName();
    }

    @Override
    public String toString() {
        String aux = "\nPeso da coisa: "+peso+";\n"+
                     "volume da coisa: "+volume+";\n"+
                     "Nível de perigo: "+perigo+"\n";
        return super.toString()+aux;
    }
    
}