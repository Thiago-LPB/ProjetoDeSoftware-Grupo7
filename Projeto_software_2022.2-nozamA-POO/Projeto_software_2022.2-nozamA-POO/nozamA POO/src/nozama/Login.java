package nozama;

import java.util.Iterator;
import java.util.Scanner;

/*      Classe dedicada a administração de login(usuário e admin) */
class Login {

    Bd bancoDados = Bd.getInstance();
    Scanner input = new Scanner(System.in);
    int i;

    //Checklogin de usuário comum retornanod sua posição no array.
    public int checkLogin(){

        //  Analisa se existe conta de usuário simples registrada
        if(bancoDados.getUsers().isEmpty()){
            System.out.printf("\n\tNão existem contas registradas ainda. Gostaria de registar?\n1 - SIM\n2 - NÃO\n=>");
            int choises = input.nextInt();

            if(choises != 1) {return -1;}
            else{
                this.setRegister(); // Registra um novo usuário.
                return -1;
            }
        }

        String email, password;

        System.out.printf("\n\tDigite seu E-mail:\n=>");
        email = input.nextLine(); System.out.println();

        System.out.printf("\n\tDigite sua senha:\n=>");
        password = input.nextLine(); System.out.println();

        Iterator<Profile> itr = bancoDados.getUsers().iterator();

        while(itr.hasNext()){
            Profile e = itr.next();

            if(e.getEmail().intern()    == email.intern() &&
               e.getPassword().intern() == password.intern()) return i;
        }
        System.out.println("\n\t\tE-mail ou senha incorretos. Tente novamente.\n");
        return -1;
    } 

    //Checklogin de usuário admin retorna sua posição no array.
    public int checkLoginAdmin(){

        String email, password;

        System.out.printf("\n\tDigite seu E-mail:\n=>");
        email = input.nextLine(); System.out.println();

        System.out.printf("\n\tDigite sua senha:\n=>");
        password = input.nextLine(); System.out.println();

        Iterator<UserAdmin> itr = bancoDados.getUserAdmin().iterator();

        while(itr.hasNext()){
            UserAdmin e = itr.next();

            if(e.getEmail().intern() == email.intern() && 
               e.getPassword().intern() == password.intern()) return i;
            i++;   
        }
        System.out.println("\nt\t\tE-mail ou senha incorretos. Tente novamente.\n");
        return -1;
    } 
    

    //Inicia um novo registro e adciona ao sistema
    public void setRegister(){
        Profile novoPerfil = new Profile();
        novoPerfil.setEmail();

        Iterator<Profile> itr = bancoDados.getUsers().iterator();

        while(itr.hasNext()){
            Profile e = itr.next();

            if(e.getEmail().equals(novoPerfil.getEmail())){
                System.out.println("\n\tE-mail já cadastrado. Tente novamente mais tarde.\n");
                return;
            }
        }

        novoPerfil.setUsers();
        System.out.println(novoPerfil.toString());
        bancoDados.getUsers().add(novoPerfil);
    }
}