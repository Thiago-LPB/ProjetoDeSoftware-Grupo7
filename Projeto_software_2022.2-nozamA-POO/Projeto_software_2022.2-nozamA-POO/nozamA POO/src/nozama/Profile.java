package nozama;

import nozama.payment.BuyBoleto;
import nozama.payment.BuyPix;
import nozama.products.*;

import java.beans.beancontext.BeanContextServiceRevokedEvent;
import java.util.Iterator;
import java.util.Scanner;

public class Profile extends User{

    Bd bancoDados = Bd.getInstance();
    Scanner input = new Scanner(System.in);

    public Profile(){
        super();
    }

    Iterator<Product> itrProducts;
    Iterator<Profile> itrProfiles;
    Iterator<Message> itrMessages;

    public void menuProfile(){
        boolean aux = true;
        int userChoise, choiseBuy, choiseProduct, choiseMessage, choiseCart;

        String menuUser = "\n1 - Ver feed;\n"+
                          "2 - Postar produto;\n"+
                          "3 - Ver meu carrinho;\n"+
                          "4 - Ver meu produtos;\n"+
                          "5 - Ver mensagens;\n"+
                          "6 - Editar perfil;\n"+
                          "7 - Sair.\n=>";
        
        String menuBuy = "\nGostaria de adicionar o produto ao seu carrinho?\n"+
                         "[1] - Sim\t[2] - Não\n=>";

        String menuCart = "\nAçoes:\n"+
                          "[1] - Comprar;\n"+
                          "[2] - Remover;\n"+
                          "[3] - Continuar.\n=>";

        String menuProduct = "\nAçoes:\n"+
                             "[1] - Editar;\n"+
                             "[2] - Remover;\n"+
                             "[3] - Continuar.\n=>";

        String menuMessage = "\nAçoes:\n"+
                             "[1] - Responder;\n"+
                             "[2] - Remover;\n"+
                             "[3] - Continuar.\n=>";
        String payment = "Qual a forma de pagamento:\n"+
                         "1 - Boleto;\n"+
                         "2 - Pix;\n=>";

            while(aux){
            System.out.printf(super.toString()+menuUser);
            userChoise = input.nextInt();

            if(input.hasNextLine()) input.nextLine();

            switch(userChoise){

                case 1: if(!bancoDados.getProducts().isEmpty()){
                            itrProducts = bancoDados.getProducts().iterator();
                            while(itrProducts.hasNext()){
                                Product e = itrProducts.next();

                                System.out.printf(e.toString()+menuBuy);
                                choiseBuy = input.nextInt();

                                if(input.hasNextLine()) input.nextLine();

                                if(choiseBuy == 1){
                                    this.getMycart().add(e);
                                    System.out.printf("\n\tProducto adicionado com sucesso!!!\n");
                                } else System.out.printf("\n\tOk! continuaremos...\n");
                            }
                        }System.out.printf("\n\tNão existe nenhum produto no feed ainda.\n");
                        break;

                case 2: Product e = null;
                         System.out.printf("\n\tQual a categoria do seu produto?\n"+
                                        "1 - Casa;\n"+
                                        "2 - Comida;\n"+
                                        "3 - Eletronicos\n"+
                                        "4 - Roupas\n"+
                                        "5 - diversosn\n");
                        int choise = input.nextInt();

                        if(input.hasNextLine()) input.nextLine();

                        switch(choise){
                            case 1: e = new Casa(this);     break;
                            case 2: e = new Comida(this);   break;
                            case 3: e = new Eletrics(this); break;
                            case 4: e = new Roupas(this);   break;
                            case 5: e = new Diversos(this); break;
                            default: System.out.println("\n\tValor inválido, o produto será declarado como 'Diverso'"); e = new Diversos(this); break;
                        }

                        bancoDados.getProducts().add(e);
                        this.getMyProduct().add(e);

                        Message messageProduct = new Message(this);
                                messageProduct.setMessageProduct(this.getUser(), e.toString());

                        itrProfiles = bancoDados.getUsers().iterator();

                        while(itrProfiles.hasNext()){
                            Profile p = itrProfiles.next();

                            if(p.getFavorito().equals(e.getCategory().toString())) p.setMessageBox(messageProduct);
                        }
                        break;

                case 3: if(!this.getMycart().isEmpty()){
                            itrProducts = this.getMycart().iterator();

                            while(itrProducts.hasNext()){
                                Product p = itrProducts.next();

                                System.out.printf(p.toString()+menuCart);
                                choiseCart = input.nextInt(); 

                                if(input.hasNextLine()) input.nextLine();

                                if(choiseCart == 1){
                                    System.out.printf("\n\n\tProcessando...\n\n"+payment);
                                    choiseCart = input.nextInt();

                                    if(input.hasNextLine()) input.nextLine();

                                    if(choiseCart == 1){
                                        BuyBoleto pagamento = new BuyBoleto(this, p.owner, p);
                                        Message messageCart = new Message(this);
                                        messageCart.setBuyProduct(this.getUser(), p.getName(), pagamento);
                                        p.owner.setMessageBox(messageCart);                                
                                    }else{
                                        BuyPix pagamento = new BuyPix(this, p.owner, p);
                                        Message messageCart = new Message(this);
                                        messageCart.setBuyProduct(this.getUser(), p.getName(), pagamento);
                                        p.owner.setMessageBox(messageCart);
                                        System.out.print(pagamento.gerarQrCode());
                                    }
                                    p.compra();

                                    if(p.getQtd() == 0){
                                        bancoDados.getProducts().remove(p);
                                        itrProducts.remove();
                                    }
                                } else{
                                    itrProducts.remove();
                                    System.out.printf("\n\tItem removido com sucesso!.\n");
                                }
                                
                                System.out.printf("\n\t\tCONTINUANDO!\n");
                            }
                        }else System.out.printf("\n\tSeu carrinho estar vazio!\n");
                        break;

                case 4: if(!this.getMyProduct().isEmpty()){
                            itrProducts = this.getMyProduct().iterator();

                            while(itrProducts.hasNext()){
                                Product p = itrProducts.next();

                                System.out.printf(p.toString()+menuProduct);
                                choiseProduct = input.nextInt(); 

                                if(input.hasNextLine()) input.nextLine();

                                if(choiseProduct == 1){
                                    p.editProduct();

                                    if(p.getQtd() == 0) bancoDados.getProducts().remove(p);
                                    else{
                                        bancoDados.getProducts().remove(p);
                                        bancoDados.getProducts().add(p);
                                    }
                                }
                                else{
                                    bancoDados.getProducts().remove(p);
                                    itrProducts.remove();

                                    System.out.printf("\n\tItem comprado com sucesso!\n");
                                }

                                System.out.printf("\n\t\tCONTINUANDO!\n");
                            }
                        }else System.out.printf("\n\tSeu carrinho estar vazio!\n");
                        break;
                    
                case 5: if(!this.getMessageBox().isEmpty()){
                            itrMessages = this.getMessageBox().iterator();

                            while(itrMessages.hasNext()){
                                Message messageBox = itrMessages.next();

                                System.out.printf(messageBox.toString()+menuMessage);
                                choiseMessage = input.nextInt(); 

                                if(input.hasNextLine()) input.nextLine();

                                if(choiseMessage == 1){
                                    Message res = new Message(this);
                                    res.setMessage(this.getUser());

                                    if(!messageBox.aux.getCpf().equals(this.getCpf())){
                                        messageBox.aux.getMessageBox().add(res);
                                        System.out.printf("\n\tResposta enviada com sucesso!\n");
                                    } 
                                    else System.out.printf("\n\n\tAuto resposta detectada.\n\n");
                                }else{
                                    itrMessages.remove();
                                    System.out.printf("\n\tMensagem removida com sucesso!\n");
                                }

                                System.out.printf("\n\tCONTINUANDO!!\n");
                            }

                        }else System.out.printf("\n\tVocê não tem mensagem em seus dados.\n");
                        break;

                case 6: this.editUser();  break;
                case 7: aux = false; break;
                default: System.out.printf("\n\tOpção inválida.\n");

            }
        }

    }
} 