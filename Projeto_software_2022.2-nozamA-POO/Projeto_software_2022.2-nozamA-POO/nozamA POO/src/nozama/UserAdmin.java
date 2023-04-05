package nozama;

import java.util.Iterator;
import java.util.Scanner;
import nozama.products.*;

class UserAdmin extends User{
    
    Bd bancoDados = Bd.getInstance();

    Scanner input = new Scanner(System.in);

    Iterator<Product> itrProducts;
    Iterator<Profile> itrProfiles;
    Iterator<Message> itrMessages;


    //Método construtor para inicia
    public UserAdmin(String user, String cpf, String email, String password){
        this.user = user;
        this.cpf = cpf;
        this.email = email;
        this.password = email;
    }

    public UserAdmin(){System.out.printf("\n");}
    
    public void setUserAdmin(){
        setUser();
        setCpf();
        setEmail();
        setPassword();
    }

    //Método para editar alguns atributos dessa classe 
    @Override
    public void editUser(){

        // Atributos locais para manipilação de edição 
        int userChoise;
        boolean aux = true;
        String userAux;
        String menuEdit = "\nQual caracteristiac gostaria de editar?\n"+
                          "1 - Nome de usuário;\n"+
                          "2 - Troca de e-mail;\n"+
                          "3 - Troca de senha;\n"+
                          "4 - Sair.\n=>";

        do{
            this.toString();
            System.out.println(menuEdit);
            userChoise = input.nextInt();

            if(input.hasNextLine()) input.nextLine();

            switch(userChoise){
                // case 1: edita o nome do usuário admin.
                case 1: setUser();  break;

                // case 2: edita o email do admin.
                case 2: setEmail(); break;

                //case 3: troca a senha, baseado na senha anterior.
                case 3: System.out.printf("\nInsira sua senha atual:\n=>");
                        userAux = input.nextLine();

                        if(userAux.equals(getPassword())) setPassword();
                        else System.out.println("\nSenha incorreta. Não foi possivel efetuar a troca de senha.\n");
                        break;
                
                // case 4: finaliza a edição de usuário admin
                case 4: aux = false; break;
                default: System.out.println("\nInsira um valor válido.\n"); break;
            }
        }while(aux);
    }
    
    //Método para administrar como um usuário administrador
    public void menuUserAdmin(){

        // Atributos locais
        boolean aux = true;
        int adminChoise;
        String menuUserAdmin = "\n1 - Ver Todos os usuários.\n"+
                               "2 - ver todos os produtos.\n"+
                               "3 - Editar perfil.\n"+
                               "4 - Adicionar novo ADMIN.\n"+
                               "5 - Dar bônus.\n"+
                               "6 - Sair.\n=>";
        String adminUserMsm = "\nO que gostaria de fazer?\n1 - Mandar aviso;\n2 - Deletar;\n3 - Continuar.\n=>";
        String op = "\nOpção inválida.\n";

        while(aux){
            System.out.printf("\n"+menuUserAdmin);
            adminChoise = input.nextInt(); 

            if(input.hasNextLine()) input.nextLine();

            switch(adminChoise){
                // case 1: interage com todos os usuários do sistema (podendo excluir cada um individualmente).
                case 1: if(!bancoDados.getUsers().isEmpty()){
                            itrProfiles = bancoDados.getUsers().iterator();

                            while(itrProfiles.hasNext()){
                                Profile p = itrProfiles.next();
                                System.out.println(p.toString()+"\n"+adminUserMsm);
                                int choise = input.nextInt();

                                if(input.hasNextLine()) input.nextLine();

                                switch(choise){
                                    case 1: Message message = new Message();
                                            message.setMessageAdmin(this.getUser());

                                            p.getMessageBox().add(message);
                                            break;
                                    case 2: itrProfiles.remove();
                                            for (Product pro : p.getMyProduct()) bancoDados.getProducts().remove(pro);  
                                            p.getMyProduct().clear();                                       
                                            p.getMycart().clear();
                                            p.getMessageBox().clear();
                                            break;
                                    case 3: break;
                                    default: System.out.print(op); break;
                                }
                            }
                        } else System.out.printf("\n\tNão exitem contas registradas ainda.\n");
                        break;

                // case 2: interage com todos os produtos, podedendo excluit individualmente.
                case 2: if(!bancoDados.getProducts().isEmpty()){
                            itrProducts = bancoDados.getProducts().iterator();
                            while(itrProducts.hasNext()){

                                Product auxItrProduct = itrProducts.next();
                                System.out.print("\n"+auxItrProduct.toString()+"\n"+adminUserMsm);
                                int choise = input.nextInt();

                                if(input.hasNextLine()) input.nextLine();

                                switch(choise){
                                    case 1: Message message = new Message();
                                            message.setMessageAdmin(this.getUser());

                                            auxItrProduct.owner.getMessageBox().add(message);
                                            break;
                                    case 2: itrProducts.remove();;
                                            auxItrProduct.owner.getMyProduct().remove(auxItrProduct);
                                            break;
                                    case 3: break;
                                    default: System.out.print(op);
                                }
                            }
                        }else System.out.printf("\n\tNão exitem produtos registrados registradas ainda.\n");
                        break;

                // case 3: edita dados do usuário administrador.
                case 3: this.editUser();
                        break;

                // case 4: cria a conta de um novo administrador.
                case 4: UserAdmin novoAdmin = new UserAdmin();
                        novoAdmin.setUserAdmin();
                        bancoDados.getUserAdmin().add(novoAdmin);
                        break;

                // case 5: Working In Progress.
                case 5: System.out.printf("\n\t\tWIP\n"); break;
                case 6: aux = false; break;
                default: System.out.print(op) ;      

            }
        }
    }
    


    //Override para substituir o método toString() da class object para um padrão mais coerente a essa classe
    @Override
    public String toString() {
        return "\nUser: "+getUser()+"\nCPF: "+getCpf()+"\nE-mail: "+getEmail()+"\n";
    }
}