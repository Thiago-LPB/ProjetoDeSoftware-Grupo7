package nozama.users;

import nozama.app.*;
import nozama.payment.*;
import nozama.products.*;
import nozama.products.states.*;
import nozama.products.command.*;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Profile extends User{

    public ArrayList<CloneProduct> compras = new ArrayList<CloneProduct>();

    Bd bancoDados = Bd.getInstance();
    boolean comp = false;
    Iterator<Product> itrProducts;
    Iterator<Profile> itrProfiles;
    Iterator<Message> itrMessages;
    JDialog frame = this;

    public Profile(){
        super();
    }

    private void clearJDialog() {
        try {
            this.getContentPane().removeAll();
            this.revalidate();
            this.repaint();
            this.setModal(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Err clearJDialog(),  Exception:\n"+ex.getCause(), "Exception", 0);
        }
    }
    
    @Override
    public void menu() {
        try {
            bancoDados.mainWindow.setVisible(false);
            clearJDialog();
            this.setTitle("Profile");
            this.setLayout(new BorderLayout());
            this.setResizable(false);
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.setModal(true);
    
            JPanel buttons = new JPanel(new GridLayout(8, 1));
    
            bancoDados.loginWindow.setVisible(false);
            JButton feed = new JButton("       Feed       ");
            JButton post = new JButton("Criar Produto");
            JButton cart = new JButton("    Carrinho    ");
            JButton buy     = new JButton("    Compras    ");
            JButton product = new JButton("    Produtos    ");
            JButton message = new JButton("  Messagem  ");
            JButton edit = new JButton("       Perfil       ");
            JButton exit = new JButton("         Sair        ");
    
            JPanel feedPanel = new JPanel();
            JPanel postPanel = new JPanel();
            JPanel cartPanel = new JPanel();
            JPanel buyPanel     = new JPanel();
            JPanel productPanel = new JPanel();
            JPanel messagePanel = new JPanel();
            JPanel editPanel = new JPanel();
            JPanel exitPanel = new JPanel();
    
            configMenu(buttons, feedPanel, feed);
            configMenu(buttons, postPanel, post);
            configMenu(buttons, cartPanel, cart);
            configMenu(buttons, buyPanel, buy);
            configMenu(buttons, productPanel, product);
            configMenu(buttons, messagePanel, message);
            configMenu(buttons, editPanel, edit);
            configMenu(buttons, exitPanel, exit);
            exit.setBackground(new Color(200,80,100));
    
            JTextArea dados = new JTextArea(this.toString());
            dados.setAutoscrolls(true);
            dados.setBackground(Color.lightGray);
            dados.setEditable(false);
    
            this.add(dados, BorderLayout.EAST);
            this.add(buttons, BorderLayout.WEST);
            this.pack();
            this.repaint();
    
            feed.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (bancoDados.getProducts().isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "Não há produto no Feed!");
                        } else {
                            setVisible(false);
                            showFeed();
                            setVisible(true);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            post.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    creatProduct();
                    setVisible(true);
                }
            });

            cart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (bancoDados.getProducts().isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "Não há produto no Carrinhoed!");
                        } else {
                            setVisible(false);
                            showCart();
                            setVisible(true);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            buy.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (bancoDados.getProducts().isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "Não há produtos comprados.");
                        } else {
                            setVisible(false);
                            showCompras();
                            setVisible(true);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            product.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (bancoDados.getProducts().isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "Não há produtos criados por você.");
                        } else {
                            setVisible(false);
                            showMyProduct();
                            setVisible(true);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            message.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(getMessageBox().isEmpty()){
                        JOptionPane.showMessageDialog(frame, "Voce ainda não tem mensagens postados!");
                    }else{
                        itrMessages = getMessageBox().iterator();
    
                        while(itrMessages.hasNext()){
                            Message msm = itrMessages.next();
    
                            switch(msm.type){
                                case 0: respondMessage(msm); break;
                                case 1: JOptionPane.showMessageDialog(frame, msm.toString(), "New Product", 0);   break;
                                case 2: JOptionPane.showMessageDialog(frame, msm.toString(), "Admin message", 0); break;
                                case 3: JOptionPane.showMessageDialog(frame, msm.toString(), "Message", 0);       break;
                            }  
                        }
                    }
                }
            });

            edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    editUser();
                    setVisible(true);   
                } 
            }); 

            exit.addActionListener(e -> {
                this.setVisible(false);
                bancoDados.mainWindow.setVisible(true);
                dispose();
            });

            this.setVisible(true);

        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getCause(), "Exception", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configMenu(JPanel buttons, JPanel panel, JButton button){
        panel.setBackground(new Color(51, 60, 76));
        button.setCursor(new Cursor(12));
        button.setBackground(new Color(225, 127, 17));

        panel.add(button);
        buttons.add(panel);
    }

    Product creatProd;
    private void creatProduct() {
        JDialog frame = new JDialog(this, "Criando");
        frame.setLayout(new GridLayout(5, 1));
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(51, 60, 76));

        JButton casa = new JButton("    Casa    ");
        JButton comida = new JButton("  Comida  ");
        JButton eletric = new JButton("Eletronico");
        JButton roupa = new JButton("   Roupa   ");
        JButton cancelar = new JButton(" Cancelar ");

        JPanel casaPanel = new JPanel();
        JPanel comidaPanel = new JPanel();
        JPanel eletricPanel = new JPanel();
        JPanel roupaPanel = new JPanel();
        JPanel cancelarPanel = new JPanel();

        configMenu2(casaPanel, casa, frame);
        configMenu2(comidaPanel, comida, frame);
        configMenu2(eletricPanel, eletric, frame);
        configMenu2(roupaPanel, roupa, frame);
        configMenu2(cancelarPanel, cancelar, frame);
        cancelar.setBackground(new Color(200, 100, 90));

        frame.pack();

        casa.addActionListener(e -> {
            try {
                creatProd = new Casa(this);
                setMyProduct(creatProd);
                if(creatProd.getQtd() > 0) postProduct(creatProd);
                this.aux = true;
            } catch (NullPointerException b) {
                this.getMyProduct().add(creatProd);
            }
            
        });

        comida.addActionListener(e -> {
            try {
                creatProd = new Comida(this);
                getMyProduct().add(creatProd);
                if(creatProd.getQtd() > 0) postProduct(creatProd);
                this.aux = true;
            } catch (NullPointerException b) {
                this.getMyProduct().add(creatProd);
            }
        });

        eletric.addActionListener(e -> {
            try {
                creatProd = new Eletrics(this);
                setMyProduct(creatProd);
                if(creatProd.getQtd() > 0) postProduct(creatProd);
                this.aux = true;
            } catch (NullPointerException b) {
                this.getMyProduct().add(creatProd);
            }
        });

        roupa.addActionListener(e -> {
            try {
                creatProd = new Roupas(this);
                setMyProduct(creatProd);
                if(creatProd.getQtd() > 0) postProduct(creatProd);
                this.aux = true;
            } catch (NullPointerException b) {
                this.getMyProduct().add(creatProd);
            }
        });

        cancelar.addActionListener(e -> {
            try {
                frame.dispose();
            }catch(IllegalStateException ex){
                ex.printStackTrace();
            }
        });
        frame.setVisible(true);
    }

    private void configMenu2(JPanel panel, JButton button, JDialog frame){
        button.setBackground(new Color(0, 200, 200));
        button.setCursor(new Cursor(12));
        panel.setBackground(new Color(51, 60, 76));

        panel.add(button);
        frame.add(panel);
    }

    private void notifyUsers(Product product){
        for (int i = 0; i < bancoDados.getUsers().size(); i++) {
            User perfil = bancoDados.getUsers().get(i);


            if (perfil.getFavorito().equals(product.getCategory())) {
                Message productMessage = new Message(this);
                productMessage.setMessage(this.getName(), product.getName(), 1);
                perfil.getMessageBox().add(productMessage);
            }
        }
    }    
    
    private void postProduct(Product product){
        product.setState(new AvailableState());
        bancoDados.getProducts().add(product);
        notifyUsers(product);
    }
               
    private void respondMessage(Message message){
        JDialog window = new JDialog(frame, "User Message", true); 
        window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        window.setLayout(new BorderLayout());

        JPanel panel  = new JPanel(new GridLayout(2,1));
        JPanel panel2 = new JPanel(new GridLayout(1,2));

        JTextField msm = new JTextField(message.toString());
        msm.setEditable(false);

        JButton ok = new JButton("Continuar");
        JButton res = new JButton("Responder");

        ok.setCursor(new Cursor(12));
        res.setCursor(new Cursor(12));

        panel.add(msm);
        panel2.add(ok);
        panel2.add(res);

        window.add(panel, BorderLayout.CENTER);
        window.add(panel2, BorderLayout.SOUTH);

        window.pack();
        window.setResizable(false);
        window.setVisible(true);

        ok.addActionListener(e -> {
            window.dispose();
        });

        res.addActionListener(e -> {
            try {
                Message novaMensagem = new Message(this);
                novaMensagem.setMessage(txtUsername.getText(), JOptionPane.showInputDialog(this, "Qual mensagem quer enviar", "Respondendo mensagem", -1), 0);
                message.aux.getMessageBox().add(novaMensagem);
                JOptionPane.showMessageDialog(frame, "Messagem enviada com sucesso!");
            }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(null, "Err em Adicionar ao configuar a mensagem.", "Aviso", 0);
            }finally{
                window.dispose();
            }
        });

    }

    public Boolean pause;
    public JDialog window;
    public void showFeed() throws Exception{
        window = new JDialog();
        window.setSize(400,450);
        window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLayout(new BorderLayout());
        window.getContentPane().setBackground(new Color(51,60,76));

        JPanel panCompleto = new JPanel();
        panCompleto.setLayout(new GridLayout(0,1));

        for(int i = 0; i <bancoDados.getProducts().size();i++){
            Product product = bancoDados.getProducts().get(i);
            
            JPanel subPanel = new JPanel();
            subPanel.setLayout(new BorderLayout());
            JPanel panel = new JPanel();
            JTextArea dados = new JTextArea(product.toString());
            JTextArea productState = new JTextArea(product.getState().actionState(product));

            productState.setBackground(Color.lightGray);
            productState.setOpaque(true);
            productState.setForeground(new Color(51,60,76));
            productState.setEditable(false);

            dados.setBackground(Color.lightGray);
            dados.setOpaque(true);
            dados.setForeground(new Color(51,60,76));
            dados.setEditable(false);

            panel.add(dados);
            JPanel menu = new  JPanel(new GridLayout(1,1));

            
            JButton add = new JButton("Carinho");
            add.setCursor(new Cursor(12));
            add.setBackground(new Color(225,127,17));
    
            menu.add(add);
        
            subPanel.add(productState, BorderLayout.NORTH);
            subPanel.add(panel, BorderLayout.CENTER);
            subPanel.add(menu, BorderLayout.SOUTH);
            panCompleto.add(subPanel);

            add.addActionListener(e ->{
                if(this.getMycart().contains(product)){
                    JOptionPane.showMessageDialog(this, "Produto já existente em seu carrinho.");
                }else{
                    this.getMycart().add(product);
                    JOptionPane.showMessageDialog(this, "Produto adicionado ao seu carrinho.");
                }
                window.dispose();
            }); 
        if(product.checkUnavailableState()) add.setEnabled(false);
        }
    
        JButton cancelar = new JButton("Cancelar");
        cancelar.setCursor(new Cursor(12));
        cancelar.setBackground(new Color(200,80,100));

        cancelar.addActionListener(e ->{
            window.dispose();
        });

        JScrollPane scroll = new JScrollPane(panCompleto);

        window.add(scroll, BorderLayout.CENTER);
        window.add(cancelar, BorderLayout.SOUTH);
        window.setTitle("Feed");
        window.setModal(true);
        window.repaint();
        window.setVisible(true);
    }

    private void showCart() throws Exception{
        for(int i = 0; i< this.getMycart().size(); i++){
            this.aux = true;

            while(this.aux){
                Product produto = this.getMycart().get(i);
                this.pause = false;

                JDialog window = new JDialog(this, produto.getName());
                window.setTitle("Carrinho");
                window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                window.setModal(true);
                window.setLayout(new BorderLayout());
                window.getContentPane().setBackground(new Color(51,60,76));
                window.setBackground(new Color(51,60,76));

                JPanel panel = new JPanel();
                JPanel menu = new  JPanel(new GridLayout(1,3));

                JButton cancelar = new JButton("Cancelar");
                JButton comprar = new JButton("Comprar");
                JButton proximo = new JButton("Próximo");

                cancelar.setCursor(new Cursor(12));
                comprar.setCursor(new Cursor(12));
                proximo.setCursor(new Cursor(12));

                cancelar.setBackground(new Color(200,80,100));
                comprar.setBackground(new Color(225,127,17));
                proximo.setBackground(new Color(225,127,17));

                menu.add(cancelar);
                menu.add(comprar);
                menu.add(proximo);

                JTextArea dados = new JTextArea(produto.toString());
                dados.setEditable(false);
                dados.setAutoscrolls(true);
                dados.setBackground(Color.lightGray);
                panel.setBackground(Color.lightGray);
                panel.add(dados);

                JTextArea productState = new JTextArea(produto.getState().actionState(produto));
                productState.setEditable(false);

                window.add(productState, BorderLayout.NORTH);
                window.add(panel, BorderLayout.CENTER);
                window.add(menu, BorderLayout.SOUTH);

                cancelar.addActionListener(e -> {
                    this.pause = true;
                    this.aux = false;
                    window.dispose();
                });

                proximo.addActionListener(e -> {
                    this.aux = false;
                    window.dispose();
                });

                comprar.addActionListener(e -> {
                    Command buyButton = new BuyButton(new CommandReceiver(), this, produto);
                    buyButton.execute();
                });

                if(produto.checkUnavailableState()) comprar.setEnabled(false);

                window.pack();
                window.setVisible(true);
            }
            if(this.pause) break;
        }
    }

    public boolean aux;
    private void showMyProduct() throws Exception{
        for(int i=0; i<this.getMyProduct().size(); i++){
            Product product = this.getMyProduct().get(i);
            this.aux = true;

            while(this.aux){
                this.pause = false;

                JDialog window = new JDialog(this, product.getName());
                window.setTitle("Meus Produtos");
                window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                window.setModal(true);
                window.setLayout(new BorderLayout());
                window.getContentPane().setBackground(new Color(51,60,76));
                window.setTitle(product.getName());
                window.setResizable(false);

                JPanel panel = new JPanel();
                JPanel menu  = new  JPanel(new GridLayout(1,4));

                JButton cancelar = new JButton("Cancelar");
                JButton excluir  = new JButton("Excluir");
                JButton proximo  = new JButton("Próximo");
                JButton edit     = new JButton("Editar");

                cancelar.setCursor(new Cursor(12));
                excluir.setCursor(new Cursor(12));
                proximo.setCursor(new Cursor(12));
                edit.setCursor(new Cursor(12));

                cancelar.setBackground(new Color(200,80,100));
                excluir.setBackground(new Color(225,127,17));
                proximo.setBackground(new Color(225,127,17));
                edit.setBackground(new Color(225,127,17));

                menu.add(cancelar);
                menu.add(excluir);
                menu.add(edit);
                menu.add(proximo);

                JTextArea dados = new JTextArea(product.toString());
                dados.setEditable(false);
                dados.setBackground(Color.lightGray);
                panel.setBackground(Color.lightGray);
                panel.add(dados);

                JTextArea producState = new JTextArea(product.getState().actionState(product));
                producState.setBackground(Color.lightGray);

                window.add(producState, BorderLayout.NORTH);
                window.add(panel, BorderLayout.CENTER);
                window.add(menu, BorderLayout.SOUTH);


                cancelar.addActionListener(e ->{
                    this.pause = true;
                    this.aux = false;
                    window.dispose();
                });

                excluir.addActionListener(e ->{
                    bancoDados.getProducts().remove(product);
                    this.getMyProduct().remove(product);
                    JOptionPane.showMessageDialog(this, "Produto removido!");
                    window.dispose();
                    this.aux = false;
                });

                edit.addActionListener(e ->{
                    product.editProduct();  
                    this.aux = false;
                });

                proximo.addActionListener(e ->{
                    this.aux = false;
                    window.dispose();
                });

                window.pack();
                window.setVisible(true);
            }
            if(this.pause) break;
        }
        this.pack();
    }

    private void showCompras(){
        window = new JDialog();
        window.setTitle("Compras");
        window.setSize(400,450);
        window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLayout(new BorderLayout());
        window.getContentPane().setBackground(new Color(51,60,76));

        JPanel panCompleto = new JPanel();
        panCompleto.setLayout(new GridLayout(0,1));

        for(int i=0; i<compras.size(); i++){
            CloneProduct product = compras.get(i);

            JPanel subPanel = new JPanel();
            JPanel panel    = new JPanel();
            subPanel.setLayout(new BorderLayout());

            JTextArea dados        = new JTextArea(product.toString());
            JTextArea productState = new JTextArea(product.state.actionState(null));

            productState.setBackground(Color.lightGray);
            productState.setOpaque(true);
            productState.setForeground(new Color(51,60,76));
            productState.setEditable(false);

            dados.setBackground(Color.lightGray);
            dados.setOpaque(true);
            dados.setForeground(new Color(51,60,76));
            dados.setEditable(false);

            panel.add(dados);
            JPanel menu = new  JPanel(new GridLayout(1,1));

            JButton confir = new JButton("Confirmar entrega");
            confir.setCursor(new Cursor(12));
            confir.setBackground(new Color(225,127,17));

            menu.add(confir);

            subPanel.add(productState, BorderLayout.NORTH);
            subPanel.add(panel, BorderLayout.CENTER);
            subPanel.add(menu, BorderLayout.SOUTH);
            panCompleto.add(subPanel);

            confir.addActionListener(e -> {
                product.setState(new FinishedState());
                window.repaint();
            });
            if(product.state instanceof FinishedState){
                confir.setText("Finalizado");
                confir.setEnabled(false);
                repaint();
            }
        }

        JButton cancelar = new JButton("Cancelar");
        cancelar.setCursor(new Cursor(12));
        cancelar.setBackground(new Color(200,80,100));

        cancelar.addActionListener(e ->{
            window.dispose();
        });

        JScrollPane scroll = new JScrollPane(panCompleto);

        window.add(scroll, BorderLayout.CENTER);
        window.add(cancelar, BorderLayout.SOUTH);
        window.setModal(true);
        window.repaint();
        window.setVisible(true);
    }
   
} 