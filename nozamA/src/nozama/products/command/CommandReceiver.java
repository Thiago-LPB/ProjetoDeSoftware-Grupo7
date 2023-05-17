package nozama.products.command;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

import nozama.payment.*;

import nozama.products.*;
import nozama.users.Profile;

public class CommandReceiver {

    void casa(Casa product) {

        try {

            product.name = product.txtName.getText();
            product.description = product.txtDescription.getText();
            product.price = Float.parseFloat(product.txtPrice.getText());
            product.qtdProduto = Integer.parseInt(product.txtQtd.getText());

            product.largura = Integer.valueOf(product.txtLargura.getText());
            product.comprimento = Integer.valueOf(product.txtComprimento.getText());
            product.construido = product.boxConst.isSelected();
            product.loc = product.boxTer.isSelected();

        } catch (NumberFormatException b) {
            JOptionPane.showMessageDialog(null, "Digite um número", "Erro", JOptionPane.ERROR_MESSAGE);
            product.qtdProduto = 0;
        }
    } 
    
    void comida(Comida product){

        try{

            product.name        = product.txtName.getText();
            product.description = product.txtDescription.getText();
            product.price       = Float.parseFloat(product.txtPrice.getText());
            product.qtdProduto  = Integer.parseInt(product.txtQtd.getText());
            product.lactose     = product.cLac.isSelected();
            product.gluten      = product.cGl.isSelected();
            product.lata        = product.cLat.isSelected();
            product.qtd         = Integer.valueOf(product.txtqtd.getText());
            product.validade    = Integer.valueOf(product.txtdias.getText());

        } catch(NumberFormatException b){
                    JOptionPane.showMessageDialog(null,"Digite um número","Erro",JOptionPane.ERROR_MESSAGE);
                    product.qtdProduto  = 0;
       }
      
    }


    void eletrics(Eletrics product){

        try{
            product.name        = product.txtName.getText();
            product.description = product.txtDescription.getText();
            product.price       = Float.parseFloat(product.txtPrice.getText());
            product.qtdProduto  = Integer.parseInt(product.txtQtd.getText());

        String word = "";
                for(char k : product.comboMenuCapa.getSelectedItem().toString().toCharArray()){
                    if(Character.isDigit(k)){
                        word += k;
                    }
                
            }

            product.capacidade = Integer.parseInt(word);

        
            word = "";
            for(char k : product.comboMenuR.getSelectedItem().toString().toCharArray()){
                if(Character.isDigit(k)){
                    word += k;
                }
        }
            product.resolucao = Integer.parseInt(word);

                }catch(NumberFormatException b){
                    JOptionPane.showMessageDialog(null,"Digite um número","Erro",JOptionPane.ERROR_MESSAGE);
                    product.qtdProduto  = 0;
        }
    }

    void roupas(Roupas product){

        try{
           
            product.name        = product.txtName.getText();
            product.description = product.txtDescription.getText();
            product.price       = Float.parseFloat(product.txtPrice.getText());
            product.qtdProduto  = Integer.parseInt(product.txtQtd.getText());
            product.color       = product.comboCor.getSelectedItem().toString();
            product.size        = product.comboTamanho.getSelectedItem().toString();
        
        }catch(NumberFormatException b){
            JOptionPane.showMessageDialog(null,"Digite um número","Erro",JOptionPane.ERROR_MESSAGE);
            product.qtdProduto  = 0;
        }
    }
    void buy(Buy buy, Product product){

        if(buy.Pay()){
            JDialog n = new JDialog();
            JPanel z = new JPanel();
            n.setLayout(new BorderLayout());
            z.setLayout(new GridLayout(1,1));
            JLabel lblav = new JLabel("Selecione uma nota para o produto: ");
            JButton enviar = new JButton("Confirmar");
            JSlider slider = new JSlider(0,10,5);
            slider.setPaintTicks(true);
            slider.setMinorTickSpacing(1);
            slider.setPaintTrack(true);
            slider.setMajorTickSpacing(1);
            slider.setPaintLabels(true);            
            enviar.addActionListener(q -> {
            product.setRate(slider.getValue());
            n.dispose();
        });
        
        z.add(lblav);
        z.add(slider);
        n.add(z,BorderLayout.CENTER);
        n.add(enviar, BorderLayout.SOUTH);
        n.setModal(true);
        n.pack();
        n.setVisible(true);
        }
    }
    
    void buyButton(Profile profile, Product product){

        profile.pause = false;

        String[] l = {"Boleto", "Pix"};

        JDialog pagamento = new JDialog();
        pagamento.setLayout(new BorderLayout());

        JPanel p  = new JPanel();
        JPanel p2 = new JPanel();

        p.setLayout(new BorderLayout());
        p2.setLayout(new GridLayout(1,2));

        pagamento.setTitle("Pagamento");

        JLabel   forma      = new JLabel("Selecione a forma de pagamento: ");
        JComboBox cp        = new JComboBox(l);
        JButton   confirmar = new JButton("Confirmar");
                    
        confirmar.addActionListener(a -> {
        pagamento.dispose();
                    
        if(cp.getSelectedItem() == "Boleto"){
            Buy b = new BuyBoleto(profile,product.owner , product);
        }else{
            Buy b = new BuyPix(profile,product.owner , product);
        }

        profile.aux = false;

        });

        p2.add(forma);
        p2.add(cp);
        p.add(p2,BorderLayout.CENTER);
        p.add(confirmar,BorderLayout.SOUTH);
        pagamento.add(p,BorderLayout.CENTER);
        pagamento.pack();
        pagamento.setModal(true);
        pagamento.setVisible(true);
        profile.window.repaint();
        profile.window.dispose();
    }
}
