package nozama.products;

import nozama.Profile;
import java.util.Scanner;


public class Casa extends Product {

    Scanner input = new Scanner(System.in);

    int largura, comprimento;
    boolean construido, loc;

    public Casa(Profile e){
        super(e);
        setTam();
        this.setBool();
    }

    private void setTam(){
        System.out.printf("\nQual a largura do terreno: ");
        largura = input.nextInt();

        if(input.hasNextLine()) input.nextLine();

        System.out.printf("\nQual o comprimento do terreno: ");
        comprimento = input.nextInt();

        if(input.hasNextLine()) input.nextLine();
    }

    private void setBool(){
        System.out.printf("Há área construida no terreno? [SIM] OU [NAO]\n=>");
        String ans = input.nextLine();

        if(ans.equals("SIM")) construido = true;

        System.out.printf("Terreno bem localizado? [SIM] OU [NAO]\n=>");
        ans = input.nextLine();

        if(input.hasNextLine()) input.nextLine();

        if(ans.equals("SIM")) loc = true;
    }

    @Override
    public String getCategory() {
        return this.getClass().getName();
    }

    @Override
    public String toString() {
        String aux = "\nLargura: "+largura+";\n"+
                     "Comprimento: "+comprimento+";\n"+
                     "Área construida: "+construido+";\n"+
                     "Boa localização: "+loc+".\n";
        return super.toString()+aux;
    }
    
}