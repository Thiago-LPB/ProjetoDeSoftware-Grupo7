package nozama.products;

import nozama.Profile;
import java.util.Scanner;

public class Roupas  extends Product{

    Scanner input = new Scanner(System.in);

    String size;
    String color;
    int choise;
    
    String menuCor =  "\n1 - Branco\n2 - Preto\n3 - Colorido\n=>";
    String menuSize = "\n1 - Small\n2 - Medium\n3- Large\n=>";

    public Roupas(Profile e){
        super(e);
        setDate();;
    }

    public void setDate(){
        System.out.printf("\nQual o tamanho do produto:"+menuSize);
        choise = input.nextInt();

        if(input.hasNextLine()) input.nextLine();

        switch(choise){
            case 1: size = "Small"; break;
            case 2: size = "Medium"; break;
            case 3: size = "Large"; break;
        }

        System.out.println("\nQual a cor da roupa?"+menuCor);
        choise = input.nextInt();

        if(input.hasNextLine()) input.nextLine();

        switch(choise){
            case 1: color = "Branco";   break;
            case 2: color = "Preto";    break;
            case 3: color = "Colorido"; break;
        }
    }

    @Override
    public String getCategory() {
        return this.getClass().getName();
    }

    @Override
    public String toString() {
        String aux = "\nTamanho da roupa: "+size+";\n"+
                     "Cor da roupa: "+color+"\n";
        return super.toString() + aux;
    }
    
}