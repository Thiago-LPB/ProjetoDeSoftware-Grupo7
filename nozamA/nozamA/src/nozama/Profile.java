package nozama;
import nozama.payment.BuyBoleto;
import nozama.payment.BuyPix;
import nozama.products.*;

import java.util.Iterator;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Profile extends User{

    Bd bancoDados = Bd.getInstance();
    Scanner input = new Scanner(System.in);
    boolean comp = false;
    Iterator<Product> itrProducts;
    Iterator<Profile> itrProfiles;
    Iterator<Message> itrMessages;
    JDialog frame = this;

    public Profile(){
        super();
    }

    private void clearJDialog(){
        this.getContentPane().removeAll();
        this.revalidate();
        this.repaint();
        this.setModal(true);
    }

    public void menuProfile(){
        bancoDados.mainWindow.setVisible(false);
        clearJDialog();
        this.setTitle("Profile");
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);

        JPanel buttons = new JPanel(new GridLayout(7,1));

        bancoDados.loginWindow.setVisible(false);
        JButton feed    = new JButton("       Feed       ");
        JButton post    = new JButton("Criar Produto");
        JButton cart    = new JButton("    Carrinho    ");
        JButton product = new JButton("    Produtos    ");
        JButton message = new JButton("  Messagem  ");
        JButton edit    = new JButton("       Perfil       ");
        JButton exit    = new JButton("         Sair        ");

        JPanel feedPanel = new JPanel();
        JPanel postPanel = new JPanel();
        JPanel cartPanel = new JPanel();
        JPanel productPanel = new JPanel();
        JPanel messagePanel = new JPanel();
        JPanel editPanel = new JPanel();
        JPanel exitPanel = new JPanel();

        feedPanel.setBackground(new Color(51,60,76));
        postPanel.setBackground(new Color(51,60,76));
        cartPanel.setBackground(new Color(51,60,76));
        productPanel.setBackground(new Color(51,60,76));
        messagePanel.setBackground(new Color(51,60,76));
        editPanel.setBackground(new Color(51,60,76));
        exitPanel.setBackground(new Color(51,60,76));

        feed.setCursor(new Cursor(12));
        post.setCursor(new Cursor(12));
        cart.setCursor(new Cursor(12));
        product.setCursor(new Cursor(12));
        message.setCursor(new Cursor(12));
        edit.setCursor(new Cursor(12));
        exit.setCursor(new Cursor(12));

        feed.setBackground(new Color(225,127,17));
        post.setBackground(new Color(225,127,17));
        cart.setBackground(new Color(225,127,17));
        product.setBackground(new Color(225,127,17));
        message.setBackground(new Color(225,127,17));
        edit.setBackground(new Color(225,127,17));
        exit.setBackground(new Color(225,127,17));

        feedPanel.add(feed);
        postPanel.add(post);
        cartPanel.add(cart);
        productPanel.add(product);
        messagePanel.add(message);
        editPanel.add(edit);
        exitPanel.add(exit);

        
        buttons.add(feedPanel);
        buttons.add(postPanel);
        buttons.add(cartPanel);
        buttons.add(productPanel);
        buttons.add(messagePanel);
        buttons.add(editPanel);
        buttons.add(exitPanel);

        JTextArea dados = new JTextArea(this.toString());
        System.out.println(this.toString());
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
                if(bancoDados.getProducts().isEmpty()){
                    JOptionPane.showMessageDialog(frame, "Não há produto no Feed!");
                }else{
                    setVisible(false);
                    showFeed();
                    setVisible(true);
                }
            }
        });

        post.addActionListener(e -> {       
            setVisible(false);
            creatProduct();
            setVisible(true);
        });

        cart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getMycart().isEmpty()){
                    JOptionPane.showMessageDialog(frame, "Não há produtos no carinho!");
                }else{
                    setVisible(false);
                    showCart();
                    setVisible(true);
                }
            }
        });

        product.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getMyProduct().isEmpty()){
                    JOptionPane.showMessageDialog(frame, "Voce ainda não tem produtos postados!");
                }else{
                    setVisible(false);
                    showMyProduct();
                    setVisible(true);
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

    }

    private boolean menuBuy(){
        String messageMenuBuy = "Gostaria de adicionar o produto ao seu carrinho?";
        String title = "Adicionar ao carrinho";
        JOptionPane.showConfirmDialog(this, messageMenuBuy, title, 4);
        return true;
    }

    private void creatProduct(){
        JDialog frame = new JDialog(this, "Criando");
        frame.setLayout(new GridLayout(5,1));
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(51,60,76));

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

        casaPanel.setBackground(new Color(51,60,76));
        comidaPanel.setBackground(new Color(51,60,76));
        eletricPanel.setBackground(new Color(51,60,76));
        roupaPanel.setBackground(new Color(51,60,76));
        cancelarPanel.setBackground(new Color(51,60,76));

        casa.setCursor(new Cursor(12));
        comida.setCursor(new Cursor(12));
        eletric.setCursor(new Cursor(12));
        roupa.setCursor(new Cursor(12));
        cancelar.setCursor(new Cursor(12));

        casa.setBackground(new Color(0,200,200));
        comida.setBackground(new Color(0,200,200));
        eletric.setBackground(new Color(0,200,200));
        roupa.setBackground(new Color(0,200,200));
        cancelar.setBackground(new Color(200,80,100));

        casa.setBackground(new Color(225,127,17));
        comida.setBackground(new Color(225,127,17));
        eletric.setBackground(new Color(225,127,17));
        roupa.setBackground(new Color(225,127,17));
        cancelar.setBackground(new Color(200,80,100));

        casaPanel.add(casa);
        comidaPanel.add(comida);
        eletricPanel.add(eletric);
        roupaPanel.add(roupa);
        cancelarPanel.add(cancelar);

        casa.addActionListener(e -> {
            Product product = new Casa(this);
            bancoDados.getProducts().add(product);
            setMyProduct(product);

            for(int i=0; i<bancoDados.getUsers().size(); i++){
                Profile perfil = bancoDados.getUsers().get(i);

                if(perfil.getFavorito().equals(product.getCategory())){
                    Message productMessage = new Message(this);
                    productMessage.setMessageProduct(this.getName(), product.getName());
                    perfil.getMessageBox().add(productMessage);
                }
            }
            this.aux = true;
        });

        comida.addActionListener(e -> {
            Product product = new Comida(this);
            bancoDados.getProducts().add(product);
            setMyProduct(product);

            for(int i=0; i<bancoDados.getUsers().size(); i++){
                Profile perfil = bancoDados.getUsers().get(i);

                if(perfil.getFavorito().equals(product.getCategory())){
                    Message productMessage = new Message(this);
                    productMessage.setMessageProduct(this.getName(), product.getName());
                    perfil.getMessageBox().add(productMessage);
                }
            }

            this.aux = true;
        });

        eletric.addActionListener(e -> {
            Product product = new Eletrics(this);
            bancoDados.getProducts().add(product);
            setMyProduct(product);

            for(int i=0; i<bancoDados.getUsers().size(); i++){
                Profile perfil = bancoDados.getUsers().get(i);

                if(perfil.getFavorito().equals(product.getCategory())){
                    Message productMessage = new Message(this);
                    productMessage.setMessageProduct(this.getName(), product.getName());
                    perfil.getMessageBox().add(productMessage);
                }
            }
            this.aux = true;
        });

        roupa.addActionListener(e -> {
            Product product = new Roupas(this);
            bancoDados.getProducts().add(product);
            setMyProduct(product);

            for(int i=0; i<bancoDados.getUsers().size(); i++){
                Profile perfil = bancoDados.getUsers().get(i);

                if(perfil.getFavorito().equals(product.getCategory())){
                    Message productMessage = new Message(this);
                    productMessage.setMessageProduct(this.getName(), product.getName());
                    perfil.getMessageBox().add(productMessage);
                }
            }
        });

        cancelar.addActionListener(e -> {
            frame.dispose();
        });
        
        frame.add(casaPanel);
        frame.add(comidaPanel);
        frame.add(eletricPanel);
        frame.add(roupaPanel);
        frame.add(cancelarPanel);
        frame.repaint();

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void respondMessage(Message message){
        JDialog window = new JDialog(frame, "User Message", true); 
        window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        window.setLayout(new BorderLayout());

        JPanel panel  = new JPanel(new GridLayout(2,1));
        JPanel panel2 = new JPanel(new GridLayout(1,2));

        JTextField msm = new JTextField(message.toString());
        msm.setEditable(false);

        /*JTextField respond = new JTextField("");
        respond.setEditable(true);
        respond.setToolTipText("Caso queira responder insira seu texto aq");*/

        JButton ok = new JButton("Continuar");
        JButton res = new JButton("Responder");

        ok.setCursor(new Cursor(12));
        res.setCursor(new Cursor(12));

        panel.add(msm);
        //panel.add(respond);

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
            Message novaMensagem = new Message(this);
            novaMensagem.setMessage(txtUsername.getText(), JOptionPane.showInputDialog(this, "Qual mensagem quer enviar", "Respondendo mensagem", -1));
            message.aux.getMessageBox().add(novaMensagem);
            JOptionPane.showMessageDialog(frame, "Messagem enviada com sucesso!");
            window.dispose();
        });

    }

    Boolean pause;

    JDialog window;
    
    public void showFeed(){
            window = new JDialog();
            window.setSize(400,450);
            window.getContentPane().setBackground(new Color(51,60,76));
            window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            window.setLocationRelativeTo(null);
            window.setLayout(new BorderLayout());
            window.getContentPane().setBackground(new Color(51,60,76));
           JPanel pan_completo = new JPanel();
           pan_completo.setLayout(new GridLayout(0,1));
        for(int i = 0; i <bancoDados.getProducts().size();i++){
            Product product = bancoDados.getProducts().get(i);
            
            
            JPanel sub_panel = new JPanel();
            sub_panel.setLayout(new BorderLayout());
            JPanel panel = new JPanel();
            JTextArea dados = new JTextArea(product.toString());

            dados.setBackground(Color.lightGray);
            dados.setOpaque(true);
            dados.setForeground(new Color(51,60,76));
            dados.setEditable(false);

            panel.add(dados);
            JPanel menu = new  JPanel(new GridLayout(1,3));

            JButton cancelar = new JButton("Cancelar");
            JButton add = new JButton("Carinho");
            

            cancelar.setCursor(new Cursor(12));
            add.setCursor(new Cursor(12));
         

            cancelar.setBackground(new Color(200,80,100));
            add.setBackground(new Color(225,127,17));
       

            menu.add(cancelar);
            menu.add(add);
        
            sub_panel.add(panel, BorderLayout.CENTER);
            sub_panel.add(menu, BorderLayout.SOUTH);
            pan_completo.add(sub_panel);
        

            cancelar.addActionListener(e ->{

                window.dispose();
            });

            

            add.addActionListener(e ->{
                this.getMycart().add(product);
                JOptionPane.showMessageDialog(this, "Produto adicionado ao seu carrinho.");
                window.dispose();
            });

            
            

            
           
           
        }
        JScrollPane scroll = new JScrollPane(pan_completo);
        window.add(scroll, BorderLayout.CENTER);
        window.setModal(true);
        window.repaint();
        window.setVisible(true);
    }

    private void showCart(){
        
        for(int i = 0; i< this.getMycart().size(); i++){
            this.aux = true;

            while(this.aux){
                Product produto = this.getMycart().get(i);
                this.pause = false;

                JDialog window = new JDialog(this, produto.getName());
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
                    this.pause = false;
                    String[] l = {"Boleto", "Pix"};
                    JDialog pagamento = new JDialog();
                    pagamento.setLayout(new BorderLayout());
                    JPanel p = new JPanel();
                    JPanel p2 = new JPanel();
                    p.setLayout(new BorderLayout());
                    p2.setLayout(new GridLayout(1,2));
                    pagamento.setTitle("Pagamento");
                    JLabel forma = new JLabel("Selecione a forma de pagamento: ");
                    JComboBox cp = new JComboBox(l);
                    JButton confirmar = new JButton("Confirmar");
                    
                    confirmar.addActionListener(a -> {
                    pagamento.dispose();
                    if(cp.getSelectedItem() == "Boleto"){
                        nozama.payment.Buy b = new nozama.payment.BuyBoleto(this,produto.owner , produto);
                    }else{
                        nozama.payment.Buy b = new nozama.payment.BuyPix(this,produto.owner , produto);
                    }
                    if(produto.getQtd() <= 0){
                            bancoDados.getProducts().remove(produto);
                    }
                    this.getMycart().remove(produto);
                    this.aux = false;
                    });

                    p2.add(forma);
                    p2.add(cp);
                    p.add(p2,BorderLayout.CENTER);
                    p.add(confirmar,BorderLayout.SOUTH);
                    pagamento.add(p,BorderLayout.CENTER);
                    pagamento.pack();
                    pagamento.setModal(true);
                    pagamento.setVisible(true);
                    window.repaint();
                    window.dispose();
                });

                
                window.pack();
                window.setVisible(true);
            }
            if(this.pause) break;
        }
    }
    boolean aux;
    private void showMyProduct(){
        for(int i=0; i<this.getMyProduct().size(); i++){
            Product product = this.getMyProduct().get(i);
            this.aux = true;

            while(this.aux){
                this.pause = false;

                JDialog window = new JDialog(this, product.getName());
                window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                window.setModal(true);
                window.setLayout(new BorderLayout());
                window.getContentPane().setBackground(new Color(51,60,76));
                window.setTitle(product.getName());
                window.setResizable(false);

                JPanel panel = new JPanel();
                JPanel menu = new  JPanel(new GridLayout(1,3));

                JButton cancelar = new JButton("Cancelar");
                JButton excluir = new JButton("Excluir");
                JButton proximo = new JButton("Próximo");

                cancelar.setCursor(new Cursor(12));
                excluir.setCursor(new Cursor(12));
                proximo.setCursor(new Cursor(12));

                cancelar.setBackground(new Color(200,80,100));
                excluir.setBackground(new Color(225,127,17));
                proximo.setBackground(new Color(225,127,17));

                menu.add(cancelar);
                menu.add(excluir);
                menu.add(proximo);

                JTextArea dados = new JTextArea(product.toString());
                dados.setEditable(false);
                dados.setBackground(Color.lightGray);
                panel.setBackground(Color.lightGray);
                panel.add(dados);

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
   
} 