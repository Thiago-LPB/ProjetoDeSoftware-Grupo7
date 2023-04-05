package nozama.payment;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import nozama.Profile;
import nozama.products.Product;

public class BuyBoleto  extends Buy {

    private LocalDateTime vencimento;
    JTextArea txt;
    JDialog j;
    JButton comprar, cancelar;
    JPanel panel;
    public BuyBoleto(Profile buyer, Profile seller, Product product){
        super(buyer, seller, product);
        j = new JDialog();
        comprar = new JButton("Comprar");
        cancelar =  new JButton("Cancelar");
        cancelar.addActionListener(e -> {
            j.dispose();
        });
        comprar.addActionListener(e -> {
            if(Pay()){
                JDialog n = new JDialog();
            JPanel z = new JPanel();
            n.setLayout(new BorderLayout());
            z.setLayout(new GridLayout(1,1));
            JLabel lblav = new JLabel("Selecione uma nota para o produto: ");
            JButton enviar =  new JButton("Confirmar");
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
            
            j.dispose();
        });
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(comprar);
        panel.add(cancelar);
        j.setLayout(new BorderLayout());
        j.setModal(true);
        txt = new JTextArea(this.imprimirBoleto());
        j.add(txt,BorderLayout.CENTER);
        j.add(panel,BorderLayout.SOUTH);
        j.setTitle("Boleto");
        
        j.pack();
        j.setVisible(true);
    }

    public String imprimirBoleto(){
        LocalDateTime now = LocalDateTime.now();
        this.vencimento = now.plusDays(7);
        Random r = new Random();
        int n;
        
        String bar = r.nextInt(99999) + 11111+".";
        for(int i = 0;i<5;i++){
n = r.nextInt(99999) + 11111;
bar += "" + n;
if(i+1==5) break;
bar += ".";
        }
        this.vencimento = this.vencimento.plusDays(7);
        return "\nData de emissão do boleto:"+now.getDayOfMonth()+"/"+now.getMonthValue()+"/"
        +now.getYear()+"\n"+"Data de Vencimento do boleto:"+(vencimento.getDayOfMonth())+"/"+vencimento.getMonthValue()+"/"+vencimento.getYear()+
        "\nNome do comprador: "+ buyer.getUser()+"\nNome do vendedor: "+ seller.getUser()+"\nValor:"+product.getPrice()+"\nCódigo de Barras:"+bar;
    }
   




    @Override
    public String getVencimento() {
        return "Data de Vencimento: "+this.vencimento.getDayOfMonth()+"/"+this.vencimento.getMonthValue()+"/"+this.vencimento.getYear();
    }

   

    
    @Override
    public String toString() {
        return super.toString();
    }
    
}
