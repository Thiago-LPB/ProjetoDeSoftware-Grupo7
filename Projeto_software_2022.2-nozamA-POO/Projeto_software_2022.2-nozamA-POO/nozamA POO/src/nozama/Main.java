package nozama;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {


        //Atributos de administração, input: entrada de dados, login: logagem no sistema.
        Scanner input = new Scanner(System.in);
        Login login = new Login();

        // Iniciando o "banco de dados".
        Bd bancoDados = Bd.getInstance();

        //Atributos de controle.
        boolean active = true;
        int choise, index;

        //Admin default do sistema.
        bancoDados.getUserAdmin().add(new UserAdmin("admin", "123.456.789-10", "admin", "admin"));

        while(active)
        {
            System.out.println("\n\t\tBem-vindo ao nozamA!");
            System.out.printf("1 - Login;\n2 - Cadastro;\n3 - Administrar;\n4 - Sair.\n=>");            
        
            choise = input.nextInt(); // Entrada de dados: inteiro.

            if(input.hasNextLine()) input.nextLine(); // Limpa o buffer do caractyer (\n)

            switch(choise)
            {
                //  case 1: verifica um possivel login e retorna um index do usuário no accounts.
                case 1: index = login.checkLogin();
                        if(index > -1) bancoDados.getUsers().get(index).menuProfile();
                        break;
                //  case 2: Registra um novo usuário no sistema.
                case 2: login.setRegister(); break;

                //   case3: verifica um possivel login de admin do sistema, retornnado se posição no admins.
                case 3: index = login.checkLoginAdmin();
                        if(index > -1) bancoDados.getUserAdmin().get(index).menuUserAdmin();
                        break;

                //   case 4: encerra o looping e finaliza o sistema.
                case 4: active = false; break;

                //    default: caso o usuário insira um valor não mapiado no sistema.
                default: System.out.println("\n\tInsira uma opção válida.\n"); break;
            }
        }
        input.close(); //finaliza o controle de dados do usuário.
    }
}