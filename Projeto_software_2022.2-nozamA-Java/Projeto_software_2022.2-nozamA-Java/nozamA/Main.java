import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        //Atributos de administração, input: entrada de dados, login: logagem no sistema.
        Scanner input = new Scanner(System.in);
        Login login = new Login();

        //"Banco de dados" ArrayLists para armazenar os conjuntos de dados do sistema.
        ArrayList<Profile> accounts = new ArrayList<Profile>(); // Contas simples.
        ArrayList<UserAdmin> admins = new ArrayList<UserAdmin>(); // Contas de administradores.
        ArrayList<Product> feed = new ArrayList<Product>(); // Conjunto de todos os produtos do sistema.

        //Atributos de controle.
        boolean active = true;
        int choise, index;

        //Admin default do sistema.
        UserAdmin novoAdmin = new UserAdmin(feed, accounts, "admin", "admin", "admin"); 
        admins.add(novoAdmin);

        while(active)
        {
            System.out.println("\nBem-vindo ao nozamA!");
            System.out.printf("1 - Login;\n2 - Cadastro;\n3 - Administrar;\n4 - Sair.\n=>");            
        
            choise = input.nextInt(); // Entrada de dados: inteiro.

            switch(choise)
            {
                //  case 1: verifica um possivel login e retorna um index do usuário no accounts.
                case 1: index = login.checkLogin(accounts);
                        if(index > -1) accounts.get(index).menuUser(feed, accounts);
                        break;
                //  case 2: Registra um novo usuário no sistema.
                case 2: login.setRegister(accounts); break;

                //   case3: verifica um possivel login de admin do sistema, retornnado se posição no admins.
                case 3: index = login.checkLoginAdmin(admins);
                        if(index > -1) admins.get(index).menuUserAdmin(admins, feed);
                        break;

                //   case 4: encerra o loopin e finaliza o sistema.
                case 4: active = false; break;

                //    default: caso o usuário insira um valor não mapiado no sistema.
                default: System.out.println("Insira uma opção válida.\n"); break;
            }
        }
        input.close(); //finaliza o controle de dados do usuário.
    }
}
