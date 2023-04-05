import java.util.ArrayList;
import java.util.Scanner;

/*      Classe dedicada a administração de login(usuário e admin) */
class Login {

    Scanner input = new Scanner(System.in);

    //Checklogin de usuário comum retornanod sua posição no array.
    public int checkLogin(ArrayList<Profile> accounts){

        //  Analisa se existe conta de usuário simples registrada
        if(accounts.isEmpty()){
            System.out.printf("Não existem contas registradas ainda. Gostaria de registar?\n1 - SIM\n2 - NÃO\n=>");
            int choises = input.nextInt();

            if(choises != 1) {return -1;}
            else{
                this.setRegister(accounts); // Registra um novo usuário.
                return -1;
            }
        }

        String email, password;

        System.out.printf("Digite seu E-mail:\n=>");
        email = input.nextLine(); System.out.println();

        System.out.printf("Digite sua senha:\n=>");
        password = input.nextLine(); System.out.println();

        for (int i = 0; i <accounts.size(); i++) {
            User user = accounts.get(i).user;
            if(user.getEmail().intern() == email.intern() && 
              user.getPassword().intern() == password.intern()) {return i;}
        }
        System.out.println("E-mail ou senha incorretos. Tente novamente.");
        return -1;
    } 

    //Checklogin de usuário admin retorna sua posição no array.
    public int checkLoginAdmin(ArrayList<UserAdmin> userAdmin){

        String email, password;

        System.out.printf("Digite seu E-mail:\n=>");
        email = input.nextLine(); System.out.println();

        System.out.printf("Digite sua senha:\n=>");
        password = input.nextLine(); System.out.println();

        for (int i = 0; i <userAdmin.size(); i++) {
            if(userAdmin.get(i).getEmail().intern() == email.intern() && 
              userAdmin.get(i).getPassword().intern() == password.intern()) {return i;}
        }
        System.out.println("E-mail ou senha incorretos. Tente novamente.");
        return -1;
    } 
    

    //Inicia um novo registro e adciona ao sistema
    public boolean setRegister(ArrayList<Profile> accounts){
        User novoUsuario = new User();

        for(int i=0; i<accounts.size(); i++){
            User a = accounts.get(i).user;

            if(a.getEmail().intern() == novoUsuario.getEmail().intern()) {  
                System.out.println("\nE-mail já cadastrado. Tente novamente mais tarde.\n");
                return false;
            }
        }

        novoUsuario.setPassword();
        System.out.println(novoUsuario.toString());
        Profile novoPerfil = new Profile();
        novoPerfil.user = novoUsuario;
        accounts.add(novoPerfil);

        return true;
    }
}
