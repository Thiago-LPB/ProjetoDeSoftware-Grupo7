import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

class Profile {
    // input: entrada de dados do usuário.
    Scanner input = new Scanner(System.in);

    // messageBok: é tipo o E-mail do perfim
    ArrayList<Message> messageBox = new ArrayList<Message>();
    ArrayList<User> prefer = new ArrayList<User>(); // Working In Progress (WIP).

    public User user; //  Entidade do perfil, futuramente essa classe herdara a classe User e nao será mais necessario esse atributo.
    
    //Menu do usuário: método ondo o usuário consegue interagir diretamente com o sistema.
    public void menuUser(ArrayList<Product> product, ArrayList<Profile> accounts){

        //Atributos locais para ajudar na manipulação do menu
        boolean aux = true;
        int userChoise, userChoiseCompra, userChoiseCart;

        //Itens do menu
        String menuUser = "1 - Ver feed;\n"+
                          "2 - Postar produto;\n"+
                          "3 - Ver meu carrinho;\n"+
                          "4 - Ver meu produtos;\n"+
                          "5 - Ver mensagens;\n"+
                          "6 - Editar perfil;\n"+
                          "7 - Sair.\n=>";
        String menuCompra = "\nGostaria de adicionar o produto ao carrinho? \n[1] - Sim\n[2] - Não\n=>";
        String menuCart = "\nAções: \n[1] - Comprar\n[2] - Remover\n[3]Voltar\n=>";

        while(aux){ 
            System.out.printf(this.user.toString()+"\n\n"+menuUser);
            userChoise = input.nextInt();

            switch(userChoise){
 
                //case 1: Mostra o feed e permite ao usuário interagir com cada produto. 
                case 1: if(!product.isEmpty()){ 
                            Iterator<Product> itrProduct = product.iterator();
                            while(itrProduct.hasNext()){
                                Product e = itrProduct.next();
                                System.out.printf("\n"+e.toString()+menuCompra);
                                userChoiseCompra =  input.nextInt();

                                switch(userChoiseCompra){
                                    case 1: System.out.println(this.user.cpf);
                                            System.out.println(e.getName()); 
                                            System.out.println("Produto adicionado no carrinho!\n");
                                            this.user.setMyCart(e);
                                            break;
                                    case 2: break;
                                    }
                            }
                        } else System.out.printf("\nNão existem produto no feed.\n\n");
                    break;

                // case 2: Cria um novo produto e notifica ao usuários com categoria preferida referente a esse novo produto.
                case 2: Product novoProduto = new Product(this);
                        this.user.setMyProduct(novoProduto);
                      
                        product.add(novoProduto);

                        System.out.print("\nProduto adicionado com sucesso:\n"+novoProduto.toString()+"\n");
                        Iterator<Profile> itr = accounts.iterator();
                        while(itr.hasNext()){
                            Profile auxE = itr.next();
                            String e = auxE.user.getFavorito();

                            if(e.equalsIgnoreCase(novoProduto.getCategory())){
                                Message novaMensagem = new Message(this);
                                novaMensagem.sendMessageSend("Um novo produto da categoria: "+e+"\n"+novoProduto.toString()+"\nVeja no feed!\n");
                                auxE.messageBox.add(novaMensagem);
                            }
                        }
                        break;
                // case 3: Mostra os produto do carrinha, caso nao esteja vazio, permitindo interagir com cada produto para comprar.
                case 3: if(!this.user.getMycart().isEmpty()){
                            Iterator<Product> itrPro = this.user.getMycart().iterator();
                            while(itrPro.hasNext()){
                                Product i = itrPro.next();
                                System.out.println(i.toString()+menuCart);
                                userChoiseCart = input.nextInt();
                                switch(userChoiseCart){
                                    case 1: i.compra();
                                            i.setRate();

                                            Message novaMensagem = new Message(this);
                                            novaMensagem.sendMessageSend("Item comprado!!! por "+this.user.getUser()+"\n");
                                            i.owner.messageBox.add(novaMensagem);
                                            if(i.qtdProduto == 0){
                                                product.remove(i);
                                            }                                          
                                            this.user.favoritos.add(i.getCategory());
                                            this.user.favorito = this.user.getFavorito();
                                            itrPro.remove();
                                            break;
                                    case 2: itrPro.remove();
                                    case 3:break;
                                    }  
                            }
                        }else{
                            System.out.println("Carrinho Vazio!\n");
                        }
            
                break;

                //case 4: Mostar todos os produtos postados, podendo interagir diretamente com eles: podendo editar.
                case 4: if(this.user.getMyProduct().isEmpty()){ System.out.println("Sem produtos!\n");}
                else {
                    Iterator<Product> itrProd = this.user.getMyProduct().iterator(); 
                    while(itrProd.hasNext()){
                        Product i = itrProd.next();
                        System.out.println(i.toString()+"\nEditar produto?\n[1] Sim\n[2] Não\n=>");
                        userChoiseCart = input.nextInt();
                        if(userChoiseCart == 1){i.editProduct();}
                    } 
                    }
                    break;

                //case 5: Mostra todas as mensagem recebidas: podendo interagir diretamente com cada mensagem, podendo excluir.
                case 5:
                    if(this.messageBox.isEmpty()){
                        System.out.println("\nSem Mensagens!\n");
                    }else{
                        Iterator<Message> itrM = this.messageBox.iterator();
                        while(itrM.hasNext()){
                            Message i = itrM.next();
                            System.out.println(i.toString()+"\nO que quer fazer?\n[1]Responder\n[2]Deletar mensagem\n[3] - Voltar\n");
                            userChoiseCart = input.nextInt();
                            if(userChoiseCart == 1){
                                Message novaMensagem = new Message(this);
                                novaMensagem.setMessageSend();
                                i.origem.messageBox.add(novaMensagem);
                            }else if(userChoiseCart == 2){itrM.remove();}
                        }
                    }
                    break;
                // case 6: ditar os dados do usuário.
                case 6: this.user.editUser();
                break;
                //case 7: fecha as opção de menu de usuário.
                case 7: aux = false; break;
                default: break;
                }
            }
        }

        //copia o override do user para o profile(this);
        @Override
        public String toString() {
            return this.user.toString();
        }
}
